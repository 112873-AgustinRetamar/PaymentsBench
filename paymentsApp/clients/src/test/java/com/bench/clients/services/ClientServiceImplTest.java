package com.bench.clients.services;

import com.bench.clients.entities.Address;
import com.bench.clients.entities.Client;
import com.bench.clients.repositories.ClientRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest
class ClientServiceImplTest {
    @MockBean
    ClientRepository clientRepository;
    @Autowired
    IClientService clientService;
    @Test
    void findClientByEmail(){
        Client clients = new Client("Melba","Motrel", "leo@morel.com", "123",
                new Address(123, "cale", "direc"));
        clientRepository.save(clients);
        when(clientRepository.findByEmail("leo@morel.com")).thenReturn(Optional.of(Optional.of(clients).orElseThrow()));
        Optional<Client> client = clientService.findByEmail("leo@morel.com");
        assertNotNull(client);
    }
}