package com.version1.frs.service.impl;

import java.util.List;
import java.util.Optional;

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
		response.setAirportId(airport.getAirportId());
		response.setAirportName(airport.getAirportName());
		response.setAirportCode(airport.getAirportCode());
		response.setAirportCity(airport.getAirportCity());
		response.setAirportState(airport.getAirportState());
		response.setAirportCountry(airport.getAirportCountry());
		return response;
	}

	@Override
	public AirportResponse addAirport(AirportRequest request) {
		Airport airport = new Airport();
		airport.setAirportName(request.getAirportName());
		airport.setAirportCode(request.getAirportCode());
		airport.setAirportCity(request.getAirportCity());
		airport.setAirportState(request.getAirportState());
		airport.setAirportCountry(request.getAirportCountry());
		return mapToResponse(airportRepository.save(airport));
	}

	@Override
	public AirportResponse updateAirport(int id, AirportRequest request) {
		Airport airport = airportRepository.findById(id).orElseThrow(() -> new RuntimeException("Airport not found"));
		airport.setAirportName(request.getAirportName());
		airport.setAirportCode(request.getAirportCode());
		airport.setAirportCity(request.getAirportCity());
		airport.setAirportState(request.getAirportState());
		airport.setAirportCountry(request.getAirportCountry());
		return mapToResponse(airportRepository.save(airport));
	}

	@Override
	public void deleteAirport(int id) {
		airportRepository.deleteById(id);
	}

	@Override
	public List<AirportResponse> getAllAirports() {
		return airportRepository.findAll().stream().map(this::mapToResponse).toList();
	}

    @Override
    public Optional<AirportResponse> getAirportById(int id) {
        Optional<Airport> airport = airportRepository.findById(id);
        return airport.map(this::mapToResponse);
    }

    @Override
    public boolean doesAirportExist(int id) {
        return airportRepository.existsById(id);
    }

}