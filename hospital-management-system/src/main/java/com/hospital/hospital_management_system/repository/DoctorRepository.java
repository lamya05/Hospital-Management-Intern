package com.hospital.hospital_management_system.repository;

import com.hospital.hospital_management_system.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    Doctor findByEmail(String email);

    // Basic PDF Filters
    List<Doctor> findByDepartmentAndCity(String department, String city);
    List<Doctor> findByDepartment(String department);
    List<Doctor> findByCity(String city);

    // Advanced Filter with Pagination
    @Query("SELECT d FROM Doctor d WHERE " +
           "(:dept IS NULL OR d.department = :dept) AND " +
           "(:city IS NULL OR d.city = :city) AND " +
           "(:fees IS NULL OR d.feesPerConsult <= :fees)")
    Page<Doctor> findByFilters(@Param("dept") String dept, 
                               @Param("city") String city, 
                               @Param("fees") BigDecimal fees, 
                               Pageable pageable);
@Query("SELECT COUNT(a) FROM Availability a WHERE a.doctor.id = :doctorId AND a.isBooked = false")
long countAvailableSlotsByDoctorId(@Param("doctorId") Long doctorId);
}