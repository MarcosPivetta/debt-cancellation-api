package com.example.debtcancellation.adapters.in.controller;

import com.example.debtcancellation.adapters.in.controller.mapper.DebtCancellationResponseMapper;
import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.core.validation.DebitoValidator;
import com.example.debtcancellation.application.ports.in.CancelarDebitoInputPort;
import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/debts")
@RequiredArgsConstructor
@Tag(name = "Debt Cancellation", description = "API para controle de cancelamentos de débitos dos clientes")
public class CancelarDebitoController {

    private final CancelarDebitoInputPort cancelarDebitoInputPort;
    private final DebtCancellationResponseMapper debtCancellationResponseMapper;
    private final DebitoValidator debitoValidator;

    @PostMapping("/{id}")
    @Operation(
        summary = "Cancelar débito",
        description = "Solicita o cancelamento de um débito específico através do seu ID. " +
                     "O débito deve estar com status PENDENTE para ser cancelado."
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Débito cancelado com sucesso"),
        @ApiResponse(responseCode = "400", description = "ID do débito inválido"),
        @ApiResponse(responseCode = "404", description = "Débito não encontrado"),
        @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    public ResponseEntity<DebtCancellationResponse> cancelDebt(
            @Parameter(description = "ID do débito a ser cancelado", required = true, example = "12345")
            @PathVariable("id") Long idDebito) {
        debitoValidator.validateDebitoId(idDebito);
        ResultadoCancelamento resultado = cancelarDebitoInputPort.cancelar(idDebito);
        return ResponseEntity.ok(debtCancellationResponseMapper.toDebtCancellationResponse(resultado));
    }
}
