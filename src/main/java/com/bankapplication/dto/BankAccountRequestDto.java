package com.bankapplication.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;

@Data
public class BankAccountRequestDto {

    @NotBlank
    private BigDecimal moneyAmount;

    @NotBlank
    private String currency;

}
