package com.example.debtcancellation.application.core.domain.policy;

import com.example.debtcancellation.application.core.domain.model.DecisaoCancelamento;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import com.example.debtcancellation.application.core.domain.model.Debito;

public interface PoliticaCancelamentoDebitoPort {
    DecisaoCancelamento podeCancelar(StatusDebito statusDebito);
    DecisaoCancelamento podeCancelar(Debito debito);
}
