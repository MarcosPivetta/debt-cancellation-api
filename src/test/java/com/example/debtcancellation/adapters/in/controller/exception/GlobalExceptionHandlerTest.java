package com.example.debtcancellation.adapters.in.controller.exception;

import com.example.debtcancellation.application.core.exception.DebitoNotFoundException;
import com.example.debtcancellation.application.core.exception.InvalidDebitoIdException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class GlobalExceptionHandlerTest {

    private GlobalExceptionHandler globalExceptionHandler;

    @BeforeEach
    void setUp() {
        globalExceptionHandler = new GlobalExceptionHandler();
    }

    @Test
    void shouldHandleDebitoNotFoundException() {
        DebitoNotFoundException exception = new DebitoNotFoundException("Débito não encontrado com id: 123");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleDebitoNotFound(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(404);
        assertThat(response.getBody().getError()).isEqualTo("Debt Not Found");
        assertThat(response.getBody().getMessage()).isEqualTo("Débito não encontrado com id: 123");
        assertThat(response.getBody().getPath()).isEqualTo("/api/v1/debts");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void shouldHandleInvalidDebitoIdException() {
        InvalidDebitoIdException exception = new InvalidDebitoIdException("Debt ID must be positive");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleInvalidDebitoId(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getError()).isEqualTo("Invalid Debt ID");
        assertThat(response.getBody().getMessage()).isEqualTo("Debt ID must be positive");
        assertThat(response.getBody().getPath()).isEqualTo("/api/v1/debts");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void shouldHandleMethodArgumentTypeMismatchException() {
        MethodArgumentTypeMismatchException exception = mock(MethodArgumentTypeMismatchException.class);
        when(exception.getValue()).thenReturn("abc");
        when(exception.getName()).thenReturn("id");
        when(exception.getRequiredType()).thenReturn((Class) Long.class);

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleTypeMismatch(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(400);
        assertThat(response.getBody().getError()).isEqualTo("Invalid Parameter Type");
        assertThat(response.getBody().getMessage()).contains("Invalid value 'abc' for parameter 'id'");
        assertThat(response.getBody().getPath()).isEqualTo("/api/v1/debts");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }

    @Test
    void shouldHandleGenericException() {
        Exception exception = new RuntimeException("Unexpected error");

        ResponseEntity<ErrorResponse> response = globalExceptionHandler.handleGenericException(exception);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.INTERNAL_SERVER_ERROR);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().getStatus()).isEqualTo(500);
        assertThat(response.getBody().getError()).isEqualTo("Internal Server Error");
        assertThat(response.getBody().getMessage()).isEqualTo("An unexpected error occurred");
        assertThat(response.getBody().getPath()).isEqualTo("/api/v1/debts");
        assertThat(response.getBody().getTimestamp()).isNotNull();
    }
}
