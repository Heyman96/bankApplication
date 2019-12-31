package com.bankapplication.dto;

import com.bankapplication.data.domain.BankAccount;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ClientResponseDto {

    private String firstName;

    private String lastName;

    private String patronymic;

    private LocalDate birthDate;

    private String phoneNumber;

    private String email;

    private BankAccount bankAccount;

}
