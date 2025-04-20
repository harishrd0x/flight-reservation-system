package com.version1.frs.service.impl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;
import com.version1.frs.model.Airplane;
import com.version1.frs.model.Airport;
import com.version1.frs.model.Flight;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.repository.AirportRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;
    
    @Autowired
    private AirportRepository airportRepository;

    @Override
    public FlightResponse addFlight(FlightRequest flightRequest) {
        // Fetch airplane entity
        Airplane airplane = airplaneRepository.findById(flightRequest.getAirplaneId())
                .orElseThrow(() -> new IllegalArgumentException("Airplane not found"));

        // Fetch from and to airport entities
        Airport fromAirport = airportRepository.findById(flightRequest.getDepartureAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Departure airport not found"));
        
        Airport toAirport = airportRepository.findById(flightRequest.getArrivalAirportId())
                .orElseThrow(() -> new IllegalArgumentException("Arrival airport not found"));

        // Create the flight entity and set values
        Flight flight = new Flight();
        flight.setAirplane(airplane);
        flight.setDepartureTime(flightRequest.getDepartureTime());
        flight.setArrivalTime(flightRequest.getArrivalTime());
        flight.setFromAirport(fromAirport);  // Use the Airport object, not just the ID
        flight.setToAirport(toAirport);      // Use the Airport object, not just the ID
        flight.setPrice(flightRequest.getPrice());
        flight.setAirline(flightRequest.getAirline());

        flightRepository.save(flight);

        return mapToDto(flight);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
    	return flightRepository.findByDepartureTimeAfter(LocalDateTime.now()).stream()
    	        .map(this::mapToDto)
    	        .collect(Collectors.toList());
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

        // Check if the flight's departure time is in the future (not expired)
        if (flight.getDepartureTime().isBefore(LocalDateTime.now())) {
            throw new IllegalArgumentException("This flight has already expired.");
        }

        return mapToDto(flight);
    }
    
    @Override
    public void deleteFlight(Long id) {
        Flight flight = flightRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Flight not found with id: " + id));
        flightRepository.delete(flight);
    }
    
    @Override
    public List<FlightResponse> searchFlights(Long sourceId, Long destinationId, LocalDate date) {
        LocalDateTime startOfDay = null, endOfDay = null;
        if (date != null) {
            startOfDay = date.atStartOfDay();
            endOfDay   = date.atTime(23, 59, 59);
        }

        List<Flight> flights = flightRepository.searchFlights(
            sourceId, destinationId, startOfDay, endOfDay
        );

        // Filter out past flights in the service layer
        flights = flights.stream()
                         .filter(flight -> flight.getDepartureTime().isAfter(LocalDateTime.now()))
                         .collect(Collectors.toList());

        return flights.stream()
                      .map(this::mapToDto)
                      .collect(Collectors.toList());
    }




    private FlightResponse mapToDto(Flight flight) {
        FlightResponse response = new FlightResponse();
        response.setId(flight.getId());
        response.setAirline(flight.getAirline());
        response.setDepartureTime(flight.getDepartureTime().toString());
        response.setArrivalTime(flight.getArrivalTime().toString());

        // Map airport data (name or code)
        response.setFromAirportId(flight.getFromAirport().getId());
        response.setFromAirportName(flight.getFromAirport().getAirportName());  // Retrieve the airport name

        response.setToAirportId(flight.getToAirport().getId());
        response.setToAirportName(flight.getToAirport().getAirportName());  // Retrieve the airport name

        response.setPrice(flight.getPrice());  // If you need to convert BigDecimal to Double

        return response;
    }

}
