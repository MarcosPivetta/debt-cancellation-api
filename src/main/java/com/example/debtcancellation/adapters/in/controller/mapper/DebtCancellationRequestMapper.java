package com.example.debtcancellation.adapters.in.controller.mapper;

import com.example.debtcancellation.adapters.in.controller.dto.DebtCancellationRequest;
import com.example.debtcancellation.application.core.domain.model.Debito;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.time.Instant;

@Mapper(componentModel = "spring")
public interface DebtCancellationRequestMapper {

    @Mapping(target = "idDebito", source = "debtId")
    @Mapping(target = "status", constant = "PENDENTE")
    @Mapping(target = "criadoEm", expression = "java(java.time.Instant.now())")
    Debito toDebito(DebtCancellationRequest request);
}
