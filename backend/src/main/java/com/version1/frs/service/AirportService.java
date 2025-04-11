package com.version1.frs.service;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.model.Airport;

import java.util.List;

public interface AirportService {
    String addAirport(AirportRequest request);
    List<Airport> getAllAirports();
    String updateAirport(int id, AirportRequest request);
    void deleteAirport(int id);
}