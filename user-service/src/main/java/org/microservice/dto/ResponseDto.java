package org.microservice.dto;

import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
public class ResponseDto<T>  {

    private boolean isSuccess;

    private String message;

    private T data;
}
