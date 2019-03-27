package com.example.rishitmehta.echelon.Model;

public class User {
    private String Password;

    private User() {}

    public User( String password) {

        this.Password = password;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }
}
