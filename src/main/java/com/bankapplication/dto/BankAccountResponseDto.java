package com.bankapplication.dto;

import com.bankapplication.data.domain.enums.Currency;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class BankAccountResponseDto {

    private UUID id;
    
    private String accountNumber;

    private BigDecimal moneyAmount;

    private Currency currency;

}
