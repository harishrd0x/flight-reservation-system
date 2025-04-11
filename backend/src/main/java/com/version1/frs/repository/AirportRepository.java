package com.version1.frs.repository;

import com.version1.frs.model.Airport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AirportRepository extends JpaRepository<Airport, Integer> {
    boolean existsByAirportCode(String airportCode);
}