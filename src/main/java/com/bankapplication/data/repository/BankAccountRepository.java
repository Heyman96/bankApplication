package com.bankapplication.data.repository;

import com.bankapplication.data.domain.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {

    Optional<BankAccount> findByAccountNumber(String accountNumber);

    BankAccount findByCurrency(String currency);

}
