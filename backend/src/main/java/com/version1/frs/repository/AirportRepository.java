package com.version1.frs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.version1.frs.model.Airport;

public interface AirportRepository extends JpaRepository<Airport, Long> {

    // Method to find Airport by its unique airportCode (non-primary key)
    Optional<Airport> findByAirportCode(String airportCode);

    List<Airport> findByAirportCityIgnoreCase(String city);

    List<Airport> findByAirportStateIgnoreCase(String state);

    List<Airport> findByAirportCountryIgnoreCase(String country);

    @Query("SELECT a FROM Airport a WHERE " +
    	       "LOWER(a.airportName) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    	       "LOWER(a.airportCity) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    	       "LOWER(a.airportCountry) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
    	       "LOWER(a.airportCode) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    	List<Airport> searchAirports(@Param("keyword") String keyword);


}
