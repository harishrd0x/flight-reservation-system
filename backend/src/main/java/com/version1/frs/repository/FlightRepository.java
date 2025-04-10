package com.version1.frs.repository;

import com.version1.frs.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, LocalDate date);
}