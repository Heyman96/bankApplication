package com.bankapplication.controllers;

import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;
import com.bankapplication.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ClientController {

    private final ClientService clientService;

    @PostMapping("createClient")
    public ClientResponseDto createClient(@RequestBody ClientRequestDto clientRequestDto) {
        return clientService.createClient(clientRequestDto);
    }

    @DeleteMapping("deleteClient/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }

}
