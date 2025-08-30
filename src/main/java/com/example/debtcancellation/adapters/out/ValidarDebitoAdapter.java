package com.example.debtcancellation.adapters.out;

import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import com.example.debtcancellation.application.ports.out.ValidarDebitoOutputPort;
import org.springframework.stereotype.Component;
import java.time.Instant;

@Component
public class ValidarDebitoAdapter implements ValidarDebitoOutputPort {

    @Override
    public Debito buscarDebito(Long idDebito) {
        return new Debito(idDebito, StatusDebito.PENDENTE, Instant.now());
    }
}
