package com.bankapplication.converters;

import com.bankapplication.data.domain.Client;
import com.bankapplication.dto.ClientResponseDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ClientResponseDtoConverter implements Converter<Client, ClientResponseDto> {

    @Override
    public ClientResponseDto convert(Client client) {

        ClientResponseDto clientResponseDto = new ClientResponseDto();

        clientResponseDto.setFirstName(client.getFirstName());
        clientResponseDto.setLastName(client.getLastName());
        clientResponseDto.setPatronymic(client.getPatronymic());
        clientResponseDto.setBirthDate(client.getBirthDate());
        clientResponseDto.setBankAccount(client.getBankAccount());
        clientResponseDto.setEmail(client.getEmail());

        return clientResponseDto;
    }
}
