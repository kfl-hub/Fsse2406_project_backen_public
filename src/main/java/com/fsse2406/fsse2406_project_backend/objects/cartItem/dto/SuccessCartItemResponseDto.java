package com.fsse2406.fsse2406_project_backend.objects.cartItem.dto;

public class SuccessCartItemResponseDto {
    private String result="SUCCESS";

    public SuccessCartItemResponseDto() {
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
