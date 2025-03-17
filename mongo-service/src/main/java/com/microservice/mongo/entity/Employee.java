package com.microservice.mongo.entity;


import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Setter
@Getter
@Document(value = "employee")
public class Employee {
    @org.springframework.data.annotation.Id
    private long id;

    private String empName;

    private boolean gender;

    private Date bod;

    private String email;

    private String phoneNumber;
}
