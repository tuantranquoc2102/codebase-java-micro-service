package org.microservice.exceptions;

public class BusinessException extends RuntimeException {
    private String message;

    public BusinessException() {
        super();
    }

    public BusinessException(Throwable throwable) {
        super(throwable);
    }

    public BusinessException(String message) {
        super(message);
        this.message = message;
    }
}
