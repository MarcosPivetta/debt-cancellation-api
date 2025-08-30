package com.example.debtcancellation.adapters.in.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtCancellationResponse {

    private String status;
    private String message;
    private Long debtId;
}
