package com.hospital.hospital_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponse {
    private Long id;        // patientId or doctorId
    private String jwtToken; // PDF output requirement
    private String role;     // Login output requirement
}