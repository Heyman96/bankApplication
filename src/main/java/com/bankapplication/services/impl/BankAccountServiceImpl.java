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
import java.util.UUID;

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
    public BankAccountResponseDto createAccount(BankAccountRequestDto bankAccountRequestDto, UUID id) {

        Client client = clientRepository.findById(id).
                orElseThrow(() -> new BankAccountNotFoundException("Client not found!"));

        BankAccount bankAccount = conversionService.convert(bankAccountRequestDto, BankAccount.class);

        String accountNumber = generationService.getAccountNumber();

        bankAccount.setAccountNumber(accountNumber);

        bankAccount.setClient(client);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        client.addBankAccount(savedBankAccount); //FIXME :: FIXED :: when we create 2nd+ account, program will add it to List "BankAccounts"

        return conversionService.convert(savedBankAccount, BankAccountResponseDto.class);

    }

    @Override
    public BankAccountResponseDto putMoney(String accountNumber, BankAccountRequestDto bankAccountRequestDto) {

        BankAccount bankAccount = bankAccountRepository.findByAccountNumber(accountNumber).
                orElseThrow(() -> new BankAccountNotFoundException("Account not found!"));

        BigDecimal newMoneyAmount = bankAccount.getMoneyAmount().add(bankAccountRequestDto.getMoneyAmount());

        bankAccount.setMoneyAmount(newMoneyAmount);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        BankAccountResponseDto bankAccountResponseDto =
                conversionService.convert(savedBankAccount, BankAccountResponseDto.class);

        return bankAccountResponseDto;
    }

    @Override
    public BankAccountResponseDto sendMoney(String sendingAccountNumber, String gettingAccountNumber, BankAccountRequestDto bankAccountRequestDto) {

        BankAccount sendingBankAccount = bankAccountRepository.findByAccountNumber(sendingAccountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Sending account not found!"));
        BankAccount gettingBankAccount = bankAccountRepository.findByAccountNumber(gettingAccountNumber)
                .orElseThrow(() -> new BankAccountNotFoundException("Getting account not found!"));

        BigDecimal sentMoneyAmount = sendingBankAccount.getMoneyAmount().subtract(bankAccountRequestDto.getMoneyAmount());
        BigDecimal gotMoneyAmount = gettingBankAccount.getMoneyAmount().add(bankAccountRequestDto.getMoneyAmount());

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
    public void deleteById(UUID id) {
        bankAccountRepository.deleteById(id);
    }
}
