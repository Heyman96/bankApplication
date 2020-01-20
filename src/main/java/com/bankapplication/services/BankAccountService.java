package com.bankapplication.services;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;

import java.util.UUID;

public interface BankAccountService {

    BankAccountResponseDto createAccount(BankAccountRequestDto bankAccountRequestDto, UUID id);

    BankAccountResponseDto putMoney(String accountNumber, BankAccountRequestDto bankAccountRequestDto);

    BankAccountResponseDto sendMoney(String sendingAccountNumber, String gettingAccountNumber, BankAccountRequestDto bankAccountRequestDto);

    BankAccount findByAccountNumber(String accountNumber);

    void deleteById(UUID id);

}
