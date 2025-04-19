//package com.version1.frs.controller;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.format.annotation.DateTimeFormat;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//import com.version1.frs.dto.FlightRequest;
//import com.version1.frs.dto.FlightResponse;
//import com.version1.frs.service.FlightService;
//
//@RestController
//@RequestMapping("/api/flights")
//public class FlightController {
//
//    @Autowired
//    private FlightService flightService;
//
//    @PostMapping
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<FlightResponse> addFlight(@RequestBody FlightRequest request) {
//        FlightResponse createdFlight = flightService.addFlight(request);
//        return new ResponseEntity<>(createdFlight, HttpStatus.CREATED);
//    }
//
//    @GetMapping
//    public ResponseEntity<List<FlightResponse>> getAllFlights() {
//        return ResponseEntity.ok(flightService.getAllFlights());
//    }
//
//    @GetMapping("/{flightNumber}")
//    public ResponseEntity<FlightResponse> getFlightByFlightNumber(@PathVariable String flightNumber) {
//        return ResponseEntity.ok(flightService.getFlightByFlightNumber(flightNumber));
//    }
//
//    @PutMapping("/{flightNumber}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<FlightResponse> updateFlight(
//            @PathVariable String flightNumber,
//            @RequestBody FlightRequest request) {
//        FlightResponse updatedFlight = flightService.updateFlight(flightNumber, request);
//        return ResponseEntity.ok(updatedFlight);
//    }
//
//    @DeleteMapping("/{flightNumber}")
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<String> deleteFlight(@PathVariable String flightNumber) {
//        flightService.deleteFlight(flightNumber);
//        return ResponseEntity.ok("Flight deleted successfully");
//    }
//
//    @GetMapping("/search")
//    public ResponseEntity<List<FlightResponse>> searchFlights(
//            @RequestParam String sourceCity,
//            @RequestParam String destinationCity,
//            @RequestParam(required = false) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate date) {
//
//        if (date == null) {
//            date = LocalDate.now();
//        }
//
//        return ResponseEntity.ok(flightService.searchFlights(sourceCity, destinationCity, date));
//    }
//}
