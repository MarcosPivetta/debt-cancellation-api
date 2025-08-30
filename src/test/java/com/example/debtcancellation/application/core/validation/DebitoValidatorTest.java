package com.example.debtcancellation.application.core.validation;

import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import com.example.debtcancellation.application.core.exception.InvalidDebitoIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class DebitoValidatorTest {

    private DebitoValidator debitoValidator;

    @BeforeEach
    void setUp() {
        debitoValidator = new DebitoValidator();
    }

    @Test
    void shouldValidateValidDebitoId() {
        assertDoesNotThrow(() -> debitoValidator.validateDebitoId(123L));
        assertDoesNotThrow(() -> debitoValidator.validateDebitoId(1L));
        assertDoesNotThrow(() -> debitoValidator.validateDebitoId(Long.MAX_VALUE));
    }

    @Test
    void shouldThrowExceptionForNullDebitoId() {
        assertThatThrownBy(() -> debitoValidator.validateDebitoId(null))
                .isInstanceOf(InvalidDebitoIdException.class)
                .hasMessage("Debt ID cannot be null");
    }

    @Test
    void shouldThrowExceptionForZeroDebitoId() {
        assertThatThrownBy(() -> debitoValidator.validateDebitoId(0L))
                .isInstanceOf(InvalidDebitoIdException.class)
                .hasMessage("Debt ID must be a positive number, but was: 0");
    }

    @Test
    void shouldThrowExceptionForNegativeDebitoId() {
        assertThatThrownBy(() -> debitoValidator.validateDebitoId(-1L))
                .isInstanceOf(InvalidDebitoIdException.class)
                .hasMessage("Debt ID must be a positive number, but was: -1");
    }

    @Test
    void shouldValidateValidDebitoForCancellation() {
        Debito debito = new Debito(123L, StatusDebito.PENDENTE, Instant.now());
        assertDoesNotThrow(() -> debitoValidator.validateDebitoForCancellation(debito));
    }

    @Test
    void shouldThrowExceptionForNullDebito() {
        assertThatThrownBy(() -> debitoValidator.validateDebitoForCancellation(null))
                .isInstanceOf(InvalidDebitoIdException.class)
                .hasMessage("Debt cannot be null");
    }

    @Test
    void shouldThrowExceptionForOldDebito() {
        Instant oldDate = Instant.now().minus(400, ChronoUnit.DAYS);
        Debito oldDebito = new Debito(123L, StatusDebito.PENDENTE, oldDate);
        
        assertThatThrownBy(() -> debitoValidator.validateDebitoForCancellation(oldDebito))
                .isInstanceOf(InvalidDebitoIdException.class)
                .hasMessageContaining("Debt is too old to be cancelled");
    }

    @Test
    void shouldValidateDebitoWithNullCreationDate() {
        Debito debito = new Debito(123L, StatusDebito.PENDENTE, null);
        assertDoesNotThrow(() -> debitoValidator.validateDebitoForCancellation(debito));
    }
}
