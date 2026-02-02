package com.hospital.hospital_management_system.service;

import com.hospital.hospital_management_system.dto.AuthResponse;
import com.hospital.hospital_management_system.dto.LoginRequest;
import com.hospital.hospital_management_system.entity.Doctor;
import com.hospital.hospital_management_system.entity.Patient;
import com.hospital.hospital_management_system.repository.DoctorRepository;
import com.hospital.hospital_management_system.repository.PatientRepository;
import com.hospital.hospital_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse login(LoginRequest loginRequest) {
        String role = loginRequest.getRole();
        Long id = null;

        // 1. Role-ai check panni credentials verify pannuvom
        if ("PATIENT".equalsIgnoreCase(role)) {
            Patient p = patientRepository.findByEmail(loginRequest.getEmail());
            if (p != null && p.getPassword().equals(loginRequest.getPassword())) {
                id = p.getId();
            }
        } else if ("DOCTOR".equalsIgnoreCase(role)) {
            Doctor d = doctorRepository.findByEmail(loginRequest.getEmail());
            if (d != null && d.getPassword().equals(loginRequest.getPassword())) {
                id = d.getId();
            }
        } else if ("ADMIN".equalsIgnoreCase(role)) {
            // Admin-ukku direct-aa check pannikalam
            if ("admin@hospital.com".equals(loginRequest.getEmail()) && 
                "admin123".equals(loginRequest.getPassword())) {
                id = 0L; // Static ID for Admin
            }
        }

        if (id == null) {
            throw new RuntimeException("Invalid Email, Password or Role!");
        }

        // 2. Success-naa Token generate panni anuppalaam
        String token = jwtUtil.generateToken(loginRequest.getEmail());
        return new AuthResponse(id, token, role.toUpperCase());
    }
}