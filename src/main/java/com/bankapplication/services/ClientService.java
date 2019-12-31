package com.bankapplication.services;

import com.bankapplication.dto.ClientRequestDto;
import com.bankapplication.dto.ClientResponseDto;

public interface ClientService {

    ClientResponseDto createClient(ClientRequestDto clientRequestDto);

    void deleteById(Long id);

}
