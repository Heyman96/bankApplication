package com.bankapplication.services.impl;

import com.bankapplication.data.domain.Client;
import com.bankapplication.data.repository.ClientRepository;
import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;
import com.bankapplication.services.ClientService;
import com.bankapplication.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private GenerationService generationService;

    @Autowired
    private ConversionService conversionService;

    @Autowired
    private ClientRepository clientRepository;

    @Override
    public ClientResponseDto createClient(ClientRequestDto clientRequestDto) {

        Client client = conversionService.convert(clientRequestDto, Client.class);

        String password = generationService.getPassword();
        String accountNumber = generationService.getAccountNumber();

        client.setPassword(password);
        client.getBankAccounts().get(0).setAccountNumber(accountNumber); // we are sure new client always has one account

        Client savedClient = clientRepository.save(client);

        return conversionService.convert(savedClient, ClientResponseDto.class);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }
}
