package com.hospital.hospital_management_system.dto;

public class SlotDTO {
    private String timeSlot;
    private boolean isBooked;

    public SlotDTO(String timeSlot, boolean isBooked) {
        this.timeSlot = timeSlot;
        this.isBooked = isBooked;
    }

    // Getters
    public String getTimeSlot() { return timeSlot; }
    public boolean isBooked() { return isBooked; }

    // Setters
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
    public void setBooked(boolean isBooked) { this.isBooked = isBooked; }
}