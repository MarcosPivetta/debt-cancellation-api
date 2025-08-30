package com.example.debtcancellation.adapters.in.controller;

import com.example.debtcancellation.adapters.in.controller.mapper.DebtCancellationResponseMapper;
import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
public class CancelarDebitoController {

    private final CancelarDebitoInputPort cancelarDebitoInputPort;
    private final DebtCancellationResponseMapper debtCancellationResponseMapper;

    @PostMapping("/{id}")
    public ResponseEntity<DebtCancellationResponse> cancelDebt(@PathVariable("id") Long idDebito) {
        ResultadoCancelamento resultado = cancelarDebitoInputPort.cancelar(idDebito);
        return ResponseEntity.ok(debtCancellationResponseMapper.toDebtCancellationResponse(resultado));
    }
}
