package com.hospital.hospital_management_system.repository;

import com.hospital.hospital_management_system.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List; // Intha line thaan missing

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByEmail(String email);
    // PatientRepository.java kulla idhai podunga
    List<Patient> findByCity(String city);
    // Ithu basic Save, Delete, Find operations-ai auto-va paathukkum
}