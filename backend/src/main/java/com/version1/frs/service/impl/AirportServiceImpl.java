//package com.version1.frs.service.impl;
//
//import java.util.List;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.version1.frs.dto.AirportRequest;
//import com.version1.frs.model.Airport;
//import com.version1.frs.repository.AirportRepository;
//import com.version1.frs.service.AirportService;
//
//@Service
//public class AirportServiceImpl implements AirportService {
//
//    @Autowired
//    private AirportRepository airportRepository;
//
//    @Override
//    public String addAirport(AirportRequest request) {
//        // Check if the airport already exists by code
//        if (airportRepository.existsByAirportCode(request.getAirportCode())) {
//            return "Airport with this code already exists.";
//        }
//
//        Airport airport = new Airport();
//        airport.setAirportName(request.getAirportName());
//        airport.setAirportCode(request.getAirportCode());
//        airport.setAirportCity(request.getAirportCity());
//        airport.setAirportState(request.getAirportState());
//        airport.setAirportCountry(request.getAirportCountry());
//
//        airportRepository.save(airport);
//        return "Airport added successfully.";
//    }
//
//    @Override
//    public String updateAirport(String airportCode, AirportRequest request) {
//        Airport airport = airportRepository.findByAirportCode(airportCode)
//                .orElseThrow(() -> new RuntimeException("Airport not found"));
//
//        airport.setAirportName(request.getAirportName());
//        airport.setAirportCode(request.getAirportCode());
//        airport.setAirportCity(request.getAirportCity());
//        airport.setAirportState(request.getAirportState());
//        airport.setAirportCountry(request.getAirportCountry());
//
//        airportRepository.save(airport);
//        return "Airport updated successfully.";
//    }
//
//    @Override
//    public String deleteAirport(String airportCode) {
//        if (!airportRepository.existsByAirportCode(airportCode)) {
//            return "Airport not found.";
//        }
//
//        airportRepository.deleteById(airportCode);
//        return "Airport deleted successfully.";
//    }
//
//    @Override
//    public List<Airport> getAllAirports() {
//        return airportRepository.findAll();
//    }
//
//    @Override
//    public Airport getAirportByCode(String airportCode) {
//        return airportRepository.findByAirportCode(airportCode)
//                .orElseThrow(() -> new RuntimeException("Airport not found"));
//    }
//
//    @Override
//    public boolean doesAirportExist(String airportCode) {
//        return airportRepository.existsByAirportCode(airportCode);
//    }
//}
