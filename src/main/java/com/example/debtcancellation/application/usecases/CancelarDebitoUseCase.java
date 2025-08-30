package com.example.debtcancellation.application.usecases;

import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.DecisaoCancelamento;
import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDebitoPort;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.application.ports.out.ValidarDebitoOutputPort;

public record CancelarDebitoUseCase(
        PoliticaCancelamentoDebitoPort politicaCancelamentoDebito,
        ValidarDebitoOutputPort validarDebitoOutputPort) implements CancelarDebitoInputPort {

    @Override
    public ResultadoCancelamento cancelar(Long idDebito) {
        Debito debito = validarDebitoOutputPort.buscarDebito(idDebito);
        DecisaoCancelamento decisao = politicaCancelamentoDebito.podeCancelar(debito.status());
        
        return new ResultadoCancelamento(
            debito.idDebito(),
            decisao.status(),
            decisao.motivo()
        );
    }
}
