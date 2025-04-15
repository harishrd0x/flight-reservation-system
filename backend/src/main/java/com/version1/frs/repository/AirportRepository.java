package com.version1.frs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    boolean existsByAirportCode(String airportCode);
    Optional<Airport> findByAirportId(Long airportId);
}