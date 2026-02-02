package com.hospital.hospital_management_system.dto;

public class LoginRequest {
    private String email;
    private String password;
    private String role; // ADMIN, DOCTOR, or PATIENT
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
    public String getRole() {
        return role;
    }
    public void setRole(String role) {
        this.role = role;
    }

    // Getters and Setters (VS Code-la Right click -> Source Action -> Generate Getters/Setters)
}