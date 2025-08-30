package com.example.debtcancellation.adapters.out.repository;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class DebitoRepositoryTest {

    @Autowired
    private DebitoRepository debitoRepository;

    @Test
    void shouldSaveAndFindDebitoEntity() {
        DebitoEntity debito = new DebitoEntity();
        debito.setIdDebito(123L);
        debito.setStatus(StatusDebito.PENDENTE);
        debito.setCriadoEm(Instant.now());

        DebitoEntity savedDebito = debitoRepository.save(debito);
        Optional<DebitoEntity> foundDebito = debitoRepository.findById(123L);

        assertThat(savedDebito).isNotNull();
        assertThat(savedDebito.getIdDebito()).isEqualTo(123L);
        assertThat(savedDebito.getStatus()).isEqualTo(StatusDebito.PENDENTE);
        assertThat(foundDebito).isPresent();
        assertThat(foundDebito.get().getStatus()).isEqualTo(StatusDebito.PENDENTE);
    }

    @Test
    void shouldReturnEmptyWhenDebitoNotFound() {
        Optional<DebitoEntity> foundDebito = debitoRepository.findById(999L);

        assertThat(foundDebito).isEmpty();
    }
}
