package com.bankapplication.services.impl;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.data.domain.Client;
import com.bankapplication.data.repository.BankAccountRepository;
import com.bankapplication.data.repository.ClientRepository;
import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.exceptions.BankAccountNotFoundException;
import com.bankapplication.services.BankAccountService;
import com.bankapplication.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class BankAccountServiceImpl implements BankAccountService {

    @Autowired
    private ConversionService conversionService;
    @Autowired
    private BankAccountRepository bankAccountRepository;
    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private GenerationService generationService;

    @Override
    public BankAccount createDefaultAccount(BankAccountRequestDto bankAccountRequestDto) {

        BankAccount bankAccount =
                conversionService.convert(bankAccountRequestDto, BankAccount.class);

        String accountNumber = generationService.getAccountNumber();

        bankAccount.setAccountNumber(accountNumber);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return savedBankAccount;

    }

    @Override
    public BankAccountResponseDto createAccount(BankAccountRequestDto bankAccountRequestDto, Long id) {

        BankAccount bankAccount =
                conversionService.convert(bankAccountRequestDto, BankAccount.class);

        String accountNumber = generationService.getAccountNumber();

        bankAccount.setAccountNumber(accountNumber);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        Client client = clientRepository.findById(id).
                orElseThrow(() -> new BankAccountNotFoundException("Client not found!"));

        client.setBankAccount(savedBankAccount);

        BankAccountResponseDto bankAccountResponseDto =
                conversionService.convert(savedBankAccount, BankAccountResponseDto.class);

        return bankAccountResponseDto;

    }

    @Override
    public BankAccountResponseDto putMoney(String accountNumber, BigDecimal addingMoneyAmount) {

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new BankAccountNotFoundException("Account not found!"));

        BigDecimal newMoneyAmount = bankAccount.getMoneyAmount().add(addingMoneyAmount);

        bankAccount.setMoneyAmount(newMoneyAmount);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDto bankAccountResponseDto =
                conversionService.convert(savedBankAccount, BankAccountResponseDto.class);

        return bankAccountResponseDto;
    }

    @Override
    public BankAccountResponseDto getMoney(String accountNumber, BigDecimal gettingMoneyAmount) {

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found!"));

        BigDecimal newMoneyAmount = bankAccount.getMoneyAmount().subtract(gettingMoneyAmount);

        bankAccount.setMoneyAmount(newMoneyAmount);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDto bankAccountResponseDto =
                conversionService.convert(savedBankAccount, BankAccountResponseDto.class);

        return bankAccountResponseDto;

    }

    @Override
    public BankAccountResponseDto sendMoney(String accountNumber, BigDecimal sendingMoneyAmount) {

        BankAccount sendingBankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Sending account not found!"));
        BankAccount gettingBankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Getting account not found!"));

        BigDecimal sentMoneyAmount = sendingBankAccount.getMoneyAmount().subtract(sendingMoneyAmount);
        BigDecimal gotMoneyAmount = gettingBankAccount.getMoneyAmount().add(sendingMoneyAmount);

        sendingBankAccount.setMoneyAmount(sentMoneyAmount);
        gettingBankAccount.setMoneyAmount(gotMoneyAmount);

        BankAccount savedSendingBankAccount = bankAccountRepository.save(sendingBankAccount);
        BankAccount savedGettingBankAccount = bankAccountRepository.save(gettingBankAccount);

        BankAccountResponseDto bankAccountResponseDto =
                conversionService.convert(savedSendingBankAccount, BankAccountResponseDto.class);

        return bankAccountResponseDto;
    }

    @Override
    public BankAccount findByAccountNumber(String accountNumber) {

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Account not found!"));

        return bankAccount;
    }

    @Override
    public void deleteById(Long id) {
        bankAccountRepository.deleteById(id);
    }
}
