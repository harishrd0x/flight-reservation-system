package com.version1.frs.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.version1.frs.model.Airplane;

@Repository
public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
    
    Optional<Airplane> findByAirplaneNumber(String airplaneNumber);
    
    boolean existsByAirplaneNumber(String airplaneNumber);
    
    List<Airplane> findByAirplaneNameContainingIgnoreCase(String keyword);
    
    List<Airplane> findByManufacturerIgnoreCase(String manufacturer);
    
    List<Airplane> findByAirplaneModelIgnoreCase(String model);
    
    List<Airplane> findByCapacityGreaterThanEqual(int capacity);
    
    List<Airplane> findByCapacityBetween(int min, int max);
}
