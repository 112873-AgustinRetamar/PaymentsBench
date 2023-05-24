package com.bench.clients.services;

import com.bench.clients.entities.Client;
import com.bench.clients.models.AccountDTO;
import com.bench.clients.models.ClientDTO;
import com.bench.clients.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

import static java.util.stream.Collectors.toList;

@Service
public class ClientServiceImpl implements IClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestTemplate clientRest;

    @Override
    //@Transactional (readOnly = true)
    public List<ClientDTO> findAll() {
        return clientRepository.findAll().stream().map(ClientDTO::new).collect(toList());
    }

    @Override
    public List<AccountDTO> findAllAccounts() {
        List<AccountDTO> accounts = Arrays.asList(clientRest.getForObject("http://localhost:8090/accounts/api/accounts", AccountDTO[].class));
        return accounts;
    }

    @Override
    public List<AccountDTO> findByClientId(Long clientId) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("clientId", clientId.toString());
        List<AccountDTO> clientAccounts = Arrays.asList(clientRest.getForObject("http://localhost:8090/accounts/api/accounts/client/{clientId}", AccountDTO[].class, pathVariables));
        return clientAccounts;
    }

    @Override
    //@Transactional (readOnly = true)
    public ClientDTO findById(Long id) {
        return clientRepository.findById(id).map(ClientDTO::new).orElse(null);
    }

    @Override
    public Optional<ClientDTO> findByEmail(String email) {
        return clientRepository.findByEmail(email).stream().map(ClientDTO::new).findFirst();
    }

}
