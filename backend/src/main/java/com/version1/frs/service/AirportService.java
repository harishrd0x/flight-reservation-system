package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;

public interface AirportService {
    AirportResponse addAirport(AirportRequest request);
    AirportResponse updateAirport(int airportId, AirportRequest request);
    void deleteAirport(int airportId);
    List<AirportResponse> getAllAirports();
    AirportResponse getAirportById(int airportId);
}