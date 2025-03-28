package com.microservice.authService.services;

import com.microservice.authService.dto.*;
import org.springframework.http.ResponseEntity;

public interface IAuthService {
    ResponseDto<AuthResponse> register(AuthRequest authRequest);

    LoginResponse loginSystem(LoginRequest loginRequest);
}
