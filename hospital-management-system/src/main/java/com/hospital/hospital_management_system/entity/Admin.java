package com.hospital.hospital_management_system.entity;
import jakarta.persistence.*;
import lombok.Data;
@Entity
@Table(name = "admins")
@Data
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // [cite: 34]
    private String name;
    @Column(unique = true)
    private String email;
    private String password; // [cite: 35]
}