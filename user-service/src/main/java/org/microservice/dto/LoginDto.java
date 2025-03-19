package org.microservice.dto;

import lombok.*;




@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class LoginDto  {
    private String accountName;
    private String password;
}
