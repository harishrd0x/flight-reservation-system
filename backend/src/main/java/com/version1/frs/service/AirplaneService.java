package com.version1.frs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.model.Airplane;
import com.version1.frs.repository.AirplaneRepository;

@Service
public class AirplaneService {
	
  private AirplaneRepository airplaneRepository;
    @Autowired
	public AirplaneService(AirplaneRepository airplaneRepository) {
		this.airplaneRepository = airplaneRepository;
	}
    
	public String addAirplane(Airplane airplane) {
		
		airplaneRepository.save(airplane);
		return "Airplane added successfully";
	}
	
	public String deleteAirplane(Long id) {
		airplaneRepository.deleteById(id);
		return "Airplane deleted successfully";
	}
	
	public Iterable<Airplane> getAllAirplanes() {
		return airplaneRepository.findAll();
	}
	
	public Airplane getAirplaneById(Long id) {
		return airplaneRepository.findById(id).orElse(null);
	}

}
