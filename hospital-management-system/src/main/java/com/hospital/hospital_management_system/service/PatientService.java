package com.hospital.hospital_management_system.service;

import com.hospital.hospital_management_system.dto.AuthResponse; // Idhu thevai
import com.hospital.hospital_management_system.dto.PatientRequestDTO;
import com.hospital.hospital_management_system.entity.Patient;
import com.hospital.hospital_management_system.repository.PatientRepository;
import com.hospital.hospital_management_system.util.JwtUtil; // JWT generate panna idhu thevai
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PatientService {

  

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthResponse registerPatient(PatientRequestDTO patientDto) {
        // 1. Check if input password is null (Safety check)
        if (patientDto.getPassword() == null) {
            throw new RuntimeException("Password cannot be null in the request!");
        }

        Patient patient = new Patient();
        
        // 2. MUKKHIYAM: Input DTO-la irundhu password-ai eduthu encode pannanum!
        String encodedPassword = passwordEncoder.encode(patientDto.getPassword());
        patient.setPassword(encodedPassword); // Hash-ana password-ai set pandrom
        
        // 3. Matra details-ai set pannunga
        patient.setName(patientDto.getName());
        patient.setAge(patientDto.getAge());
        patient.setPhone(patientDto.getPhone());
        patient.setEmail(patientDto.getEmail());
        patient.setAddress(patientDto.getAddress());
        patient.setGender(patientDto.getGender());
        patient.setBloodGroup(patientDto.getBloodGroup());
        patient.setEmergencyContact(patientDto.getEmergencyContact());
        patient.setCity(patientDto.getCity());

        // 4. Save PATIENT
        Patient savedPatient = patientRepository.save(patient);
        
        // 5. Generate JWT
        String token = jwtUtil.generateToken(savedPatient.getEmail());
        
        // 6. Return output
        return new AuthResponse(savedPatient.getId(), token, "PATIENT");
    }
}
    

