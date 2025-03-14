package com.microservice.authService.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequest {
    private String accountName;
    private String password;
    private String email;
}
