package com.bankapplication.controllers;

import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequiredArgsConstructor
public class BankAccountController {

    private final BankAccountService bankAccountService;

    // Account mappings

    @PostMapping("user/{id}/createAccount") //TODO: Доделать реализацию. Везде BankAccount заменить на List<BankAccount>, что бы у юзера могло быть несколько счетов.
    public BankAccountResponseDto createAccount(@RequestBody BankAccountRequestDto bankAccountRequestDto, @PathVariable Long id) {
        return bankAccountService.createAccount(bankAccountRequestDto, id);
    }

    @DeleteMapping("deleteAccount/{id}")
    public void deleteAccount(@PathVariable Long id) {
        bankAccountService.deleteById(id);
    }


    //Money mappings

    @PutMapping("putMoney/{accountNumber}")
    public BankAccountResponseDto putMoney(@PathVariable String accountNumber, @RequestBody BigDecimal addingMoneyAmount) {
        return bankAccountService.putMoney(accountNumber, addingMoneyAmount);
    }

    @PutMapping("sendMoneyTo/{accountNumber}")
    public BankAccountResponseDto sendMoney(@PathVariable String accountNumber, @RequestBody BigDecimal sendingMoneyAmount) {
        return bankAccountService.sendMoney(accountNumber, sendingMoneyAmount);
    }

}
