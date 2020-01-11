package com.bankapplication.converters;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.dto.BankAccountResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class BankAccountResponseDtoConverter implements Converter<BankAccount, BankAccountResponseDto> {

    @Override
    public BankAccountResponseDto convert(BankAccount bankAccount) {

        BankAccountResponseDto bankAccountResponseDto = new BankAccountResponseDto();

        bankAccountResponseDto.setId(bankAccount.getId());
        bankAccountResponseDto.setAccountNumber(bankAccount.getAccountNumber());
        bankAccountResponseDto.setMoneyAmount(bankAccount.getMoneyAmount());
        bankAccountResponseDto.setCurrency(bankAccount.getCurrency());

        return bankAccountResponseDto;
    }
}
