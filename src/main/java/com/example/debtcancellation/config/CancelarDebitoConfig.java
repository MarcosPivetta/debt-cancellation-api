package com.example.debtcancellation.config;

import com.example.debtcancellation.adapters.out.repository.mapper.DebitoEntityMapper;
import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDebitoPort;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.application.ports.in.FindDebitoByIdInputPort;
import com.example.debtcancellation.application.usecases.CancelarDebitoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelarDebitoConfig {

    @Bean
    public CancelarDebitoInputPort cancelarDebitoInputPort(
            PoliticaCancelamentoDebitoPort politicaCancelamentoDebito,
            FindDebitoByIdInputPort findDebitoByIdInputPort,
            DebitoEntityMapper debitoEntityMapper
    ) {
        return new CancelarDebitoUseCase(
                politicaCancelamentoDebito,
                findDebitoByIdInputPort,
                debitoEntityMapper
        );
    }

}
