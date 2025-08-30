package com.example.debtcancellation.application.ports.out;

import com.example.debtcancellation.application.core.domain.model.Debito;

public interface ValidarDebitoOutputPort {
    Debito buscarDebito(Long idDebito);
}
