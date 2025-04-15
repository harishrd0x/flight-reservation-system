package com.version1.frs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;
import com.version1.frs.service.AirportService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airports")
@CrossOrigin
public class AirportController {

    @Autowired
    private AirportService airportService;

    // Add airport (ADMIN only)
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<AirportResponse> addAirport(@Valid @RequestBody AirportRequest request) {
        return ResponseEntity.ok(airportService.addAirport(request));
    }

    // Update airport (ADMIN only)
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<AirportResponse> updateAirport(@PathVariable int id,
                                                         @RequestBody AirportRequest request) {
        return ResponseEntity.ok(airportService.updateAirport(id, request));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable int id) {
        boolean exists = airportService.doesAirportExist(id);
        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Airport not found.");
        }
        airportService.deleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully.");
    }


    // Get all airports (shared)
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public List<AirportResponse> getAllAirports() {
        return airportService.getAllAirports();
    }

    // Get airport by ID (shared)
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<AirportResponse> getAirportById(@PathVariable int id) {
        Optional<AirportResponse> response = airportService.getAirportById(id);
        return response.map(ResponseEntity::ok)
                       .orElse(ResponseEntity.notFound().build());
    }
}