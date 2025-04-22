package com.version1.frs.dto;

public class PassengerResponse {

    private String passengerName;
    private int passengerAge;
    private String passengerGender;

    // Constructor
    public PassengerResponse(String name, int age, String gender) {
        this.passengerName = name;
        this.passengerAge = age;
        this.passengerGender = gender;
    }

    // Getters
    public String getPassengerName() {
        return passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public String getPassengerGender() {
        return passengerGender;
    }
}
