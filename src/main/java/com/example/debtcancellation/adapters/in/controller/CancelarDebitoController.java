package com.example.debtcancellation.adapters.in.controller;

import com.example.debtcancellation.adapters.in.controller.mapper.DebtCancellationRequestMapper;
import com.example.debtcancellation.adapters.in.controller.mapper.DebtCancellationResponseMapper;
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
    private final DebtCancellationResponseMapper debtCancellationResponseMapper;

    @PostMapping("/cancel")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @Valid @RequestBody DebtCancellationRequest request) {
        ResultadoCancelamento resultado = cancelarDebitoInputPort.cancelar(debtCancellationRequestMapper.toDebito(request));
        DebtCancellationResponse response = debtCancellationResponseMapper.toDebtCancellationResponse(resultado);
        return ResponseEntity.ok(response);
    }
}
