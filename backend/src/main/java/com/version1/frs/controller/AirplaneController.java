package com.version1.frs.controller;

import java.util.List;

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

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;
import com.version1.frs.service.AirplaneService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/airplanes")
@CrossOrigin
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    // Add Airplane
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> addAirplane(@Valid @RequestBody AirplaneRequest request) {
        try {
            airplaneService.addAirplane(request);
            return new ResponseEntity<>(new MessageResponse("Airplane created successfully"), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to create airplane: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    // Update Airplane
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateAirplane(@PathVariable Long id, @RequestBody AirplaneRequest request) {
        try {
            String result = airplaneService.updateAirplane(id, request);
            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to update airplane: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteAirplane(@PathVariable Long id) {
        boolean exists = airplaneService.doesAirplaneExist(id);
        
        if (!exists) {
            return new ResponseEntity<>(new MessageResponse("Airplane not found."), HttpStatus.NOT_FOUND);
        }

        try {
            String result = airplaneService.deleteAirplane(id);
            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to delete airplane: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }


    // Get all Airplanes
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<Object> getAllAirplanes() {
        try {
            List<Airplane> airplanes = airplaneService.getAllAirplanes();
            if (airplanes.isEmpty()) {
                return new ResponseEntity<>(new MessageResponse("No airplanes found"), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(airplanes);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airplanes: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Get Airplane by ID
    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getAirplaneById(@PathVariable Long id) {
        try {
            Airplane airplane = airplaneService.getAirplaneById(id);
            if (airplane == null) {
                return new ResponseEntity<>(new MessageResponse("Airplane not found"), HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(airplane);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airplane: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // MessageResponse class to standardize responses
    public static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
