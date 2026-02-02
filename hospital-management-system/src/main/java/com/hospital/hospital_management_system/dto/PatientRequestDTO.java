package com.hospital.hospital_management_system.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class PatientRequestDTO {
    @NotBlank(message = "Name is required") // Name empty-aa irukka koodaadhu
    private String name;

    @NotNull(message = "Age is required")
    @Min(value = 1, message = "Age must be at least 1")
    private Integer age;

    @NotBlank(message = "Phone number is required")
    @Size(min = 10, max = 15, message = "Phone number must be between 10 to 15 digits")
    private String phone;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format") // Email format-ai check pannum
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;

    private String address;
    private String gender;
    private String bloodGroup;
    private String emergencyContact;
    private String city;
}