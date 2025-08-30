package com.example.debtcancellation.controller;

import com.example.debtcancellation.dto.DebtCancellationRequest;
import com.example.debtcancellation.dto.DebtCancellationResponse;
import com.example.debtcancellation.service.DebtCancellationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class DebtCancellationController {

    private final DebtCancellationService debtCancellationService;

    @PostMapping("/cancel")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @Valid @RequestBody DebtCancellationRequest request) {
        
        DebtCancellationResponse response = debtCancellationService.cancelDebt(request);
        
        return ResponseEntity.ok(response);
    }
}
