package com.example.demo.petshop.client;

public class Client {
    private final Long id;
    private final String firstName;
    private final String lastName;
    private final String role;

    public Client(Long id, String firstName, String lastName, String role) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getRole() {
        return role;
    }

    public String getFullName() {
        return getFirstName() + " " + getLastName();
    }
}
