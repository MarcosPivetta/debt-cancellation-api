package com.example.debtcancellation.adapters.out;

import com.example.debtcancellation.adapters.out.repository.DebitoRepository;
import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.ports.out.FindDebitoByIdOutputPort;
import org.springframework.stereotype.Component;
import java.util.Optional;

@Component
public class FindDebitoByIdAdapter implements FindDebitoByIdOutputPort {

    private final DebitoRepository debitoRepository;

    public FindDebitoByIdAdapter(DebitoRepository debitoRepository) {
        this.debitoRepository = debitoRepository;
    }

    @Override
    public Optional<DebitoEntity> findDebitoById(Long idDebito) {
        return debitoRepository.findById(idDebito);
    }
}
