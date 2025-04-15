package com.version1.frs.service;

import java.time.LocalDate;
import java.util.List;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;
import com.version1.frs.model.Airport;

public interface FlightService {
    void addFlight(FlightRequest request);
    void updateFlight(Long id, FlightRequest request);
    void deleteFlight(Long id);
    List<FlightResponse> getAllFlights();
    FlightResponse getFlightById(Long id);
    List<FlightResponse> searchFlights(Airport source, Airport destination, LocalDate date);
}