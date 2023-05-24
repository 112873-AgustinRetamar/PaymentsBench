package com.bench.payments.services;

import com.bench.payments.entities.Payment;
import com.bench.payments.models.AccountDTO;
import com.bench.payments.models.PaymentDTO;

import java.util.List;

public interface IPaymentService {
    public List<PaymentDTO> findAll();

    public PaymentDTO findById(Long id);

    public List<PaymentDTO> findByFromAccountId(Long fromAccountId);
    public Payment createNewPayment(PaymentDTO paymentDTO);
    public String convertAndSend(PaymentDTO paymentDTO);
}
