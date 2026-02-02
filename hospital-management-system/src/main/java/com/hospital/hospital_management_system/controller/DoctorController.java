package com.hospital.hospital_management_system.controller;

import com.hospital.hospital_management_system.dto.*;

import com.hospital.hospital_management_system.service.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.List;




@RestController
@RequestMapping("/api/auth/doctor")
@CrossOrigin(origins = "*")
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    // 1. REGISTER: Pudhu doctor join panna
    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody DoctorDTO dto) {
        return ResponseEntity.ok(doctorService.registerDoctor(dto));
    }

    // 2. LOGIN: Token edukka (Unga LoginRequest class use panrom)
    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(doctorService.loginDoctor(loginRequest.getEmail(), loginRequest.getPassword()));
    }

   
    @GetMapping("/all")
public ResponseEntity<Page<DoctorDTO>> getDoctors(
        @RequestParam(required = false) String department,
        @RequestParam(required = false) String city,
        @RequestParam(required = false) Double fees_lt,
        Pageable pageable) { // Inga 'Pageable' kandippa irukkanum
    return ResponseEntity.ok(doctorService.getFilteredDoctors(department, city, fees_lt, pageable));
}
// 5. DOCTOR PROFILE: PDF Point 2-ku idhu thaan mukkiyam
@GetMapping("/{id}")
public ResponseEntity<DoctorProfileDTO> getProfile(@PathVariable Long id) {
    return ResponseEntity.ok(doctorService.getDoctorProfile(id));
}
   
   // 4. AVAILABILITY: Doctor avaroda free timings-ai add panna
    @PostMapping("/availability")
    public ResponseEntity<String> setAvailability(
            @RequestParam Long doctorId,
            @RequestParam String date,
            @RequestParam String startTime,
            @RequestParam String endTime) {
        
        // Inga 'doctorId', 'date', 'startTime', 'endTime' variables-ai match panni anupurom
        return ResponseEntity.ok(doctorService.addSlot(doctorId, date, startTime, endTime));
    }
    @GetMapping("/{id}/availability")
public ResponseEntity<List<SlotDTO>> getSlots(
        @PathVariable Long id, 
        @RequestParam String date) {
    return ResponseEntity.ok(doctorService.getDoctorSlots(id, date));
}
}