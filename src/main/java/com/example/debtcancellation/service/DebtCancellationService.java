package com.example.debtcancellation.service;

import com.example.debtcancellation.dto.DebtCancellationRequest;
import com.example.debtcancellation.dto.DebtCancellationResponse;
import org.springframework.stereotype.Service;

@Service
public class DebtCancellationService {

    public DebtCancellationResponse cancelDebt(DebtCancellationRequest request) {
        Long debtId = request.getDebtId();
        
        return new DebtCancellationResponse(
            "SUCCESS",
            "Debt cancellation request received for debt ID: " + debtId,
            debtId
        );
    }
}
