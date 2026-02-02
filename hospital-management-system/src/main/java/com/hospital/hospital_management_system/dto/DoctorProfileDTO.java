package com.hospital.hospital_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;

@Data
@AllArgsConstructor // Idhu thaan andha error-ai fix pannum
@NoArgsConstructor
public class DoctorProfileDTO {
    private String name;
    private String department;
    private int experienceYears;
    private BigDecimal feesPerConsult;
    private long availableSlotsCount;
}