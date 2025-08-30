package com.example.debtcancellation.application.ports.in;

import com.example.debtcancellation.application.core.domain.ResultadoCancelamento;
import com.example.debtcancellation.application.core.domain.model.Debito;

public interface CancelarDebitoInputPort {
    ResultadoCancelamento cancelar(Debito debito);
}
