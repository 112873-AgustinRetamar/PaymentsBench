package com.bench.clients.services;

import com.bench.clients.entities.Client;
import com.bench.clients.models.AccountDTO;
import com.bench.clients.models.ClientDTO;

import java.util.List;
import java.util.Optional;

public interface IClientService {
    public List<ClientDTO> findAll();
    public ClientDTO findById(Long Id);
    public List<AccountDTO> findAllAccounts();
    public List<AccountDTO> findByClientId(Long ClientId);
    Optional<ClientDTO> findByEmail (String email);

}
