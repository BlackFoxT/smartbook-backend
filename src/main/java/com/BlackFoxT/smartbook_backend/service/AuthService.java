package com.BlackFoxT.smartbook_backend.service;

import com.BlackFoxT.smartbook_backend.dto.auth.AuthResponse;
import com.BlackFoxT.smartbook_backend.dto.auth.LoginRequest;
import com.BlackFoxT.smartbook_backend.dto.auth.RegisterRequest;

public interface AuthService {

    AuthResponse register(RegisterRequest request);

    AuthResponse login(LoginRequest request);
}
