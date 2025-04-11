package com.version1.frs.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.model.Flight;
import com.version1.frs.service.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<String> addFlight(@RequestBody FlightRequest request,
                                            @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Access denied. Only ADMINs can add flights.");
        }

        flightService.addFlight(request);
        return ResponseEntity.ok("Flight added successfully.");
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String source,
                                      @RequestParam String destination,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.searchFlights(source, destination, date);
    }
}