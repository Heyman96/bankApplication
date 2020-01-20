package com.bankapplication.dto;

import com.bankapplication.data.domain.enums.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BankAccountRequestDto {

    @NotNull
    private BigDecimal moneyAmount = new BigDecimal("10000");

    @NotNull
    private Currency currency = Currency.EUR;

}
