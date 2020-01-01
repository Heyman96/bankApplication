package com.bankapplication.dto;

import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class ClientRequestDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    private String patronymic;

    @Past
    private LocalDate birthDate;

    @Pattern(regexp = "\\(?([0-9]{3})\\)?([ ]?)([0-9]{3})\\2([0-9]{4})") // (123) 456 7899
    @NotBlank
    private String phoneNumber;

    @Email
    private String email;

    private BankAccountRequestDto bankAccount;

}
