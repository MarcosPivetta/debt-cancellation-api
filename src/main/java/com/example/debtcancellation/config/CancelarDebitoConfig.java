package com.example.debtcancellation.config;

import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDebitoPort;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.application.ports.out.ValidarDebitoOutputPort;
import com.example.debtcancellation.application.usecases.CancelarDebitoUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CancelarDebitoConfig {

    @Bean
    public CancelarDebitoInputPort cancelarDebitoInputPort(
            PoliticaCancelamentoDebitoPort politicaCancelamentoDebitoPort,
            ValidarDebitoOutputPort validarDebitoOutputPort) {
        return new CancelarDebitoUseCase(
                politicaCancelamentoDebitoPort,
                validarDebitoOutputPort);
    }
}
