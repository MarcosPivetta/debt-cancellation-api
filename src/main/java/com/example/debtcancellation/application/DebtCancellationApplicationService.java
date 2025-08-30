package com.example.debtcancellation.application;

import com.example.debtcancellation.domain.DebtCancellation;
import com.example.debtcancellation.port.DebtCancellationUseCase;
import com.example.debtcancellation.port.DebtValidationPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DebtCancellationApplicationService implements DebtCancellationUseCase {
    
    private final DebtValidationPort debtValidationPort;
    
    @Override
    public DebtCancellation cancelDebt(Long debtId) {
        if (!debtValidationPort.isValidForCancellation(debtId)) {
            String reason = debtValidationPort.getValidationFailureReason(debtId);
            return DebtCancellation.createFailed(debtId, reason);
        }
        
        return DebtCancellation.createSuccessful(debtId);
    }
}
