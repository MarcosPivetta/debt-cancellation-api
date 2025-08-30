package com.example.debtcancellation.application.ports.in;

import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;

public interface CancelarDebitoInputPort {
    ResultadoCancelamento cancelar(Long debito);
}
