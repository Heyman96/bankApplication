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
    public BankAccountResponseDto createAccount(BankAccountRequestDto bankAccountRequestDto, Long id) {

        Client client = clientRepository.findById(id).
                orElseThrow(() -> new BankAccountNotFoundException("Client not found!")); // at first we have to ensure that client with given id exists
                                                                                          // especially it's has no sense to check client existence after saving account
        String accountNumber = generationService.getAccountNumber();

        BankAccount bankAccount = conversionService.convert(bankAccountRequestDto, BankAccount.class);
        bankAccount.setAccountNumber(accountNumber);
        bankAccount.setClient(client);

        BankAccount savedBankAccount = bankAccountRepository.save(bankAccount);

        return conversionService.convert(savedBankAccount, BankAccountResponseDto.class);
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
    // TODO: ???
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
    // FIXME: it's not working as expected, need to know account number send from and account number send to
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
    // TODO: ???
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
