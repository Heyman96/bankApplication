package com.bankapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
public class ClientRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String patronymic;

    @NotBlank
    private LocalDate birthDate;

    @NotBlank
    private String phoneNumber;

    @NotBlank
    private String email;

    @NotBlank
    private BankAccountRequestDto bankAccount;

}
