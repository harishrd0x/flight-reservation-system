//package com.version1.frs.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.version1.frs.dto.FlightRequest;
//import com.version1.frs.dto.FlightResponse;
//import com.version1.frs.service.FlightService;
//
//@RestController
//@RequestMapping("/api/admin/flights")
//@PreAuthorize("hasRole('ADMIN')")
//public class AdminFlightController {
//
//    @Autowired
//    private FlightService flightService;
//
//    @PostMapping
//    public ResponseEntity<String> addFlight(@RequestBody FlightRequest request) {
//        flightService.addFlight(request);
//        return ResponseEntity.ok("Flight added successfully.");
//    }
//
//    @PutMapping("/{id}")
//    public ResponseEntity<String> updateFlight(@PathVariable String flightNumber, @RequestBody FlightRequest request) {
//        flightService.updateFlight(flightNumber, request);
//        return ResponseEntity.ok("Flight updated successfully.");
//    }
//
//    @DeleteMapping("/{flightNumber}")
//    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
//        flightService.deleteFlight(flightNumber);
//        return ResponseEntity.ok("Flight deleted successfully.");
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FlightResponse>> getAllFlights() {
//        return ResponseEntity.ok(flightService.getAllFlights());
//    }
//
//    @GetMapping("/{id}")
//    public ResponseEntity<FlightResponse> getFlightById(@PathVariable String flightNumber) {
//        return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
//    }
//}