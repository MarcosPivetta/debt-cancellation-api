package com.example.debtcancellation.application.core.exception;

public class DebitoNotFoundException extends RuntimeException {
    public DebitoNotFoundException(String message) {
        super(message);
    }
    
    public DebitoNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
