package com.example.debtcancellation.application.core.validation;

import com.example.debtcancellation.application.core.exception.InvalidDebitoIdException;
import com.example.debtcancellation.application.core.domain.model.Debito;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Component
public class DebitoValidator {

    private static final long MAX_DEBT_AGE_DAYS = 365;

    public void validateDebitoId(Long idDebito) {
        if (idDebito == null) {
            throw new InvalidDebitoIdException("Debt ID cannot be null");
        }
        
        if (idDebito <= 0) {
            throw new InvalidDebitoIdException("Debt ID must be a positive number, but was: " + idDebito);
        }
        
        if (idDebito > Long.MAX_VALUE) {
            throw new InvalidDebitoIdException("Debt ID is too large: " + idDebito);
        }
    }

    public void validateDebitoForCancellation(Debito debito) {
        if (debito == null) {
            throw new InvalidDebitoIdException("Debt cannot be null");
        }

        validateDebitoId(debito.idDebito());

        if (debito.criadoEm() != null) {
            Instant cutoffDate = Instant.now().minus(MAX_DEBT_AGE_DAYS, ChronoUnit.DAYS);
            if (debito.criadoEm().isBefore(cutoffDate)) {
                throw new InvalidDebitoIdException(
                    String.format("Debt is too old to be cancelled. Created: %s, Max age: %d days", 
                                debito.criadoEm(), MAX_DEBT_AGE_DAYS));
            }
        }
    }
}
