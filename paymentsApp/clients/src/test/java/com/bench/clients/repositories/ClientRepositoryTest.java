package com.bench.clients.repositories;

import com.bench.clients.entities.Client;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.NONE;
import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
class ClientRepositoryTest {
    @Autowired
    ClientRepository clientRepository;

    @Test
    public void existClients(){
        List<Client> clients = clientRepository.findAll();
        assertThat(clients,is(not(empty())));
    }

    @Test
    public void testFindByEmail(){
        Optional<Client> client = clientRepository.findByEmail("melba@mindhub.com");
        assertTrue(client.isPresent());
        assertEquals("melba@mindhub.com",client.orElseThrow().getEmail());
    }
    @Test
    public void testFindByEmailThrowException(){
        Optional<Client> client = clientRepository.findByEmail("cachito174Capokj@mindhub.com");
        assertThrows(NoSuchElementException.class, client::orElseThrow);
        assertFalse(client.isPresent());
    }
    @Test
    public void testClientSave(){
        Client client1= new Client("Jack","Reta","jackreta@gmail.com","123",null);
        clientRepository.save(client1);
        //when
        Optional<Client> client = clientRepository.findByEmail("jackreta@gmail.com");
        //then
        assertEquals("jackreta@gmail.com",client.orElseThrow().getEmail());
    }
    @Test
    public void testClientDelete(){
        Client client= clientRepository.findByEmail("melba@mindhub.com").orElseThrow();
        assertEquals("Melba",client.getFirstName());

        clientRepository.delete(client);

        assertThrows(NoSuchElementException.class, () -> {
            clientRepository.findByEmail("melba@mindhub.com").orElseThrow();
        });
    }

}