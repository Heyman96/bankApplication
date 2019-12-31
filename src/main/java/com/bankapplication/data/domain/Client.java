package com.bankapplication.data.domain;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    private String patronymic;

    @Column(nullable = false)
    private LocalDate birthDate;

    @Column(nullable = false)
    private String phoneNumber;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn()
    private BankAccount bankAccount;

}
