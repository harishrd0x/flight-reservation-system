package com.version1.frs.service;

import com.version1.frs.dto.AirportRequest;
import com.version1.frs.dto.AirportResponse;
import java.util.List;

public interface AirportService {
    
    String addAirport(AirportRequest request);

    String updateAirport(String airportCode, AirportRequest request);

    String deleteAirport(String airportCode);

    List<AirportResponse> getAllAirports();

    AirportResponse getAirportByCode(String airportCode);

    List<AirportResponse> getAirportsByCity(String city);

    List<AirportResponse> getAirportsByState(String state);

    List<AirportResponse> getAirportsByCountry(String country);

    boolean airportExists(String airportCode);

	List<AirportResponse> filterByState(String state);

	List<AirportResponse> filterByCity(String city);

	List<AirportResponse> filterByCountry(String country);

	List<AirportResponse> filterAirportsByState(String state);

	List<AirportResponse> filterAirportsByCountry(String country);
}
