package com.bankapplication.services;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;

import java.math.BigDecimal;

public interface BankAccountService {

    BankAccountResponseDto createAccount(BankAccountRequestDto bankAccountRequestDto, Long id);

    BankAccountResponseDto putMoney(String accountNumber, BigDecimal addingMoneyAmount);

    BankAccountResponseDto getMoney(String accountNumber, BigDecimal gettingMoneyAmount);

    BankAccountResponseDto sendMoney(String accountNumber, BigDecimal sendingMoneyAmount);

    BankAccount findByAccountNumber(String accountNumber);

    void deleteById(Long id);

}
