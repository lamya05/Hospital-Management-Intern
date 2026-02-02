package com.hospital.hospital_management_system.service;

import com.hospital.hospital_management_system.dto.*;
import com.hospital.hospital_management_system.entity.*;
import com.hospital.hospital_management_system.repository.*;
import com.hospital.hospital_management_system.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DoctorService {
    @Autowired private DoctorRepository doctorRepository;
    @Autowired private AvailabilityRepository availabilityRepository;
    @Autowired private JwtUtil jwtUtil;

    // 1. REGISTER
    public AuthResponse registerDoctor(DoctorDTO dto) {
        Doctor doctor = new Doctor();
        doctor.setName(dto.getName());
        doctor.setEmail(dto.getEmail());
        doctor.setPassword(dto.getPassword());
        doctor.setCity(dto.getCity());
        doctor.setDepartment(dto.getDepartment());
        doctor.setFeesPerConsult(java.math.BigDecimal.valueOf(dto.getFeesPerConsult()));
        doctor.setExperienceYears(dto.getExperienceYears());
        Doctor saved = doctorRepository.save(doctor);
        return new AuthResponse(saved.getId(), jwtUtil.generateToken(saved.getEmail()), "DOCTOR");
    }

    // 2. LOGIN
    public AuthResponse loginDoctor(String email, String password) {
        Doctor doctor = doctorRepository.findByEmail(email);
        if (doctor != null && doctor.getPassword().equals(password)) {
            return new AuthResponse(doctor.getId(), jwtUtil.generateToken(doctor.getEmail()), "DOCTOR");
        }
        throw new RuntimeException("Invalid credentials!");
    }

    // 3. FILTER WITH PAGINATION (Screenshot 335 fix)
    public Page<DoctorDTO> getFilteredDoctors(String dept, String city, Double fees, Pageable pageable) {
    java.math.BigDecimal feesLimit = (fees != null) ? java.math.BigDecimal.valueOf(fees) : null;
    
    // 1. DB-la irundhu Entity-ah edukkurom
    Page<Doctor> doctors = doctorRepository.findByFilters(dept, city, feesLimit, pageable);
    
    // 2. Entity-ah DTO-va mathuroam (Password-ah hide panna)
    return doctors.map(doc -> {
        DoctorDTO dto = new DoctorDTO();
        dto.setId(doc.getId());
        dto.setName(doc.getName());
        dto.setDepartment(doc.getDepartment());
        dto.setCity(doc.getCity());
        dto.setFeesPerConsult(doc.getFeesPerConsult().doubleValue());
        dto.setExperienceYears(doc.getExperienceYears());
        // Password set panna vendam!
        return dto;
    });
}

    // 4. ADD SLOT
    public String addSlot(Long docId, String date, String start, String end) {
    LocalDate slotDate = LocalDate.parse(date);
    LocalTime startTime = LocalTime.parse(start);
    LocalTime endTime = LocalTime.parse(end);

    // 1. PDF Rule: Check for overlap
    // Orey doctor-ku, orey date-la, time clash aagudha-nu check panrom
    boolean isOverlapping = availabilityRepository.existsOverlappingSlot(
            docId, slotDate, startTime, endTime);

    if (isOverlapping) {
        throw new RuntimeException("Error: Already Booked!");
    }

    // 2. Overlap illai-na save pannalaam
    Doctor doc = doctorRepository.findById(docId).orElseThrow();
    Availability slot = new Availability();
    slot.setDoctor(doc);
    slot.setDate(slotDate);
    slot.setStartTime(startTime);
    slot.setEndTime(endTime);
    slot.setBooked(false); // Puthiya slot eppovum available-ah irukanum
    
    availabilityRepository.save(slot);
    return "Slot added successfully!";
}
    // 5. DOCTOR PROFILE
    // 5. DOCTOR PROFILE
public DoctorProfileDTO getDoctorProfile(Long id) {
    Doctor doctor = doctorRepository.findById(id).orElseThrow();
    long count = availabilityRepository.countByDoctorIdAndIsBookedFalse(id);
    
    // Constructor order: name, department, experienceYears, feesPerConsult, availableSlotsCount
    return new DoctorProfileDTO(
            doctor.getName(), 
            doctor.getDepartment(), 
            doctor.getExperienceYears(), 
            doctor.getFeesPerConsult(), 
            count
    );
}
public List<SlotDTO> getDoctorSlots(Long doctorId, String date) {
    LocalDate slotDate = LocalDate.parse(date);
    List<Availability> availabilities = availabilityRepository.findByDoctorIdAndDate(doctorId, slotDate);

    return availabilities.stream()
            .map(a -> new SlotDTO(a.getStartTime().toString(), a.isBooked()))
            .collect(Collectors.toList());
}
}