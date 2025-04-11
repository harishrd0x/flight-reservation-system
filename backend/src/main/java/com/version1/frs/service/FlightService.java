package com.version1.frs.service;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    void addFlight(FlightRequest request);
    void updateFlight(Long id, FlightRequest request);
    void deleteFlight(Long id);
    List<FlightResponse> getAllFlights();
    FlightResponse getFlightById(Long id);
    List<FlightResponse> searchFlights(String source, String destination, LocalDate date);
}