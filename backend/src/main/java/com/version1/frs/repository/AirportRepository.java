package com.version1.frs.repository;
 
import org.springframework.data.repository.CrudRepository;
import com.version1.frs.model.Airport;

public interface AirportRepository extends CrudRepository<Airport, Integer> {
	// This interface extends CrudRepository to provide CRUD operations for the
	// Airport entity.
	// The generic types are <Airport, Long>, where Airport is the entity type and
	// Long is the type of its primary key.

}
