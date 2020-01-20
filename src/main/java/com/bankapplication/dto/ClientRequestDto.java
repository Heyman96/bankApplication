package com.bankapplication.dto;

import lombok.Data;

import javax.validation.Valid;
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

    @Past //Переменная под такой аннотацией, может быть инициализирована исключительно датой из прошлого или относительно недавнего времени. БУДУЩИЕ ДАТЫ НЕ ДОПУСКАЮТСЯ.
    private LocalDate birthDate;

    @Pattern(regexp = "\\(?([0-9]{3})\\)?([ ]?)([0-9]{3})?([ ]?)([0-9]{4})") // (123) 456 7899
    @NotBlank
    private String phoneNumber;
    
    @Email
    private String email;

    @Valid
    private BankAccountRequestDto bankAccount;

}
