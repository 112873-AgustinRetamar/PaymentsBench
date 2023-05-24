package com.bench.payments.models;

import com.bench.payments.entities.Payment;
import com.bench.payments.entities.PaymentMethod;
import com.bench.payments.entities.PaymentType;

import java.time.LocalDateTime;

public class PaymentDTO {
    private Long id;
    private PaymentType paymentType;
    private Double amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;
    private Long fromAccountId;
    private Long toAccountId;

    public PaymentDTO() {
    }

    public PaymentDTO(Payment payment) {
        this.id = payment.getId();
        this.paymentType = payment.getPaymentType();
        this.amount = payment.getAmount();
        this.paymentMethod = payment.getPaymentMethod();
        this.date = payment.getDate();
        this.fromAccountId = payment.getFromAccountId();
        this.toAccountId = payment.getToAccountId();
    }

    public Long getId() {
        return id;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getFromAccountId() {
        return fromAccountId;
    }

    public void setFromAccountId(Long fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public Long getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(Long toAccountId) {
        this.toAccountId = toAccountId;
    }
}
