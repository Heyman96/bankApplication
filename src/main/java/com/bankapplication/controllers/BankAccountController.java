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
@RequestMapping("clients/accounts")
public class BankAccountController {

    private final BankAccountService bankAccountService;

    // Account mappings

    @PostMapping
    public BankAccountResponseDto createAccount(@RequestBody @Valid BankAccountRequestDto bankAccountRequestDto, @PathVariable UUID id) {
        return bankAccountService.createAccount(bankAccountRequestDto, id);
    }

    @GetMapping
    public BankAccount findByAccountNumber(@RequestParam String accountNumber) {
        return bankAccountService.findByAccountNumber(accountNumber);
    }

    @DeleteMapping("{id}")
    public void deleteAccount(@PathVariable UUID id) {
        bankAccountService.deleteById(id);
    }

    //Money mappings

    @PatchMapping("{accountNumber}")
    public BankAccountResponseDto putMoney(@PathVariable String accountNumber, @RequestBody @Valid BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.putMoney(accountNumber, bankAccountRequestDto);
    }

    @PutMapping("{sendingAccountNumber}/{gettingAccountNumber}")
    public BankAccountResponseDto sendMoney(@PathVariable String sendingAccountNumber, @PathVariable String gettingAccountNumber, @RequestBody @Valid BankAccountRequestDto bankAccountRequestDto) {
        return bankAccountService.sendMoney(sendingAccountNumber, gettingAccountNumber, bankAccountRequestDto);
    }

}
