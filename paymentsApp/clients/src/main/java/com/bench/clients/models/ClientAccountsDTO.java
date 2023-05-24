package com.bench.clients.models;

import java.util.List;

public class ClientAccountsDTO {
    private ClientDTO client;
    private List<AccountDTO> accounts;

    public ClientAccountsDTO() {}

    public ClientAccountsDTO(ClientDTO client, List<AccountDTO> accounts) {
        this.client = client;
        this.accounts = accounts;
    }

    public List<AccountDTO> getAccounts() {
        return accounts;
    }

    public ClientDTO getClient() {
        return client;
    }
}
