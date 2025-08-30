package com.example.debtcancellation.adapters.in.controller.mapper;

import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationResponse;
import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface DebtCancellationResponseMapper {

    @Mapping(target = "status", expression = "java(resultado.cancelado() ? \"SUCCESS\" : \"FAILED\")")
    @Mapping(target = "message", expression = "java(resultado.cancelado() ? \"Debt cancellation request received for debt ID: \" + resultado.idDebito() : \"Debt cancellation failed for debt ID: \" + resultado.idDebito() + \". Reason: \" + resultado.motivo())")
    @Mapping(target = "debtId", source = "idDebito")
    DebtCancellationResponse toDebtCancellationResponse(ResultadoCancelamento resultado);
}
