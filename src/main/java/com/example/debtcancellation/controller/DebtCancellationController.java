package com.example.debtcancellation.controller;

import com.example.debtcancellation.dto.DebtCancellationRequest;
import com.example.debtcancellation.dto.DebtCancellationResponse;
import com.example.debtcancellation.service.DebtCancellationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
public class DebtCancellationController {

    private final DebtCancellationService debtCancellationService;

    @Autowired
    public DebtCancellationController(DebtCancellationService debtCancellationService) {
        this.debtCancellationService = debtCancellationService;
    }

    @PostMapping("/{id}/cancel")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @PathVariable("id") Long debtId,
            @Valid @RequestBody DebtCancellationRequest request) {
        
        request.setDebtId(debtId);
        
        DebtCancellationResponse response = debtCancellationService.cancelDebt(request);
        
        return ResponseEntity.ok(response);
    }
}
