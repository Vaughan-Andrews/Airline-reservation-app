package com.example.vaughan.airlinereservation;

import java.util.UUID;

public class Person {
    private String username;
    private String password;
    private UUID personID;

    public Person(String username, String password) {
        this.username = username;
        this.password = password;
        personID = UUID.randomUUID();
    }
    public Person(UUID id){
        personID = UUID.randomUUID();
    }

    @Override
    public String toString() {
        return "Person{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UUID getPersonID() {
        return personID;
    }

    public void setPersonID(UUID personID) {
        this.personID = personID;
    }
}
