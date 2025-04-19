package com.version1.frs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, String> {
//    boolean existsByAirportCode(String airportCode);  // Check existence by airport code
//    Optional<Airport> findByAirportCode(String airportCode);  // Find airport by airport code
//    List<Airport> findByAirport_CityContainingIgnoreCase(String cityName);
}