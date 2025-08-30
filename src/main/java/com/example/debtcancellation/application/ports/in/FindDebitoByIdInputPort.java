package com.example.debtcancellation.application.ports.in;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;

public interface FindDebitoByIdInputPort {
    DebitoEntity findDebitoById(Long idDebito);
}
