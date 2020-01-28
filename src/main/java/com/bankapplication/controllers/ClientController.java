package com.bankapplication.controllers;

import com.bankapplication.data.domain.Client;
import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;
import com.bankapplication.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    @PostMapping
    public ClientResponseDto createClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return clientService.createClient(clientRequestDto);
    }

    @GetMapping("clients")
    public List<Client> findClients() {
        return clientService.findClients();
    }

    @GetMapping("{id}")
    public Client findClientById(@PathVariable UUID id) {
        return clientService.findClientById(id);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable UUID id) {
        clientService.deleteById(id);
    }

    @DeleteMapping("clients")
    public void deleteAllClients() {
        clientService.deleteAllClients();
    }

}
