package com.version1.frs.repository;
 
import org.springframework.data.repository.CrudRepository;
 
import com.version1.frs.model.Bookings;
 
public interface BookingRepository extends CrudRepository<Bookings, Long> {

}
