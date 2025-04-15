package com.version1.frs.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AirplaneRequest {

    @NotBlank(message = "Airplane name cannot be blank")
    @Size(min = 2, max = 100, message = "Airplane name must be between 2 and 100 characters")
    private String airplaneName;

    @NotBlank(message = "Airplane model cannot be blank")
    @Size(min = 2, max = 50, message = "Airplane model must be between 2 and 50 characters")
    private String airplaneModel;

    @NotBlank(message = "Manufacturer cannot be blank")
    @Size(min = 2, max = 100, message = "Manufacturer name must be between 2 and 100 characters")
    private String manufacturer;

    @Min(value = 1, message = "Capacity must be at least 1")
    private int capacity;

    // Getters and Setters
    public String getAirplaneName() {
        return airplaneName;
    }

    public void setAirplaneName(String airplaneName) {
        this.airplaneName = airplaneName;
    }

    public String getAirplaneModel() {
        return airplaneModel;
    }

    public void setAirplaneModel(String airplaneModel) {
        this.airplaneModel = airplaneModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
