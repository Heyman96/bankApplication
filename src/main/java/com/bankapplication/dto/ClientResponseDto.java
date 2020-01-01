package com.bankapplication.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ClientResponseDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String patronymic;
    private LocalDate birthDate;
    private String phoneNumber;
    private String email;
    private List<BankAccountResponseDto> bankAccounts;

}
