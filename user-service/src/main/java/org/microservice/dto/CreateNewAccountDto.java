package org.microservice.dto;

import jakarta.persistence.Entity;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class CreateNewAccountDto implements Serializable {
    private String accountName;
    private String password;
    private String email;
}
