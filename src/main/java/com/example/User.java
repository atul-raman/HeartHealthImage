package com.example;

public class User {
    private String id;
    private String name;
    private String role;
    private String password;

    // Constructor
    public User(String id, String name, String role, String password) {
        this.id = id;
        this.name = name;
        this.role = role;
        this.password = password;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Authenticate the user based on their ID and password
    public boolean authenticate(String inputId, String inputPassword) {
        return this.id.equals(inputId) && this.password.equals(inputPassword);
    }

    // Override toString for better representation
    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Role: " + role;
    }
}
