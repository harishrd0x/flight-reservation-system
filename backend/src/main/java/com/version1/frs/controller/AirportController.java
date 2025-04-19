//package com.version1.frs.controller;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.CrossOrigin;
//import org.springframework.web.bind.annotation.DeleteMapping;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.version1.frs.dto.AirportRequest;
//import com.version1.frs.model.Airport;
//import com.version1.frs.service.AirportService;
//
//import jakarta.validation.Valid;
//
//@RestController
//@RequestMapping("/api/airports")
//@CrossOrigin
//public class AirportController {
//
//    @Autowired
//    private AirportService airportService;
//
//    // Add Airport
//    @PreAuthorize("hasRole('ADMIN')")
//    @PostMapping
//    public ResponseEntity<Object> addAirport(@Valid @RequestBody AirportRequest request) {
//        try {
//            String result = airportService.addAirport(request);
//            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.CREATED);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new MessageResponse("Failed to create airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Update Airport
//    @PreAuthorize("hasRole('ADMIN')")
//    @PutMapping("/{airportCode}")
//    public ResponseEntity<Object> updateAirport(@PathVariable String airportCode, @RequestBody AirportRequest request) {
//        try {
//            String result = airportService.updateAirport(airportCode, request);
//            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new MessageResponse("Failed to update airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Delete Airport
//    @PreAuthorize("hasRole('ADMIN')")
//    @DeleteMapping("/{airportCode}")
//    public ResponseEntity<Object> deleteAirport(@PathVariable String airportCode) {
//        try {
//            String result = airportService.deleteAirport(airportCode);
//            return new ResponseEntity<>(new MessageResponse(result), HttpStatus.OK);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new MessageResponse("Failed to delete airport: " + e.getMessage()), HttpStatus.BAD_REQUEST);
//        }
//    }
//
//    // Get All Airports
//    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
//    @GetMapping
//    public ResponseEntity<Object> getAllAirports() {
//        try {
//            List<Airport> airports = airportService.getAllAirports();
//            if (airports.isEmpty()) {
//                return new ResponseEntity<>(new MessageResponse("No airports found"), HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.ok(airports);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airports: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // Get Airport by Code
//    @PreAuthorize("hasAnyRole('ADMIN', 'CUSTOMER')")
//    @GetMapping("/{airportCode}")
//    public ResponseEntity<Object> getAirportByCode(@PathVariable String airportCode) {
//        try {
//            Airport airport = airportService.getAirportByCode(airportCode);
//            if (airport == null) {
//                return new ResponseEntity<>(new MessageResponse("Airport not found"), HttpStatus.NOT_FOUND);
//            }
//            return ResponseEntity.ok(airport);
//        } catch (Exception e) {
//            return new ResponseEntity<>(new MessageResponse("Failed to retrieve airport: " + e.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
//        }
//    }
//
//    // MessageResponse class to standardize responses
//    public static class MessageResponse {
//        private String message;
//
//        public MessageResponse(String message) {
//            this.message = message;
//        }
//
//        public String getMessage() {
//            return message;
//        }
//
//        public void setMessage(String message) {
//            this.message = message;
//        }
//    }
//}
