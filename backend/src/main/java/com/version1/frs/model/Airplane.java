package com.version1.frs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_AIRPLANES")
public class Airplane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AIRPLANE_ID")
    private Long id;

    @Column(name = "AIRPLANE_NAME", nullable = false)
    private String name;

    @Column(name = "AIRPLANE_MODEL", nullable = false)
    private String model;

    @Column(name = "MANUFACTURER", nullable = false)
    private String manufacturer;

    @Column(name = "CAPACITY", nullable = false)
    private int capacity;

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
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