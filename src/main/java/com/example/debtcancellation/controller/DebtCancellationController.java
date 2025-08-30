package com.example.debtcancellation.controller;

import com.example.debtcancellation.domain.CancellationStatus;
import com.example.debtcancellation.domain.DebtCancellation;
import com.example.debtcancellation.dto.DebtCancellationRequest;
import com.example.debtcancellation.dto.DebtCancellationResponse;
import com.example.debtcancellation.port.DebtCancellationUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DebtCancellationController {

    private final DebtCancellationUseCase debtCancellationUseCase;

    @PostMapping("/cancel")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @Valid @RequestBody DebtCancellationRequest request) {
        
        DebtCancellation result = debtCancellationUseCase.cancelDebt(request.getDebtId());
        
        DebtCancellationResponse response = mapToResponse(result);
        
        if (result.getStatus() == CancellationStatus.FAILED) {
            return ResponseEntity.badRequest().body(response);
        }
        
        return ResponseEntity.ok(response);
    }
    
    private DebtCancellationResponse mapToResponse(DebtCancellation debtCancellation) {
        return new DebtCancellationResponse(
            debtCancellation.getStatus().name(),
            debtCancellation.getMessage(),
            debtCancellation.getDebtId()
        );
    }
}
