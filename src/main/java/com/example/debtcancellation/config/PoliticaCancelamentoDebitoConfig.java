package com.example.debtcancellation.config;

import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDebitoPort;
import com.example.debtcancellation.application.core.domain.policy.PoliticaCancelamentoDefault;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoliticaCancelamentoDebitoConfig {

    @Bean
    public PoliticaCancelamentoDebitoPort politicaCancelamentoDebito() {
        return new PoliticaCancelamentoDefault();
    }
}
