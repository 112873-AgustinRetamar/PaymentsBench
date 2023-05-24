package com.bench.accounts.controllers;

import com.bench.accounts.entities.Account;
import com.bench.accounts.models.AccountDTO;
import com.bench.accounts.models.AccountPaymentsDTO;
import com.bench.accounts.models.ClientDTO;
import com.bench.accounts.models.PaymentDTO;
import com.bench.accounts.repositories.AccountRepository;
import com.bench.accounts.services.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.bench.accounts.repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("/api")
public class AccountController {


    @Autowired
    private IAccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<AccountDTO> getAccounts() {
        return accountRepository.findAll().stream().map(AccountDTO::new).collect(toList());
    }

    @GetMapping("/accounts/client/{clientId}")
    public List<AccountDTO> getClientId(@PathVariable Long clientId) {
        return accountRepository.findByClientId(clientId).stream().map(AccountDTO::new).collect(toList());
    }

    @GetMapping("/accounts/payments/{accountId}")
    public AccountPaymentsDTO getAccountPayments(@PathVariable Long accountId) {
        List<PaymentDTO> accountPayments = accountService.findByAccountId(accountId);
        AccountDTO account = accountService.findById(accountId);
        AccountPaymentsDTO accountAndPayments = new AccountPaymentsDTO(account, accountPayments);
        return accountAndPayments;
    }

    @GetMapping("/accounts/payments")
    public List<PaymentDTO> getPayments() {
        return accountService.findAllPayments();
    }

    @PostMapping("/accounts")
    public ResponseEntity<Object> createAccount(@RequestParam Long clientId) {
        Optional<ClientDTO> clientExists = accountService.findAllClients().stream().filter(c -> c.getId() == clientId).findFirst();
        if(clientId <= 0 || clientExists.equals(null)) {
            return new ResponseEntity<>("Client not found", HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
            try {
                Account account;
                do {
                    account = new Account(0.00, clientId);
                } while(accountRepository.findByNumber(account.getNumber()).isPresent());
                accountRepository.save(account);
                return new ResponseEntity<>("New account created", HttpStatus.CREATED);
            } catch (Exception exception) {
                System.out.println(exception.getMessage());
                exception.printStackTrace();
                return new ResponseEntity<>("Error creating account", HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
    }

    @PostMapping("/accounts/updateBalance")
    public void updateBalance(@RequestBody PaymentDTO paymentDTO){
        accountService.updateBalance(paymentDTO);
    }

}
