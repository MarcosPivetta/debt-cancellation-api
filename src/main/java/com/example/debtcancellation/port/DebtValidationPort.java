package com.example.debtcancellation.port;

public interface DebtValidationPort {
    
    boolean isValidForCancellation(Long debtId);
    
    String getValidationFailureReason(Long debtId);
}
