package com.example.debtcancellation.application.usecases;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.ports.in.FindDebitoByIdInputPort;
import com.example.debtcancellation.application.ports.out.FindDebitoByIdOutputPort;

public record FindDebitoByIdUseCase(
        FindDebitoByIdOutputPort findDebitoByIdOutputPort) implements FindDebitoByIdInputPort {

    @Override
    public DebitoEntity findDebitoById(Long idDebito) {
        return findDebitoByIdOutputPort.findDebitoById(idDebito)
                .orElseThrow(() -> new RuntimeException("Débito não encontrado com id: " + idDebito));
    }
}
