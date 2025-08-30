package com.example.debtcancellation.application.core.domain.policy;

import com.example.debtcancellation.application.core.domain.model.DecisaoCancelamento;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import com.example.debtcancellation.application.core.domain.model.Debito;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class PoliticaCancelamentoDefault implements PoliticaCancelamentoDebitoPort {

    private static final long MAX_DEBT_AGE_DAYS = 365;

    @Override
    public DecisaoCancelamento podeCancelar(StatusDebito statusDebito) {
        if (statusDebito == null) {
            return DecisaoCancelamento.negado("STATUS_NULO");
        }

        if (statusDebito == StatusDebito.CANCELADO) {
            return DecisaoCancelamento.negado("DEBITO_JA_CANCELADO");
        }

        if (statusDebito == StatusDebito.CONFIRMADO) {
            return DecisaoCancelamento.negado("DEBITO_JA_CONFIRMADO");
        }

        if (statusDebito != StatusDebito.PENDENTE) {
            return DecisaoCancelamento.negado("STATUS_NAO_PENDENTE");
        }

        return DecisaoCancelamento.aprovado();
    }

    public DecisaoCancelamento podeCancelar(Debito debito) {
        if (debito == null) {
            return DecisaoCancelamento.negado("DEBITO_NULO");
        }

        DecisaoCancelamento decisaoStatus = podeCancelar(debito.status());
        if (!decisaoStatus.status()) {
            return decisaoStatus;
        }

        if (debito.criadoEm() != null) {
            Instant cutoffDate = Instant.now().minus(MAX_DEBT_AGE_DAYS, ChronoUnit.DAYS);
            if (debito.criadoEm().isBefore(cutoffDate)) {
                return DecisaoCancelamento.negado("DEBITO_MUITO_ANTIGO");
            }
        }

        return DecisaoCancelamento.aprovado();
    }
}
