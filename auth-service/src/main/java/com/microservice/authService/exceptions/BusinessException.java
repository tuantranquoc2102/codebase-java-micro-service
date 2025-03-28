package com.microservice.authService.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BusinessException extends RuntimeException {

    private String message;

    public BusinessException() {}

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
