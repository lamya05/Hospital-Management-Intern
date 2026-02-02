package com.hospital.hospital_management_system.controller;

import com.hospital.hospital_management_system.entity.Patient;
import com.hospital.hospital_management_system.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin")
@CrossOrigin(origins = "*")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @GetMapping("/patients") // <--- Fetch panna GetMapping
    public ResponseEntity<List<Patient>> getPatientsByCity(@RequestParam String city) {
        return ResponseEntity.ok(adminService.getPatientsByCity(city));
    }
}