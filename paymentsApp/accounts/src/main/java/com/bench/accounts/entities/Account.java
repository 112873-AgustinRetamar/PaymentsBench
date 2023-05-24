package com.bench.accounts.entities;

import org.hibernate.annotations.GenericGenerator;
import utils.Utils;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Entity
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native" , strategy = "native")
    private Long id;
    private String number;
    private LocalDateTime creationDate;
    private double balance;
    private Long clientId;
    public Account() {
    }

    public Account(double balance, Long clientId) {
        //Creo un número al azar en generateRandomNumber() (en este caso entre 3 y 8) y se lo paso
        //por parámetro a generateRandomNumbers(el_numero_al_azar_aca) para generar esa cantidad
        //de números para la Account
        this.number = Utils.generateRandomNumbers(16);
        this.creationDate = LocalDateTime.now();
        this.balance = balance;
        this.clientId = clientId;
    }

    public Long getId() {
        return id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }
}
