package com.microservice.authService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthResponse {
    private String accessToken;
    private String refreshToken;
}
