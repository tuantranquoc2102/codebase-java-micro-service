package org.microservice.dto;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreateNewAccountDto {

    @NotNull
    private String accountName;

    @NotNull
    private String password;

    @NotNull
    private String email;

    @NotNull
    private Integer roleId;
}
