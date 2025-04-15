package com.version1.frs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;

import com.version1.frs.model.Airplane;

public interface AirplaneRepository extends JpaRepository<Airplane, Long> {
	boolean existsByAirplaneId(@NonNull Long id);
}