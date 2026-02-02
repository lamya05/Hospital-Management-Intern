package com.hospital.hospital_management_system.controller;

import com.hospital.hospital_management_system.dto.AuthResponse;
import com.hospital.hospital_management_system.dto.LoginRequest;
import com.hospital.hospital_management_system.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth") // Common path for login
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    // Login endpoint
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        // Service-la irundhu ID, Token, and Role-ai vaangi return pannum
        return ResponseEntity.ok(authService.login(loginRequest));
    }
}