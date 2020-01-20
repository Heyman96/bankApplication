package com.bankapplication.converters;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.data.domain.Client;
import com.bankapplication.dto.BankAccountRequestDto;
import com.bankapplication.dto.ClientRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import static java.util.Objects.nonNull;

@Component
public class ClientRequestDtoConverter implements Converter<ClientRequestDto, Client> {


    @Autowired
    private BankAccountRequestDtoConverter bankAccountRequestDtoConverter;

    @Override
    public Client convert(ClientRequestDto clientRequestDto) {

        Client client = new Client();

        client.setFirstName(clientRequestDto.getFirstName());
        client.setLastName(clientRequestDto.getLastName());
        client.setPatronymic(clientRequestDto.getPatronymic());
        client.setBirthDate(clientRequestDto.getBirthDate());
        client.setPhoneNumber(clientRequestDto.getPhoneNumber());
        client.setEmail(clientRequestDto.getEmail());

        BankAccount bankAccount = new BankAccount();
        BankAccountRequestDto bankAccountRequestDto = clientRequestDto.getBankAccount();
        if (nonNull(bankAccountRequestDto)) {
            bankAccount = bankAccountRequestDtoConverter.convert(bankAccountRequestDto);
        }

        client.addBankAccount(bankAccount);

        return client;
    }
}
