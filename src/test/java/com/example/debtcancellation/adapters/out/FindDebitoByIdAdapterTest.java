package com.example.debtcancellation.adapters.out;

import com.example.debtcancellation.adapters.out.repository.DebitoRepository;
import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Instant;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class FindDebitoByIdAdapterTest {

    @Mock
    private DebitoRepository debitoRepository;

    @InjectMocks
    private FindDebitoByIdAdapter findDebitoByIdAdapter;

    @Test
    void shouldFindDebitoById() {
        Long debitoId = 123L;
        DebitoEntity debitoEntity = new DebitoEntity();
        debitoEntity.setIdDebito(123L);
        debitoEntity.setStatus(StatusDebito.PENDENTE);
        debitoEntity.setCriadoEm(Instant.now());

        when(debitoRepository.findById(debitoId)).thenReturn(Optional.of(debitoEntity));

        Optional<DebitoEntity> result = findDebitoByIdAdapter.findDebitoById(debitoId);

        assertThat(result).isPresent();
        assertThat(result.get().getIdDebito()).isEqualTo(123L);
        assertThat(result.get().getStatus()).isEqualTo(StatusDebito.PENDENTE);
    }

    @Test
    void shouldReturnEmptyWhenDebitoNotFound() {
        Long debitoId = 999L;
        when(debitoRepository.findById(debitoId)).thenReturn(Optional.empty());

        Optional<DebitoEntity> result = findDebitoByIdAdapter.findDebitoById(debitoId);

        assertThat(result).isEmpty();
    }
}
