package com.fto.model.dto;

public class IdTokenRequestDto {
    private String idToken;

    public IdTokenRequestDto() {

    }

    public String getIdToken() {
        return idToken;
    }

    public IdTokenRequestDto setIdToken(String idToken) {
        this.idToken = idToken;
        return this;
    }
}
