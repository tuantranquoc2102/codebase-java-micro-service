package com.microservice.authService.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Builder
public class ResponseDto<T> {
    private boolean isSuccess;

    private String message;

    private T data;
}
