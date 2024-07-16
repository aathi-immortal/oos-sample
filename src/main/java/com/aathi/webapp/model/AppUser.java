package com.aathi.webapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class AppUser {

    // Static instance of the singleton
    private static AppUser instance;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String creditCardToken; // New field for storing credit card token

    // Private constructor to prevent instantiation
    private AppUser() {
        // Initialize fields here if needed
    }

    // Static method to get the singleton instance
    public static synchronized AppUser getInstance() {
        if (instance == null) {
            instance = new AppUser();
        }
        return instance;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getCreditCardToken() {
        return creditCardToken;
    }

    public void setCreditCardToken(String creditCardToken) {
        this.creditCardToken = creditCardToken;
    }
}
