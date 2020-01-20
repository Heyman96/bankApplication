package com.bankapplication.data.repository;

import com.bankapplication.data.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ClientRepository extends JpaRepository<Client, UUID> {

    List<Client> findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    List<Client> findByPatronymic(String patronymic);

    List<Client> findByBirthDate(LocalDate birthDate);

    Optional<Client> findByPhoneNumber(String phoneNumber);
}
