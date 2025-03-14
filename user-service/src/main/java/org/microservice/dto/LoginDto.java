package org.microservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class LoginDto implements Serializable  {
    private String accountName;
    private String password;
}
