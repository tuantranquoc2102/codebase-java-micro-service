package org.microservice.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@NoArgsConstructor
@Setter
@Getter
public class ResponseDto<T> implements Serializable {

    private boolean isSuccess;

    private String message;

    private T data;
}
