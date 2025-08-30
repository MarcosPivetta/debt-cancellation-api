package com.example.debtcancellation.adapters.in.controller;

import com.example.debtcancellation.adapters.out.repository.DebitoRepository;
import com.example.debtcancellation.adapters.out.repository.entity.DebitoEntity;
import com.example.debtcancellation.application.core.domain.model.StatusDebito;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@Transactional
class CancelarDebitoControllerE2ETest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private DebitoRepository debitoRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private DebitoEntity debitoPendente;
    private DebitoEntity debitoConfirmado;
    private DebitoEntity debitoCancelado;
    private DebitoEntity debitoAntigo;

    @BeforeEach
    void setUp() {
        debitoRepository.deleteAll();

        debitoPendente = new DebitoEntity();
        debitoPendente.setStatus(StatusDebito.PENDENTE);
        debitoPendente.setCriadoEm(Instant.now());
        debitoPendente = debitoRepository.save(debitoPendente);

        debitoConfirmado = new DebitoEntity();
        debitoConfirmado.setStatus(StatusDebito.CONFIRMADO);
        debitoConfirmado.setCriadoEm(Instant.now());
        debitoConfirmado = debitoRepository.save(debitoConfirmado);

        debitoCancelado = new DebitoEntity();
        debitoCancelado.setStatus(StatusDebito.CANCELADO);
        debitoCancelado.setCriadoEm(Instant.now());
        debitoCancelado = debitoRepository.save(debitoCancelado);

        debitoAntigo = new DebitoEntity();
        debitoAntigo.setStatus(StatusDebito.PENDENTE);
        debitoAntigo.setCriadoEm(Instant.now().minus(400, ChronoUnit.DAYS));
        debitoAntigo = debitoRepository.save(debitoAntigo);
    }

    @Test
    void shouldCancelDebtSuccessfully() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", debitoPendente.getIdDebito())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("SUCCESS"))
                .andExpect(jsonPath("$.debtId").value(debitoPendente.getIdDebito()))
                .andExpect(jsonPath("$.message").value("Debt cancellation request received for debt ID: " + debitoPendente.getIdDebito()));
    }

    @Test
    void shouldReturnBadRequestForInvalidDebtId() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", 0)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid Debt ID"))
                .andExpect(jsonPath("$.message").value("Debt ID must be a positive number, but was: 0"));
    }

    @Test
    void shouldReturnBadRequestForNegativeDebtId() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", -1)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid Debt ID"))
                .andExpect(jsonPath("$.message").value("Debt ID must be a positive number, but was: -1"));
    }

    @Test
    void shouldReturnNotFoundForNonExistentDebt() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", 99999)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(404))
                .andExpect(jsonPath("$.error").value("Debt Not Found"))
                .andExpect(jsonPath("$.message").value("Débito não encontrado com id: 99999"));
    }

    @Test
    void shouldReturnBadRequestForTypeMismatch() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", "abc")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value(400))
                .andExpect(jsonPath("$.error").value("Invalid Parameter Type"));
    }

    @Test
    void shouldRejectConfirmedDebt() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", debitoConfirmado.getIdDebito())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("FAILED"))
                .andExpect(jsonPath("$.debtId").value(debitoConfirmado.getIdDebito()))
                .andExpect(jsonPath("$.message").value("Debt cancellation failed for debt ID: " + debitoConfirmado.getIdDebito() + ". Reason: DEBITO_JA_CONFIRMADO"));
    }

    @Test
    void shouldRejectAlreadyCancelledDebt() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", debitoCancelado.getIdDebito())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("FAILED"))
                .andExpect(jsonPath("$.debtId").value(debitoCancelado.getIdDebito()))
                .andExpect(jsonPath("$.message").value("Debt cancellation failed for debt ID: " + debitoCancelado.getIdDebito() + ". Reason: DEBITO_JA_CANCELADO"));
    }

    @Test
    void shouldRejectOldDebt() throws Exception {
        mockMvc.perform(post("/api/v1/debts/{id}", debitoAntigo.getIdDebito())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("FAILED"))
                .andExpect(jsonPath("$.debtId").value(debitoAntigo.getIdDebito()))
                .andExpect(jsonPath("$.message").value("Debt cancellation failed for debt ID: " + debitoAntigo.getIdDebito() + ". Reason: DEBITO_MUITO_ANTIGO"));
    }
}
