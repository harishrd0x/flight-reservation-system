package com.version1.frs.repository;
 
import org.springframework.data.repository.CrudRepository;
import com.version1.frs.model.Airport;

public interface AirportRepository extends CrudRepository<Airport,String> {

}
