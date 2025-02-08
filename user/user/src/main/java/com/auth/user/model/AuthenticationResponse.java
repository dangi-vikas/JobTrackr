package com.auth.user.model;

import lombok.Data;

@Data
public class AuthenticationResponse {
    private String jwt;

    public AuthenticationResponse(String jwt) {
        this.jwt = jwt;
    }
}