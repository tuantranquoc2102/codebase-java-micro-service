package com.microservice.authService.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class AuthRequest {
    @NotNull(message = "Account Name is required")
    private String accountName;

    @NotNull(message = "Password is required")
    private String password;

    @NotNull(message = "Email is requried")
    private String email;

    @NotNull(message = "Role Id is requried")
    private Integer roleId;
}
