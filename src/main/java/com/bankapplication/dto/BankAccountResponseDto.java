package com.bankapplication.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountResponseDto {

    private String accountNumber;

    private BigDecimal moneyAmount;

    private String currency;

}
