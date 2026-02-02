package com.hospital.hospital_management_system.controller;

import com.hospital.hospital_management_system.dto.AuthResponse;
import com.hospital.hospital_management_system.dto.PatientRequestDTO;

import com.hospital.hospital_management_system.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth/patient") // PDF-la sonna maari idha mathunga
@CrossOrigin(origins = "*") 
public class PatientController {

    @Autowired
    private PatientService patientService;

   @PostMapping("/register")
// Return type-ai String-la irundhu ResponseEntity<AuthResponse>-ku mathunga
public ResponseEntity<AuthResponse> registerPatient(@Valid @RequestBody PatientRequestDTO patientDto) {
    
    // Service-la irundhu AuthResponse (ID + Token) vaangurom
    AuthResponse response = patientService.registerPatient(patientDto);
    
    // JSON-aa anuppurom
    return ResponseEntity.ok(response);
}
}