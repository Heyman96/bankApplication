package com.bankapplication.dto;

import com.bankapplication.data.domain.Currency;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class BankAccountRequestDto {

    @NotNull
    private BigDecimal moneyAmount;

    @NotNull
    private Currency currency;

}
