package com.bench.accounts.models;

public class ClientDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private int addressNumber;
    private String addressStreet;
    private String addressHometown;

    public ClientDTO() {
    }

    public ClientDTO(Long id, String firstName, String lastName, String email, int addressNumber, String addressStreet, String addressHometown) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.addressNumber = addressNumber;
        this.addressStreet = addressStreet;
        this.addressHometown = addressHometown;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
