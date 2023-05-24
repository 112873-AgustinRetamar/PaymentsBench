package com.bench.clients.controllers;

import com.bench.clients.entities.Address;
import com.bench.clients.entities.Client;
import com.bench.clients.models.AccountDTO;
import com.bench.clients.models.ClientAccountsDTO;
import com.bench.clients.models.ClientDTO;
import com.bench.clients.repositories.ClientRepository;
import com.bench.clients.services.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ClientController {
    @Autowired
    private IClientService clientService;
    @Autowired
    private ClientRepository clientRepository;
    @GetMapping("/clients")
    public List<ClientDTO> getClients() {
        // Prueba timeout
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return clientService.findAll();
    }
    @GetMapping("/clients/findByEmail/{email}")
    public Optional<Client> getClientByEmail(@PathVariable String email) {
        return clientRepository.findByEmail(email);
    }
    @GetMapping("/clients/accounts")
    public List<AccountDTO> getAccounts() {
        return clientService.findAllAccounts();
    }

    @GetMapping("/clients/{clientId}/accounts")
    public ClientAccountsDTO getClientAccounts(@PathVariable Long clientId) {
        List<AccountDTO> clientAccounts = clientService.findByClientId(clientId);
        ClientDTO client = clientService.findById(clientId);
        ClientAccountsDTO clientAndAccounts = new ClientAccountsDTO(client, clientAccounts);
        return clientAndAccounts;
    }
    @PostMapping("/clients")
    public ResponseEntity<Object> createClient(@RequestParam String firstName, @RequestParam String lastName,
                                               @RequestParam String email, @RequestParam String password, @RequestParam Address address){

        //valida que los campos no sean nulos
        if (firstName.isEmpty() ||  lastName.isEmpty() || email.isEmpty() || password.isEmpty() || address == null){
            return new ResponseEntity<>("Missing data", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //valida que mail no sea igual a uno ya existente
        if (clientRepository.findByEmail(email).isPresent()){
            return new ResponseEntity<>("UserName already exists", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        //nuevo cliente
        Client client = clientRepository.save(new Client(firstName, lastName, email, password, address ));

        //Si todó salió bien. Retorno un estado CREATED que es correcto
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
