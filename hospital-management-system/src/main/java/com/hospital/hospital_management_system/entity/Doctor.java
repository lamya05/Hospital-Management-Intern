package com.hospital.hospital_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.math.BigDecimal;

@Entity
@Table(name = "doctors")
@Data
public class Doctor {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // PK

    // Personal Details (Login details)
    private String name;
    private String email;
    private String password;
    private String phone;
    private String city;

    // Professional Details (Neenga keta details)
    private String position; // Senior/Junior/Consultant
    private String department; // Cardiology/Neurology
    private BigDecimal feesPerConsult; 
    
    @Column(columnDefinition = "TEXT")
    private String clinicAddress;
    
    private Integer experienceYears;
    private Double rating = 0.0;
    private String specialization;
    
    private boolean available = true;
}