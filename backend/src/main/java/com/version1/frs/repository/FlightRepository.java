package com.version1.frs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findBySourceAndDestinationAndDepartureDate(String source, String destination, LocalDate date);

	Optional<Flight> findBySourceAirport_AirportCodeAndDestinationAirport_AirportCodeAndDepartureDate(String source,
			String destination, LocalDate date);
}