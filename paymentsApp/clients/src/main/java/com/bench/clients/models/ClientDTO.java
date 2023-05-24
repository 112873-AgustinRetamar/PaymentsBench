package com.bench.clients.models;

import com.bench.clients.entities.Client;

public class ClientDTO {
    private String firstName;
    private String lastName;
    private String email;
    private int addressNumber;
    private String addressStreet;
    private String addressHometown;

    public ClientDTO() {
    }

    public ClientDTO(Client client) {
        this.firstName = client.getFirstName();
        this.lastName = client.getLastName();
        this.email = client.getEmail();
        this.addressNumber = client.getAddress().getNumber();
        this.addressStreet = client.getAddress().getStreet();
        this.addressHometown = client.getAddress().getHometown();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getAddressHometown() {
        return addressHometown;
    }

    public void setAddressHometown(String addressHometown) {
        this.addressHometown = addressHometown;
    }
}
