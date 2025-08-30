package com.example.debtcancellation.adapters.out.repository.mapper;

import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.core.domain.model.Debito;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DebitoEntityMapper {

    DebitoEntity toEntity(Debito debito);

    Debito toDebito(DebitoEntity debitoEntity);
}
