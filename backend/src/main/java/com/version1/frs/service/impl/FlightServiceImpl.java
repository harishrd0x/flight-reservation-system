package com.version1.frs.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.dto.FlightResponse;
import com.version1.frs.model.Airplane;
import com.version1.frs.model.Flight;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.service.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public void addFlight(FlightRequest request) {
        Flight flight = mapToEntity(request);
        flightRepository.save(flight);
    }

    @Override
    public void updateFlight(Long id, FlightRequest request) {
        Optional<Flight> optional = flightRepository.findById(id);
        if (optional.isPresent()) {
            Flight flight = optional.get();
            flight.setFlightNumber(request.getFlightNumber());
            flight.setSource(request.getSource());
            flight.setDestination(request.getDestination());
            flight.setDepartureDate(request.getDepartureDate());
            flight.setDepartureTime(request.getDepartureTime());
            flight.setArrivalTime(request.getArrivalTime());
            flight.setPrice(request.getPrice());

            Airplane airplane = airplaneRepository.findById(request.getAirplaneId())
                    .orElseThrow(() -> new RuntimeException("Airplane not found"));
            flight.setAirplane(airplane);

            flightRepository.save(flight);
        }
    }

    @Override
    public void deleteFlight(Long id) {
        flightRepository.deleteById(id);
    }

    @Override
    public List<FlightResponse> getAllFlights() {
        return flightRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public FlightResponse getFlightById(Long id) {
        Flight flight = flightRepository.findById(id).orElseThrow(() -> new RuntimeException("Flight not found"));
        return mapToResponse(flight);
    }

    @Override
    public List<FlightResponse> searchFlights(String source, String destination, LocalDate date) {
        return flightRepository.findBySourceAndDestinationAndDepartureDate(source, destination, date)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // === Mapper ===
    private Flight mapToEntity(FlightRequest request) {
        Flight flight = new Flight();
        flight.setFlightNumber(request.getFlightNumber());
        flight.setSource(request.getSource());
        flight.setDestination(request.getDestination());
        flight.setDepartureDate(request.getDepartureDate());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setPrice(request.getPrice());

        Airplane airplane = airplaneRepository.findById(request.getAirplaneId())
                .orElseThrow(() -> new RuntimeException("Airplane not found"));
        flight.setAirplane(airplane);

        return flight;
    }

    private FlightResponse mapToResponse(Flight flight) {
        FlightResponse res = new FlightResponse();
        res.setFlightId(flight.getId());
        res.setFlightNumber(flight.getFlightNumber());
        res.setSource(flight.getSource());
        res.setDestination(flight.getDestination());
        res.setDepartureDate(flight.getDepartureDate());
        res.setDepartureTime(flight.getDepartureTime());
        res.setArrivalTime(flight.getArrivalTime());
        res.setPrice(flight.getPrice());
        res.setAirplaneName(flight.getAirplane().getName());
        return res;
    }
}