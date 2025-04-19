package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;
import com.version1.frs.service.AirplaneService;

@RestController
@RequestMapping("/api/airplanes")
@CrossOrigin
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // CREATE
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AirplaneResponse> addAirplane(@RequestBody AirplaneRequest airplaneRequest) {
        return ResponseEntity.ok(airplaneService.addAirplane(airplaneRequest));
    }

    // READ - All
    @GetMapping
    public ResponseEntity<List<AirplaneResponse>> getAllAirplanes() {
        return ResponseEntity.ok(airplaneService.getAllAirplanes());
    }

    // READ - By ID
    @GetMapping("/{id}")
    public ResponseEntity<AirplaneResponse> getAirplaneById(@PathVariable Long id) {
        return ResponseEntity.ok(airplaneService.getAirplaneById(id));
    }

    // READ - By Number
    @GetMapping("/number/{airplaneNumber}")
    public ResponseEntity<AirplaneResponse> getAirplaneByNumber(@PathVariable String airplaneNumber) {
        return ResponseEntity.ok(airplaneService.getAirplaneByNumber(airplaneNumber));
    }

    // UPDATE - By ID
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AirplaneResponse> updateAirplaneById(
            @PathVariable Long id,
            @RequestBody AirplaneRequest airplaneRequest) {
        return ResponseEntity.ok(airplaneService.updateAirplane(id, airplaneRequest));
    }

    // UPDATE - By Number
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/number/{airplaneNumber}")
    public ResponseEntity<AirplaneResponse> updateAirplaneByNumber(
            @PathVariable String airplaneNumber,
            @RequestBody AirplaneRequest request) {
        return ResponseEntity.ok(airplaneService.updateAirplane(airplaneNumber, request));
    }

    // DELETE - By ID
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirplaneById(@PathVariable Long id) {
        airplaneService.deleteAirplane(id);
        return ResponseEntity.ok("Airplane deleted successfully");
    }

    // DELETE - By Number
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/number/{airplaneNumber}")
    public ResponseEntity<String> deleteAirplaneByNumber(@PathVariable String airplaneNumber) {
        return ResponseEntity.ok(airplaneService.deleteAirplane(airplaneNumber));
    }

    // VALIDATION - Exists by number
    @GetMapping("/exists/{airplaneNumber}")
    public ResponseEntity<Boolean> airplaneNumberExists(@PathVariable String airplaneNumber) {
        return ResponseEntity.ok(airplaneService.airplaneNumberExists(airplaneNumber));
    }

    // SEARCH - By name keyword
    @GetMapping("/search")
    public ResponseEntity<List<AirplaneResponse>> searchByName(@RequestParam String keyword) {
        return ResponseEntity.ok(airplaneService.searchByName(keyword));
    }

    // FILTER - By manufacturer
    @GetMapping("/filter/manufacturer/{manufacturer}")
    public ResponseEntity<List<AirplaneResponse>> filterByManufacturer(@PathVariable String manufacturer) {
        return ResponseEntity.ok(airplaneService.filterByManufacturer(manufacturer));
    }

    // FILTER - By model
    @GetMapping("/filter/model/{model}")
    public ResponseEntity<List<AirplaneResponse>> filterByModel(@PathVariable String model) {
        return ResponseEntity.ok(airplaneService.filterByModel(model));
    }
}
