package com.BlackFoxT.smartbook_backend.dto.auth;

import lombok.Getter;

@Getter
public class RegisterRequest {

    private String username;
    private String email;
    private String password;
}
