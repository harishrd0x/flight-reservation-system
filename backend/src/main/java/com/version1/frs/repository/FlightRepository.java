package com.version1.frs.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Airport;
import com.version1.frs.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
//    Optional<Flight> findByFlightNumber(String flightNumber);
//
//    List<Flight> findBySourceAirportAndDestinationAirportAndDepartureDate(
//        Airport sourceAirport,
//        Airport destinationAirport,
//        LocalDate departureDate
//    );
//    
//    List<Flight> findBySourceAirport_AirportCodeInAndDestinationAirport_AirportCodeIn(Set<String> sourceCodes, Set<String> destinationCodes);
//    List<Flight> findBySourceAirport_AirportCodeInAndDestinationAirport_AirportCodeInAndDepartureDate(
//    	    Set<String> sourceCodes,
//    	    Set<String> destinationCodes,
//    	    LocalDate departureDate
//    	);
}
