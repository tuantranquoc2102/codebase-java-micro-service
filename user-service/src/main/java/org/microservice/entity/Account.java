package org.microservice.entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "account")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "accountName")
    private String accountName;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;
}
