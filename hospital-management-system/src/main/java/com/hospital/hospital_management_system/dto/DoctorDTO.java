package com.hospital.hospital_management_system.dto;

import lombok.Data;

@Data
public class DoctorDTO {
    private Long id;
    private String name;
    private String email;
    private String password;
    private String phone;
    private String specialization;
    
    // PDF requirements-padi extra fields
    private String department;
    private Double feesPerConsult;
    private Integer experienceYears;
    private String city;
}