package com.microservice.authService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class LoginResponse {
    private boolean isSuccess;
    private String message;
    private AuthResponse authResponse;
    private UserVO data;
}
