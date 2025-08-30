package com.example.debtcancellation.application.core.domain.model;

public record DecisaoCancelamento(
    boolean status,
    String motivo
) {
    public static DecisaoCancelamento aprovado() { return new DecisaoCancelamento(true, "APROVADO"); }
    public static DecisaoCancelamento negado(String motivo) { return new DecisaoCancelamento(false, motivo); }
}
