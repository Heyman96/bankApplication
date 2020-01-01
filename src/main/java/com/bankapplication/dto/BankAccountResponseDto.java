package com.bankapplication.dto;

import com.bankapplication.data.domain.Currency;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class BankAccountResponseDto {

    private Long id;
    private String accountNumber;
    private BigDecimal moneyAmount;
    private Currency currency;

}
