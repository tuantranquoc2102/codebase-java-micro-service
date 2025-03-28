package com.microservice.authService.dto;

import lombok.*;
import org.apache.hc.core5.http.Message;
import org.springframework.http.HttpStatus;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {
    private HttpStatus httpStatus;
    private String message;
    private Throwable throwable;
    private String errorCode;
}
