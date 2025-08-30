package com.example.debtcancellation.adapters.in.controller;

import com.example.debtcancellation.adapters.in.controller.mapper.DebtCancellationRequestMapper;
import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationRequest;
import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class CancelarDebitoController {

    private final CancelarDebitoInputPort cancelarDebitoInputPort;
    private final DebtCancellationRequestMapper debtCancellationRequestMapper;

    @PostMapping("/cancel")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @Valid @RequestBody DebtCancellationRequest request) {
        ResultadoCancelamento resultado = cancelarDebitoInputPort.cancelar(debtCancellationRequestMapper.toDebito(request));
        DebtCancellationResponse response = mapToResponse(resultado);
        return ResponseEntity.ok(response);
    }
    
    private DebtCancellationResponse mapToResponse(ResultadoCancelamento resultado) {
        String status = resultado.cancelado() ? "SUCCESS" : "FAILED";
        String message = resultado.cancelado() ? 
            "Debt cancellation request received for debt ID: " + resultado.idDebito() :
            "Debt cancellation failed for debt ID: " + resultado.idDebito() + ". Reason: " + resultado.motivo();
        
        return new DebtCancellationResponse(status, message, resultado.idDebito());
    }
}
