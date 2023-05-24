package com.bench.accounts.services;

import com.bench.accounts.entities.Account;
import com.bench.accounts.models.AccountDTO;
import com.bench.accounts.models.ClientDTO;
import com.bench.accounts.models.PaymentDTO;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface IAccountService {
    public List<AccountDTO> findAll();

    public AccountDTO findById(Long id);

    public List<AccountDTO> findByClientId(Long clientId);

    public List<PaymentDTO> findAllPayments();

    public List<PaymentDTO> findByAccountId(Long id);
    public void updateBalance(PaymentDTO paymentDTO);
    public List<ClientDTO> findAllClients();
}
