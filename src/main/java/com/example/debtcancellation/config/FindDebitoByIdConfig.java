package com.example.debtcancellation.config;

import com.example.debtcancellation.application.ports.in.FindDebitoByIdInputPort;
import com.example.debtcancellation.application.ports.out.FindDebitoByIdOutputPort;
import com.example.debtcancellation.application.usecases.FindDebitoByIdUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FindDebitoByIdConfig {

    @Bean
    public FindDebitoByIdInputPort findDebitoByIdInputPort(
            FindDebitoByIdOutputPort findDebitoByIdOutputPort
    ) {
        return new FindDebitoByIdUseCase(
                findDebitoByIdOutputPort
        );
    }
}
