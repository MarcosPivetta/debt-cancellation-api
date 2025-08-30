package com.example.debtcancellation.application.core.domain.model;

import java.time.Instant;

public record Debito(
    Long idDebito,
    StatusDebito status,
    Instant criadoEm
) {}
