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
import org.springframework.web.bind.annotation.RequestParam;
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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<Object> addAirport(@Valid @RequestBody AirportRequest request) {
        try {
            String result = airportService.addAirport(request);
            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to create airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{airportCode}")
    public ResponseEntity<Object> updateAirport(@PathVariable String airportCode, @RequestBody AirportRequest request) {
        try {
            String result = airportService.updateAirport(airportCode, request);
            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to update airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{airportCode}")
    public ResponseEntity<Object> deleteAirport(@PathVariable String airportCode) {
        try {
            String result = airportService.deleteAirport(airportCode);
            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to delete airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping
    public ResponseEntity<Object> getAllAirports() {
        try {
            List<AirportResponse> airports = airportService.getAllAirports();
            return airports.isEmpty()
                ? new ResponseEntity<>(new MessageResponse("No airports found"), HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(airports);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airports: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/{airportCode}")
    public ResponseEntity<Object> getAirportByCode(@PathVariable String airportCode) {
        try {
            AirportResponse airport = airportService.getAirportByCode(airportCode);
            return airport == null
                ? new ResponseEntity<>(new MessageResponse("Airport not found"), HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(airport);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airport: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/city")
    public ResponseEntity<Object> filterAirportsByCity(@RequestParam String city) {
        try {
            List<AirportResponse> airports = airportService.getAirportsByCity(city);
            return airports.isEmpty()
                ? new ResponseEntity<>(new MessageResponse("No airports found for the given city"), HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(airports);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to filter airports by city: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/state")
    public ResponseEntity<Object> filterAirportsByState(@RequestParam String state) {
        try {
            List<AirportResponse> airports = airportService.filterAirportsByState(state);
            return airports.isEmpty()
                ? new ResponseEntity<>(new MessageResponse("No airports found for the given state"), HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(airports);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to filter airports by state: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
    @GetMapping("/country")
    public ResponseEntity<Object> filterAirportsByCountry(@RequestParam String country) {
        try {
            List<AirportResponse> airports = airportService.filterAirportsByCountry(country);
            return airports.isEmpty()
                ? new ResponseEntity<>(new MessageResponse("No airports found for the given country"), HttpStatus.NOT_FOUND)
                : ResponseEntity.ok(airports);
        } catch (Exception e) {
            return new ResponseEntity<>(new MessageResponse("Failed to filter airports by country: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

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