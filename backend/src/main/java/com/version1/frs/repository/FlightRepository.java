package com.version1.frs.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Airport;
import com.version1.frs.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
	List<Flight> findBySourceAirportAndDestinationAirportAndDepartureDate(
		Airport sourceAirport,
		Airport destinationAirport,
		LocalDate departureDate
	);
}
