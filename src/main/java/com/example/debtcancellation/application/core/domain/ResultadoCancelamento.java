package com.example.debtcancellation.application.core.domain;

public record ResultadoCancelamento(
    Long idDebito,
    boolean cancelado,
    String motivo
) {}
