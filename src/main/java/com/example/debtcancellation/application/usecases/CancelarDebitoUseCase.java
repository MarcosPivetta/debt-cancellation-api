package com.example.debtcancellation.application.usecases;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.adapters.out.repository.mapper.DebitoEntityMapper;
import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.DecisaoCancelamento;
import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDebitoPort;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.application.ports.in.FindDebitoByIdInputPort;

public record CancelarDebitoUseCase(
        PoliticaCancelamentoDebitoPort politicaCancelamentoDebito,
        FindDebitoByIdInputPort findDebitoByIdInputPort,
        DebitoEntityMapper debitoEntityMapper) implements CancelarDebitoInputPort {

    @Override
    public ResultadoCancelamento cancelar(Long idDebito) {
        DebitoEntity debitoEntity = findDebitoByIdInputPort.findDebitoById(idDebito);
        Debito debito = debitoEntityMapper.toDebito(debitoEntity);
        DecisaoCancelamento decisao = politicaCancelamentoDebito.podeCancelar(debito);
        
        return new ResultadoCancelamento(
            debito.idDebito(),
            decisao.status(),
            decisao.motivo()
        );
    }
}
