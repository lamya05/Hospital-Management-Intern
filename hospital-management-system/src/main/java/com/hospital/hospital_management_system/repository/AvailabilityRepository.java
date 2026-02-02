package com.hospital.hospital_management_system.repository;

import com.hospital.hospital_management_system.entity.Availability;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

public interface AvailabilityRepository extends JpaRepository<Availability, Long> {
    // Particular doctor-oda slots-ai mattum edukka idhu help pannum
    List<Availability> findByDoctorId(Long doctorId);
    long countByDoctorIdAndIsBookedFalse(Long doctorId);
    List<Availability> findByDoctorIdAndDate(Long doctorId, LocalDate date);
    @Query("SELECT COUNT(a) > 0 FROM Availability a WHERE a.doctor.id = :docId " +
       "AND a.date = :date " +
       "AND ((:start < a.endTime AND :end > a.startTime))")
boolean existsOverlappingSlot(@Param("docId") Long docId, 
                             @Param("date") LocalDate date, 
                             @Param("start") LocalTime start, 
                             @Param("end") LocalTime end);

}