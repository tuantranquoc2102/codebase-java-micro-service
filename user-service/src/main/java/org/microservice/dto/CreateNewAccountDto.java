package org.microservice.dto;


import lombok.*;



@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreateNewAccountDto {
    private String accountName;
    private String password;
    private String email;
}
