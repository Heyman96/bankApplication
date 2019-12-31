package com.bankapplication.services.impl;

import com.bankapplication.services.GenerationService;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class GenerationServiceImpl implements GenerationService {

    @Override
    public String getPassword() {

        String password;
        do {
            password = RandomStringUtils.randomAlphanumeric(8);
        } while (!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9]).{8,}$"));

        return password;
    }

    @Override
    public String getAccountNumber() {

        String accountNumber;

        do {
            accountNumber = RandomStringUtils.randomNumeric(20);
        } while (!accountNumber.matches("(?=.*?[0-9]).{20,}$"));

        return accountNumber;

    }
}
