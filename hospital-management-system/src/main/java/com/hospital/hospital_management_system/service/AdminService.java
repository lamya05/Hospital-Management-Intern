package com.hospital.hospital_management_system.service;

import com.hospital.hospital_management_system.entity.Patient;
import com.hospital.hospital_management_system.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getPatientsByCity(String city) {
        return patientRepository.findByCity(city); // <--- City filter logic
    }
}