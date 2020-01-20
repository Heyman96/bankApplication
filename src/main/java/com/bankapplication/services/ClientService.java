package com.bankapplication.services;

import com.bankapplication.data.domain.Client;
import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;

import java.util.List;
import java.util.UUID;

public interface ClientService {

    ClientResponseDto createClient(ClientRequestDto clientRequestDto);

    List<Client> findClients();

    Client findClientById(UUID id);

    void deleteById(UUID id);

    void deleteAllClients();

}
