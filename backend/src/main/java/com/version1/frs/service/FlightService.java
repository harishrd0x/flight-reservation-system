package com.version1.frs.service;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.model.Flight;

import java.time.LocalDate;
import java.util.List;

public interface FlightService {
    void addFlight(FlightRequest request);
    List<Flight> searchFlights(String source, String destination, LocalDate date);
}