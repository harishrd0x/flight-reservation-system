package com.version1.frs.service;

import java.util.List;
import java.util.Optional;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;

public interface AirportService {
    AirportResponse addAirport(AirportRequest request);
    AirportResponse updateAirport(int airportId, AirportRequest request);
    void deleteAirport(int airportId);
    List<AirportResponse> getAllAirports();
    Optional<AirportResponse> getAirportById(int airportId);
	boolean doesAirportExist(int id);
}