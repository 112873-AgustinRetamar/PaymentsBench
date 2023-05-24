package com.bench.accounts.models;

import java.util.List;

public class AccountPaymentsDTO {
    private AccountDTO account;
    private List<PaymentDTO> payments;

    public AccountPaymentsDTO() {}

    public AccountPaymentsDTO(AccountDTO account, List<PaymentDTO> payments) {
        this.account = account;
        this.payments = payments;
    }

    public AccountDTO getAccount() {
        return account;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }
}
