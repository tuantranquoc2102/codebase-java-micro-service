package org.microservice.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ErrorDto {
    private String message;
    private Throwable throwable;
}
