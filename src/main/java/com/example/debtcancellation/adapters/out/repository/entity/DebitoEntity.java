package com.example.debtcancellation.adapters.out.repository.entity;

import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import java.time.Instant;

@Data
@Entity
public class DebitoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String idDebito;
    private StatusDebito status;
    private Instant criadoEm;
}
