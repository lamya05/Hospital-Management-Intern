package com.hospital.hospital_management_system.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*; // Validation use panna idhu venum
import lombok.Data;

@Entity
@Table(name = "patients")
@Data
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Name is required") // Validation rule
    @Size(max = 100)
    private String name;

    @NotNull
    private Integer age;

    @NotBlank(message = "Phone number is required")
    @Size(max = 15)
    private String phone;

    @Email(message = "Please provide a valid email") // Email format check
    @Column(unique = true, length = 100)
    @NotBlank
    private String email;

    @NotBlank
    private String password;

    @Column(columnDefinition = "TEXT")
    private String address;

    private String gender; // Male/Female/Other

    @Size(max = 5)
    private String bloodGroup;

    private String emergencyContact;

    private String city; 

    // Getters and Setters (Kandippa city-kum generate pannanum)
    public String getCity() { return city; }
    public void setCity(String city) { this.city = city; }
}