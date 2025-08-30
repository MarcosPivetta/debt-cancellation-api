package com.example.debtcancellation.application.core.domain.policy;

import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.DecisaoCancelamento;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;

public class PoliticaCancelamentoDefault implements PoliticaCancelamentoDebitoPort {

    @Override
    public DecisaoCancelamento podeCancelar(Debito debito) {
        if (debito.status() != StatusDebito.PENDENTE)
            return DecisaoCancelamento.negado("STATUS_NAO_PENDENTE");

        return DecisaoCancelamento.aprovado();
    }
}
