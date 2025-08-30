package com.example.debtcancellation.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtCancellationRequest {

    @NotNull(message = "Debt ID cannot be null")
    @Positive(message = "Debt ID must be a positive number")
    private Long debtId;
}
