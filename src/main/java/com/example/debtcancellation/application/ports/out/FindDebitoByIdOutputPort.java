package com.example.debtcancellation.application.ports.out;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import java.util.Optional;

public interface FindDebitoByIdOutputPort {
    Optional<DebitoEntity> findDebitoById(Long id);
}
