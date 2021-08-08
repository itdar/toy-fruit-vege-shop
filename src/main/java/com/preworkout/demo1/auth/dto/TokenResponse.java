package com.preworkout.demo1.auth.dto;

public class TokenResponse {

    private String accessToken;

    protected TokenResponse() {
    }

    public TokenResponse(String token) {
        this.accessToken = token;
    }

    public String getAccessToken() {
        return this.accessToken;
    }
}
