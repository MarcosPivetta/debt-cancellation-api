package com.example.debtcancellation.adapter;

import com.example.debtcancellation.port.DebtValidationPort;
import org.springframework.stereotype.Component;

@Component
public class DebtValidationAdapter implements DebtValidationPort {
    
    @Override
    public boolean isValidForCancellation(Long debtId) {
        return debtId != null && debtId > 0;
    }
    
    @Override
    public String getValidationFailureReason(Long debtId) {
        if (debtId == null) {
            return "Debt ID cannot be null";
        }
        if (debtId <= 0) {
            return "Debt ID must be a positive number";
        }
        return "Unknown validation error";
    }
}
