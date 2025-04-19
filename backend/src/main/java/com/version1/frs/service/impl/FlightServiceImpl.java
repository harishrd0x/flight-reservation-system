//package com.version1.frs.service.impl;
//
//import java.time.LocalDate;
//import java.util.Collections;
//import java.util.List;
//import java.util.Set;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.version1.frs.dto.FlightRequest;
//import com.version1.frs.dto.FlightResponse;
//import com.version1.frs.model.Airplane;
//import com.version1.frs.model.Airport;
//import com.version1.frs.model.Flight;
//import com.version1.frs.repository.AirplaneRepository;
//import com.version1.frs.repository.AirportRepository;
//import com.version1.frs.repository.FlightRepository;
//import com.version1.frs.service.FlightService;
//
//@Service
//public class FlightServiceImpl implements FlightService {
//
//    @Autowired
//    private FlightRepository flightRepository;
//
//    @Autowired
//    private AirplaneRepository airplaneRepository;
//
//    @Autowired
//    private AirportRepository airportRepository;
//
//    @Override
//    public FlightResponse addFlight(FlightRequest request) {
//        Airplane airplane = airplaneRepository.findByAirplaneNumber(request.getAirplaneNumber())
//            .orElseThrow(() -> new RuntimeException("Airplane not found"));
//
//        Airport sourceAirport = airportRepository.findByAirportCode(request.getSourceAirportCode())
//            .orElseThrow(() -> new RuntimeException("Source airport not found"));
//
//        Airport destinationAirport = airportRepository.findByAirportCode(request.getDestinationAirportCode())
//            .orElseThrow(() -> new RuntimeException("Destination airport not found"));
//
//        Flight flight = new Flight();
//        flight.setFlightNumber(request.getFlightNumber());
//        flight.setDepartureDate(request.getDepartureDate());
//        flight.setDepartureTime(request.getDepartureTime());
//        flight.setArrivalDate(request.getArrivalDate());
//        flight.setArrivalTime(request.getArrivalTime());
//        flight.setPrice(request.getPrice());
//        flight.setAirplane(airplane);
//        flight.setSourceAirport(sourceAirport);
//        flight.setDestinationAirport(destinationAirport);
//
//        flightRepository.save(flight);
//        return mapToResponse(flight);
//    }
//
//    @Override
//    public FlightResponse updateFlight(String flightNumber, FlightRequest request) {
//        Flight flight = flightRepository.findByFlightNumber(flightNumber)
//            .orElseThrow(() -> new RuntimeException("Flight not found"));
//
//        Airplane airplane = airplaneRepository.findByAirplaneNumber(request.getAirplaneNumber())
//            .orElseThrow(() -> new RuntimeException("Airplane not found"));
//
//        Airport sourceAirport = airportRepository.findByAirportCode(request.getSourceAirportCode())
//            .orElseThrow(() -> new RuntimeException("Source airport not found"));
//
//        Airport destinationAirport = airportRepository.findByAirportCode(request.getDestinationAirportCode())
//            .orElseThrow(() -> new RuntimeException("Destination airport not found"));
//
//        flight.setDepartureDate(request.getDepartureDate());
//        flight.setDepartureTime(request.getDepartureTime());
//        flight.setArrivalDate(request.getArrivalDate());
//        flight.setArrivalTime(request.getArrivalTime());
//        flight.setPrice(request.getPrice());
//        flight.setAirplane(airplane);
//        flight.setSourceAirport(sourceAirport);
//        flight.setDestinationAirport(destinationAirport);
//
//        flightRepository.save(flight);
//        return mapToResponse(flight);
//    }
//
//    @Override
//    public void deleteFlight(String flightNumber) {
//        Flight flight = flightRepository.findByFlightNumber(flightNumber)
//            .orElseThrow(() -> new RuntimeException("Flight not found"));
//        flightRepository.delete(flight);
//    }
//
//    @Override
//    public List<FlightResponse> getAllFlights() {
//        return flightRepository.findAll().stream()
//            .map(this::mapToResponse)
//            .collect(Collectors.toList());
//    }
//
//    @Override
//    public FlightResponse getFlightByFlightNumber(String flightNumber) {
//        Flight flight = flightRepository.findByFlightNumber(flightNumber)
//            .orElseThrow(() -> new RuntimeException("Flight not found"));
//        return mapToResponse(flight);
//    }
//
//    @Override
//    public List<FlightResponse> searchFlights(String sourceCity, String destinationCity, LocalDate date) {
//        List<Airport> sourceAirports = airportRepository.findByNameContainingIgnoreCase(sourceCity);
//        List<Airport> destAirports = airportRepository.findByNameContainingIgnoreCase(destinationCity);
//
//        if (sourceAirports.isEmpty() || destAirports.isEmpty()) {
//            return Collections.emptyList();
//        }
//
//        Set<String> sourceCodes = sourceAirports.stream().map(Airport::getAirportCode).collect(Collectors.toSet());
//        Set<String> destCodes = destAirports.stream().map(Airport::getAirportCode).collect(Collectors.toSet());
//
//        List<Flight> flights;
//        if (date != null) {
//            flights = flightRepository.findBySourceAirport_AirportCodeInAndDestinationAirport_AirportCodeInAndDepartureDate(
//                sourceCodes, destCodes, date
//            );
//        } else {
//            flights = flightRepository.findBySourceAirport_AirportCodeInAndDestinationAirport_AirportCodeIn(
//                sourceCodes, destCodes
//            );
//        }
//
//        return flights.stream().map(this::mapToResponse).collect(Collectors.toList());
//    }
//
//    private FlightResponse mapToResponse(Flight flight) {
//        FlightResponse response = new FlightResponse();
//        response.setFlightNumber(flight.getFlightNumber());
//        response.setDepartureDate(flight.getDepartureDate());
//        response.setDepartureTime(flight.getDepartureTime());
//        response.setArrivalDate(flight.getArrivalDate());
//        response.setArrivalTime(flight.getArrivalTime());
//        response.setPrice(flight.getPrice());
//        response.setAirplaneNumber(flight.getAirplane().getAirplaneNumber());
//        response.setSourceAirportCode(flight.getSourceAirport().getAirportCode());
//        response.setDestinationAirportCode(flight.getDestinationAirport().getAirportCode());
//        return response;
//    }
//}
