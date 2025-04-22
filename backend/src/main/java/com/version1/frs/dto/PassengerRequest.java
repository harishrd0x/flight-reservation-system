package com.version1.frs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class PassengerRequest {

    @NotBlank(message = "Passenger name is required")
    private String passengerName;

    @Min(value = 0, message = "Passenger age must be non-negative")
    private int passengerAge;

    @NotBlank(message = "Passenger gender is required")
    private String passengerGender;

    // Getters and Setters
    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }
}
