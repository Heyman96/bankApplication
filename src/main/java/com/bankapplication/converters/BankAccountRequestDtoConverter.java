package com.bankapplication.converters;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.data.domain.Currency;
import com.bankapplication.dto.BankAccountRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

import static java.util.Objects.nonNull;

@Component
public class BankAccountRequestDtoConverter implements Converter<BankAccountRequestDto, BankAccount>{ //Converter<BankAccountRequestDto, BankAccount>

    @Override
    public BankAccount convert(BankAccountRequestDto bankAccountRequestDto) {

        BankAccount bankAccount = new BankAccount();

        BigDecimal moneyAmount = bankAccountRequestDto.getMoneyAmount();
        Currency currency = bankAccountRequestDto.getCurrency();

        if (nonNull(moneyAmount)) {
            bankAccount.setMoneyAmount(moneyAmount);
        }
        if (nonNull(currency)) {
            bankAccount.setCurrency(currency);
        }

        return bankAccount;
    }



}
