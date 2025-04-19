//package com.version1.frs.service;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import com.version1.frs.dto.FlightRequest;
//import com.version1.frs.dto.FlightResponse;
//
//public interface FlightService {
//
//    FlightResponse addFlight(FlightRequest request);
//
//    FlightResponse updateFlight(String flightNumber, FlightRequest request);
//
//    void deleteFlight(String flightNumber);
//
//    List<FlightResponse> getAllFlights();
//
//    FlightResponse getFlightByFlightNumber(String flightNumber);
//
//    List<FlightResponse> searchFlights(String sourceCity, String destinationCity, LocalDate date);
//}
