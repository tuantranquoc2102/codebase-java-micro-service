package com.microservice.authService.services;

import com.microservice.authService.dto.AuthRequest;
import com.microservice.authService.dto.AuthResponse;
import com.microservice.authService.dto.LoginRequest;
import com.microservice.authService.dto.LoginResponse;

public interface IAuthService {
    AuthResponse register(AuthRequest authRequest);

    LoginResponse loginSystem(LoginRequest loginRequest);
}
