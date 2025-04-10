package com.version1.frs.controller;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
 
import com.version1.frs.model.Airport;
import com.version1.frs.repository.AirportRepository;
 
 
@RestController
@CrossOrigin
public class AirportController {
	
	private AirportRepository airportRepository;
	public AirportController(AirportRepository airportRepository) {
	this.airportRepository = airportRepository;
	}
	@PostMapping("/add")
	public String doCreate(@RequestBody Airport airport) {
		airportRepository.save(airport);
		return "Airport are add Successfully";
	}
	@PutMapping("/update")
	public String doUpdate(@RequestBody Airport airport) {
		airportRepository.save(airport);
		return "Airport are updated Successfully";
	}
	@DeleteMapping("/delete/{code}")
   public String doDelete(@PathVariable String code) {
	   airportRepository.deleteById(code);
	   return "Airport deleted successfully";

	}
	@GetMapping("/getAll")
   public Iterable<Airport> doList() {
	return airportRepository.findAll();
}
}
