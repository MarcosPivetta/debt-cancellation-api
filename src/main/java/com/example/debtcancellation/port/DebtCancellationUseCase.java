package com.example.debtcancellation.port;

import com.example.debtcancellation.domain.DebtCancellation;

public interface DebtCancellationUseCase {
    
    DebtCancellation cancelDebt(Long debtId);
}
