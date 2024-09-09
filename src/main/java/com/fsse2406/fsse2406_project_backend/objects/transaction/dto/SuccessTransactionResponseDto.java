package com.fsse2406.fsse2406_project_backend.objects.transaction.dto;

public class SuccessTransactionResponseDto {
    private String result="SUCCESS";

    public SuccessTransactionResponseDto() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
