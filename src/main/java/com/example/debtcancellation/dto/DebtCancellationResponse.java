package com.example.debtcancellation.dto;

public class DebtCancellationResponse {

    private String status;
    private String message;
    private Long debtId;

    public DebtCancellationResponse() {
    }

    public DebtCancellationResponse(String status, String message, Long debtId) {
        this.status = status;
        this.message = message;
        this.debtId = debtId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Long getDebtId() {
        return debtId;
    }

    public void setDebtId(Long debtId) {
        this.debtId = debtId;
    }
}
