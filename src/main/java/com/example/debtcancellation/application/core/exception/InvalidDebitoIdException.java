package com.example.debtcancellation.application.core.exception;

public class InvalidDebitoIdException extends RuntimeException {
    public InvalidDebitoIdException(String message) {
        super(message);
    }
    
    public InvalidDebitoIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
