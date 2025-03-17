package com.microservice.authService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Setter
@Getter
public class LoginRequest {
    private String accountName;
    private String password;
}
