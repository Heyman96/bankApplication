package com.bankapplication.controllers;

import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    // Account mappings

    @PostMapping("{clientId}/accounts")
    public BankAccountResponseDto createAccount(@RequestBody @Valid BankAccountRequestDto bankAccountRequestDto, @PathVariable Long clientId) {
        return bankAccountService.createAccount(bankAccountRequestDto, clientId);
    }

    @DeleteMapping("accounts/{accountId}")
    public void deleteAccount(@PathVariable Long accountId) {
        bankAccountService.deleteById(accountId);
    }


    //Money mappings

    @PatchMapping("accounts/{accountNumber}")
    public BankAccountResponseDto putMoney(@PathVariable String accountNumber, @RequestBody BigDecimal addingMoneyAmount) {
        return bankAccountService.putMoney(accountNumber, addingMoneyAmount);
    }

    // FIXME: it doesn't work as expected, need to know account number send from and account number send to
    @PutMapping("sendMoneyTo/{accountNumber}")
    public BankAccountResponseDto sendMoney(@PathVariable String accountNumber, @RequestBody BigDecimal sendingMoneyAmount) {
        return bankAccountService.sendMoney(accountNumber, sendingMoneyAmount);
    }

}
