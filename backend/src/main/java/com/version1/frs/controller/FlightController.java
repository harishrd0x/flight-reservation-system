package com.version1.frs.controller;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.model.Flight;
import com.version1.frs.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    @Autowired
    private FlightService flightService;

    @PostMapping
    public ResponseEntity<String> addFlight(@RequestBody FlightRequest request) {
        flightService.addFlight(request);
        return ResponseEntity.ok("Flight added successfully");
    }

    @GetMapping("/search")
    public List<Flight> searchFlights(@RequestParam String source,
                                      @RequestParam String destination,
                                      @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return flightService.searchFlights(source, destination, date);
    }
}