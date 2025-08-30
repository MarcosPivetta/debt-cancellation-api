package com.example.debtcancellation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class DebtCancellationRequest {

    @NotNull(message = "Debt ID cannot be null")
    @Positive(message = "Debt ID must be a positive number")
    private Long debtId;

    public DebtCancellationRequest() {
    }

    public DebtCancellationRequest(Long debtId) {
        this.debtId = debtId;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }
}
