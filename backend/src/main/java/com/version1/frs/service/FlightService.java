package com.version1.frs.service;

import java.time.LocalDate;
import java.util.List;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;

public interface FlightService {

    FlightResponse addFlight(FlightRequest flightRequest);

    List<FlightResponse> getAllFlights();

    FlightResponse getFlightById(Long id);
    
    void deleteFlight(Long id);
    
    List<FlightResponse> searchFlights(Long sourceId, Long destinationId, LocalDate date);

}
