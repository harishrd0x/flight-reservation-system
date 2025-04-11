package com.version1.frs.service.impl;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.model.Airport;
import com.version1.frs.repository.AirportRepository;
import com.version1.frs.service.AirportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    @Override
    public String addAirport(AirportRequest request) {
        if (airportRepository.existsByAirportCode(request.getAirportCode())) {
            return "Airport with this code already exists.";
        }

        Airport airport = new Airport();
        airport.setAirportName(request.getAirportName());
        airport.setAirportCode(request.getAirportCode());
        airport.setAirportCity(request.getAirportCity());
        airport.setAirportState(request.getAirportState());
        airport.setAirportCountry(request.getAirportCountry());

        airportRepository.save(airport);
        return "Airport added successfully.";
    }

    @Override
    public List<Airport> getAllAirports() {
        return airportRepository.findAll();
    }

    @Override
    public String updateAirport(int id, AirportRequest request) {
        Optional<Airport> optionalAirport = airportRepository.findById(id);
        if (optionalAirport.isPresent()) {
            Airport airport = optionalAirport.get();
            airport.setAirportName(request.getAirportName());
            airport.setAirportCode(request.getAirportCode());
            airport.setAirportCity(request.getAirportCity());
            airport.setAirportState(request.getAirportState());
            airport.setAirportCountry(request.getAirportCountry());
            airportRepository.save(airport);
            return "Airport updated successfully.";
        }
        return "Airport not found.";
    }
    
    @Override
    public void deleteAirport(int id) {
        airportRepository.deleteById(id);
    }
}