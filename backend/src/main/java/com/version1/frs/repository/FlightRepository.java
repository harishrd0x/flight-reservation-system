package com.version1.frs.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.version1.frs.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {

	@Query("""
			SELECT f
			  FROM Flight f
			 WHERE (:sourceId       IS NULL OR f.fromAirport.id    = :sourceId)
			   AND (:destinationId  IS NULL OR f.toAirport.id      = :destinationId)
			   AND (:startOfDay     IS NULL OR f.departureTime    >= :startOfDay)
			   AND (:endOfDay       IS NULL OR f.departureTime    <= :endOfDay)
			""")
	List<Flight> searchFlights(@Param("sourceId") Long sourceId, @Param("destinationId") Long destinationId,
			@Param("startOfDay") LocalDateTime startOfDay, @Param("endOfDay") LocalDateTime endOfDay);
}
