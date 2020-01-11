package com.bankapplication.converters;

import com.bankapplication.data.domain.Client;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.dto.ClientResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
public class ClientResponseDtoConverter implements Converter<Client, ClientResponseDto> {

    @Autowired
    private BankAccountResponseDtoConverter bankAccountResponseDtoConverter;

    @Override
    public ClientResponseDto convert(Client client) {

        ClientResponseDto clientResponseDto = new ClientResponseDto();

        clientResponseDto.setId(client.getId());
        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setLastName(client.getLastName());
        clientResponseDto.setPatronymic(client.getPatronymic());
        clientResponseDto.setBirthDate(client.getBirthDate());
        clientResponseDto.setEmail(client.getEmail());

        List<BankAccountResponseDto> accountDtos = client.getBankAccounts()
                .stream()
                .map(bankAccountResponseDtoConverter::convert)
                .collect(toList());

        clientResponseDto.setBankAccounts(accountDtos);

        return clientResponseDto;
    }
}
