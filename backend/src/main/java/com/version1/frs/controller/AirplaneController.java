package com.version1.frs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.model.Airplane;
import com.version1.frs.service.AirplaneService;

@RestController
public class AirplaneController {
	
    private AirplaneService airplaneService;
        @Autowired
        public AirplaneController(AirplaneService airplaneService) {
        	        this.airplaneService = airplaneService;
        }
       
      @PostMapping("/airplane")
	  public String addAirplane(@RequestBody Airplane airplane) {
    	              return airplaneService.addAirplane(airplane);
    	  
		
	  }
      
      @DeleteMapping("/airplane/{id}")
              public String deleteAirplane(@PathVariable Long id) {
        	              return airplaneService.deleteAirplane(id);
      }
      @GetMapping("/airplanes")
		public Iterable<Airplane> getAllAirplanes() {
			return airplaneService.getAllAirplanes();
		}
       @GetMapping("/airplane/get/{id}")
                  public Airplane getAirplaneById(@PathVariable Long id) {
        	              return airplaneService.getAirplaneById(id);
                  }
          }
