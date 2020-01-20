package com.bankapplication.controllers;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.services.BankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    // Account mappings

    @PostMapping("client/{id}") //FIXME: doesn't want to read "?" in endpoints  (1)
    public BankAccountResponseDto createAccount(@RequestBody @Valid BankAccountRequestDto bankAccountRequestDto, @PathVariable UUID id) {
        return bankAccountService.createAccount(bankAccountRequestDto, id);
    }

    @GetMapping("?accountNumber={accountNumber}")
    public BankAccount findByAccountNumber(@PathVariable String accountNumber) {
        return bankAccountService.findByAccountNumber(accountNumber);
    }

    @DeleteMapping("account/{id}")
    public void deleteAccount(@PathVariable UUID id) {
        bankAccountService.deleteById(id);
    }

    //Money mappings

    @PatchMapping("placing/accountNumber={accountNumber}") //FIXME: doesn't want to read "?" in endpoints  (2)
    public BankAccountResponseDto putMoney(@PathVariable String accountNumber, @RequestBody @Valid BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.putMoney(accountNumber, bankAccountRequestDto);
    }

    @PutMapping("sending/{sendingAccountNumber}/{gettingAccountNumber}")
    public BankAccountResponseDto sendMoney(@PathVariable String sendingAccountNumber, @PathVariable String gettingAccountNumber, @RequestBody @Valid BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.sendMoney(sendingAccountNumber, gettingAccountNumber, bankAccountRequestDto);
    }

}
