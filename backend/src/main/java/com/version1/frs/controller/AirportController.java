package com.version1.frs.controller;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.model.Airport;
import com.version1.frs.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airports")
public class AirportController {

    @Autowired
    private AirportService airportService;

    @PostMapping
    public ResponseEntity<String> addAirport(@RequestBody AirportRequest request,
                                             @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Access denied. Only ADMINs can add airports.");
        }

        return ResponseEntity.ok(airportService.addAirport(request));
    }
    @GetMapping
	public ResponseEntity<List<Airport>> getAllAirports() {
		return ResponseEntity.ok(airportService.getAllAirports());
	}

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAirport(@PathVariable int id,
                                                @RequestBody AirportRequest request,
                                                @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Access denied. Only ADMINs can update airports.");
        }

        return ResponseEntity.ok(airportService.updateAirport(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirport(@PathVariable int id,
                                                @RequestParam String userRole) {
        if (!userRole.equalsIgnoreCase("ADMIN")) {
            return ResponseEntity.status(403).body("Access denied. Only ADMINs can delete airports.");
        }

        airportService.deleteAirport(id);
        return ResponseEntity.ok("Airport deleted successfully.");
    }
}