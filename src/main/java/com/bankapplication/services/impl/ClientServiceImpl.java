package com.bankapplication.services.impl;

import com.bankapplication.data.domain.BankAccount;
import com.bankapplication.data.domain.Client;
import com.bankapplication.data.repository.ClientRepository;
import com.bankapplication.dto.BankAccountResponseDto;
import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;
import com.bankapplication.exceptions.ClientNotFoundException;
import com.bankapplication.services.BankAccountService;
import com.bankapplication.services.ClientService;
import com.bankapplication.services.GenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

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

        client.setPassword(password);

        String accountNumber = generationService.getAccountNumber();

        client.getBankAccounts().get(0).setAccountNumber(accountNumber);

        Client savedClient = clientRepository.save(client);

        return conversionService.convert(savedClient, ClientResponseDto.class);
    }

    @Override
    public List<Client> findClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client findClientById(UUID id) {
        return clientRepository.findById(id).orElseThrow(() -> new ClientNotFoundException("Client not found!"));
    }

    @Override
    public void deleteById(UUID id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void deleteAllClients() {
        clientRepository.deleteAll();
    }
}
