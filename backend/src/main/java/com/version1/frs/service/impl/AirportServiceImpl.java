package com.version1.frs.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;
import com.version1.frs.model.Airport;
import com.version1.frs.repository.AirportRepository;
import com.version1.frs.service.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

    @Autowired
    private AirportRepository airportRepository;

    private AirportResponse mapToResponse(Airport airport) {
        AirportResponse response = new AirportResponse();
        response.setAirportId(airport.getId());
        response.setAirportCode(airport.getAirportCode());
        response.setAirportName(airport.getAirportName());
        response.setAirportCity(airport.getAirportCity());
        response.setAirportState(airport.getAirportState());
        response.setAirportCountry(airport.getAirportCountry());
        return response;
    }

    private Airport mapToEntity(AirportRequest request) {
        Airport airport = new Airport();
        airport.setAirportCode(request.getAirportCode());
        airport.setAirportName(request.getAirportName());
        airport.setAirportCity(request.getAirportCity());
        airport.setAirportState(request.getAirportState());
        airport.setAirportCountry(request.getAirportCountry());
        return airport;
    }

    @Override
    public String addAirport(AirportRequest request) {
        if (airportRepository.findByAirportCode(request.getAirportCode()).isPresent()) {
            throw new RuntimeException("Airport already exists with code: " + request.getAirportCode());
        }
        airportRepository.save(mapToEntity(request));
        return "Airport added successfully.";
    }


    @Override
    public String updateAirport(String airportCode, AirportRequest request) {
        Optional<Airport> existing = airportRepository.findByAirportCode(airportCode);
        if (existing.isEmpty()) {
            throw new RuntimeException("Airport not found with code: " + airportCode);
        }
        Airport airport = mapToEntity(request);
        airport.setId(existing.get().getId()); // Set the existing ID to preserve it
        airportRepository.save(airport);
        return "Airport updated successfully.";
    }


    @Override
    public String deleteAirport(String airportCode) {
        Optional<Airport> airport = airportRepository.findByAirportCode(airportCode);
        if (airport.isEmpty()) {
            throw new RuntimeException("Airport not found with code: " + airportCode);
        }
        airportRepository.deleteById(airport.get().getId()); // Use the actual primary key (Long) here
        return "Airport deleted successfully.";
    }


    @Override
    public List<AirportResponse> getAllAirports() {
        return airportRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirportResponse getAirportByCode(String airportCode) {
        Airport airport = airportRepository.findByAirportCode(airportCode)
                .orElseThrow(() -> new RuntimeException("Airport not found with code: " + airportCode));
        return mapToResponse(airport);
    }
    
    public List<Airport> searchAirports(String query) {
        return airportRepository.searchAirports(query);
    }

    @Override
    public List<AirportResponse> filterByCity(String city) {
        return airportRepository.findByAirportCityIgnoreCase(city).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirportResponse> filterByState(String state) {
        return airportRepository.findByAirportStateIgnoreCase(state).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirportResponse> filterByCountry(String country) {
        return airportRepository.findByAirportCountryIgnoreCase(country).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

	@Override
	public List<AirportResponse> getAirportsByCity(String city) {
	    return airportRepository.findByAirportCityIgnoreCase(city).stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	@Override
	public List<AirportResponse> getAirportsByState(String state) {
	    return airportRepository.findByAirportStateIgnoreCase(state).stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	@Override
	public List<AirportResponse> getAirportsByCountry(String country) {
	    return airportRepository.findByAirportCountryIgnoreCase(country).stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	@Override
	public boolean airportExists(String airportCode) {
	    return airportRepository.findByAirportCode(airportCode).isPresent();
	}

	@Override
	public List<AirportResponse> filterAirportsByState(String state) {
	    return airportRepository.findByAirportStateIgnoreCase(state).stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}

	@Override
	public List<AirportResponse> filterAirportsByCountry(String country) {
	    return airportRepository.findByAirportCountryIgnoreCase(country).stream()
	            .map(this::mapToResponse)
	            .collect(Collectors.toList());
	}
}
