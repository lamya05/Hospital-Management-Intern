package com.hospital.hospital_management_system.repository;

import com.hospital.hospital_management_system.entity.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
    Admin findByEmail(String email); // Login check panna idhu thevai
}
