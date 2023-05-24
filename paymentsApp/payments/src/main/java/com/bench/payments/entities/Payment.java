package com.bench.payments.entities;

import com.bench.payments.models.PaymentDTO;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Long id;
    private PaymentType paymentType;
    private Double amount;
    private PaymentMethod paymentMethod;
    private LocalDateTime date;
    private Long fromAccountId;
    private Long toAccountId;

    public Payment() {
    }

    public Payment(PaymentType paymentType, Double amount, PaymentMethod paymentMethod, Long fromAccountId, Long toAccountId) {
        this.paymentType = paymentType;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.date = LocalDateTime.now();
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
    }
    public Payment(PaymentDTO paymentDTO) {
        this.amount = paymentDTO.getAmount();
        this.date = LocalDateTime.now();
        this.paymentType = paymentDTO.getPaymentType();
        this.paymentMethod = paymentDTO.getPaymentMethod();
        this.fromAccountId = paymentDTO.getFromAccountId();
        this.toAccountId = paymentDTO.getToAccountId();

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
