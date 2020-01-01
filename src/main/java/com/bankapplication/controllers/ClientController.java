package com.bankapplication.controllers;

import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;
import com.bankapplication.services.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("clients")
public class ClientController {

    private final ClientService clientService;

    // TODO: findById

    @PostMapping
    public ClientResponseDto createClient(@RequestBody @Valid ClientRequestDto clientRequestDto) {
        return clientService.createClient(clientRequestDto);
    }

    @DeleteMapping("{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteById(id);
    }

}
