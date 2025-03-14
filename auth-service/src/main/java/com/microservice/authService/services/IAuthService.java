package com.microservice.authService.services;

import com.microservice.authService.dto.AuthRequest;
import com.microservice.authService.dto.AuthResponse;

public interface IAuthService {
    AuthResponse register(AuthRequest authRequest);
}
