package com.hospital.hospital_management_system.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
public class Availability {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isBooked = false; // Initial-aa yaarum book pannala

    @ManyToOne
    @JoinColumn(name = "doctor_id")
    private Doctor doctor; // Intha slot entha doctor-kudunnu link pannuvom
}