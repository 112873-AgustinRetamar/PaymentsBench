package com.bench.accounts.services;

import com.bench.accounts.entities.Account;
import com.bench.accounts.models.AccountDTO;
import com.bench.accounts.models.ClientDTO;
import com.bench.accounts.models.PaymentDTO;
import com.bench.accounts.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService{
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private RestTemplate accountRest;

    @Override
    public List<PaymentDTO> findAllPayments() {
        List<PaymentDTO> payments = Arrays.asList(accountRest.getForObject("http://localhost:8090/payments/api/payments", PaymentDTO[].class));
        return payments;
    }

    @Override
    public List<PaymentDTO> findByAccountId(Long accountId) {
        Map<String, String> pathVariables = new HashMap<String, String>();
        pathVariables.put("accountId", accountId.toString());
        List<PaymentDTO> accountPayments = Arrays.asList(accountRest.getForObject("http://localhost:8090/payments/api/payments/account/{accountId}", PaymentDTO[].class, pathVariables));
        return accountPayments;
    }

    @Override
    //@Transactional (readOnly = true)
    public List<AccountDTO> findAll() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(Collectors.toList());
    }

    @Override
    //@Transactional (readOnly = true)
    public AccountDTO findById(Long id) {
        return accountRepository.findById(id).map(AccountDTO::new).orElse(null);
    }

    @Override
    //@Transactional (readOnly = true)
    public List<AccountDTO> findByClientId(Long clientId) {
        return accountRepository.findByClientId(clientId).stream().map(AccountDTO::new).collect(Collectors.toList());
    }
    @Override
    public List<ClientDTO> findAllClients() {
        List<ClientDTO> clients = Arrays.asList(accountRest.getForObject("http://localhost:8090/clients/api/clients", ClientDTO[].class));
        return clients;
    }
    @Override
    public void updateBalance(PaymentDTO paymentDTO) {

        Account fromAccount = accountRepository.findById(paymentDTO.getFromAccountId()).get();
        Account toAccount = accountRepository.findById(paymentDTO.getToAccountId()).get();

        fromAccount.setBalance(fromAccount.getBalance() - paymentDTO.getAmount());
        toAccount.setBalance(toAccount.getBalance() + paymentDTO.getAmount());
        if (fromAccount.getBalance()>= paymentDTO.getAmount()){
            accountRepository.save(fromAccount);
            accountRepository.save(toAccount);
        }

    }
}
