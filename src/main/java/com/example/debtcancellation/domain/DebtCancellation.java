package com.example.debtcancellation.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebtCancellation {
    
    private Long debtId;
    private CancellationStatus status;
    private String message;
    
    public static DebtCancellation createSuccessful(Long debtId) {
        return new DebtCancellation(
            debtId,
            CancellationStatus.SUCCESS,
            "Debt cancellation request received for debt ID: " + debtId
        );
    }
    
    public static DebtCancellation createFailed(Long debtId, String reason) {
        return new DebtCancellation(
            debtId,
            CancellationStatus.FAILED,
            "Debt cancellation failed for debt ID: " + debtId + ". Reason: " + reason
        );
    }
}
