package com.tapfoods.model;

public class User {
    private int userId;
    private String username;
    private String email;
    private String password;
    private String address;
    private String role;

    public User() 
    {
    	
    }

    public User(String username, String email, String password, String address, String role) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public User(int userId, String username, String email, String password, String address, String role) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.role = role;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User [userId=" + userId + ", username=" + username + ", email=" + email +
               ", password=" + password + ", address=" + address + ", role=" + role + "]";
    }
}