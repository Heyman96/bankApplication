package com.bankapplication.converters;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.dto.BankAccountRequestDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BankAccountRequestDtoConverter implements Converter<BankAccountRequestDto, BankAccount>{ //Converter<BankAccountRequestDto, BankAccount>

    @Override
    public BankAccount convert(BankAccountRequestDto bankAccountRequestDto) {

        BankAccount bankAccount = new BankAccount();

        bankAccount.setMoneyAmount(bankAccountRequestDto.getMoneyAmount());
        bankAccount.setCurrency(bankAccountRequestDto.getCurrency());

        return bankAccount;
    }



}
