package com.example.debtcancellation.dto;

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
