package com.version1.frs.service.impl;

import com.version1.frs.dto.FlightRequest;
import com.version1.frs.model.Flight;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.service.FlightService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FlightServiceImpl implements FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public void addFlight(FlightRequest request) {
        Flight flight = new Flight();
        flight.setFlightNumber(request.getFlightNumber());
        flight.setAirline(request.getAirline());
        flight.setSource(request.getSource());
        flight.setDestination(request.getDestination());
        flight.setDepartureDate(request.getDepartureDate());
        flight.setDepartureTime(request.getDepartureTime());
        flight.setArrivalTime(request.getArrivalTime());
        flight.setTotalSeats(request.getTotalSeats());
        flight.setPrice(request.getPrice());

        flightRepository.save(flight);
    }

    @Override
    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flightRepository.findBySourceAndDestinationAndDepartureDate(source, destination, date);
    }
}