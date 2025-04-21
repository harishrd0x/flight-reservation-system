package com.version1.frs.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.version1.frs.model.Flight;

/**
 * Repository interface for managing {@link Flight} entities. Provides standard
 * CRUD operations along with custom query methods for filtering flights based
 * on criteria like source, destination, and time.
 */
public interface FlightRepository extends JpaRepository<Flight, Long> {

	/**
	 * Searches for flights based on optional filters like source airport,
	 * destination airport, and departure time window.
	 *
	 * @param sourceId      the ID of the source airport (nullable)
	 * @param destinationId the ID of the destination airport (nullable)
	 * @param startOfDay    the earliest allowed departure time (nullable)
	 * @param endOfDay      the latest allowed departure time (nullable)
	 * @return a list of matching {@link Flight} entities
	 */
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

	/**
	 * Retrieves all flights with a departure time after the specified time.
	 *
	 * @param now the lower bound for departure time
	 * @return a list of future {@link Flight} entities
	 */
	List<Flight> findByDepartureTimeAfter(LocalDateTime now);
}
