package com.java8.test6.optional;


import java.util.Optional;

public class User {
    String name;

    String address;

    String email;

    String country;

    String iscoder;

    String position;

    public User() {
    }

    public Optional<String> getPosition() {
        return Optional.ofNullable(position);
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public User(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getIscoder() {
        return iscoder;
    }

    public void setIscoder(String iscoder) {
        this.iscoder = iscoder;
    }
}
