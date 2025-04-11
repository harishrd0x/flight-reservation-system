package com.version1.frs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;
import com.version1.frs.service.AirplaneService;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

	@Autowired
	private AirplaneService airplaneService;

	@PostMapping
	public ResponseEntity<String> addAirplane(@RequestBody AirplaneRequest request, @RequestParam String userRole) {
		if (!userRole.equalsIgnoreCase("ADMIN")) {
			return ResponseEntity.status(403).body("Access denied. Only ADMINs can add airplanes.");
		}

		return ResponseEntity.ok(airplaneService.addAirplane(request));
	}

	@PutMapping("/{id}")
	public ResponseEntity<String> updateAirplane(@PathVariable Long id, @RequestBody AirplaneRequest request,
			@RequestParam String userRole) {
		if (!userRole.equalsIgnoreCase("ADMIN")) {
			return ResponseEntity.status(403).body("Access denied. Only ADMINs can update airplanes.");
		}

		return ResponseEntity.ok(airplaneService.updateAirplane(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteAirplane(@PathVariable Long id, @RequestParam String userRole) {
		if (!userRole.equalsIgnoreCase("ADMIN")) {
			return ResponseEntity.status(403).body("Access denied. Only ADMINs can delete airplanes.");
		}

		return ResponseEntity.ok(airplaneService.deleteAirplane(id));
	}

	@GetMapping
	public ResponseEntity<List<Airplane>> getAllAirplanes() {
		return ResponseEntity.ok(airplaneService.getAllAirplanes());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
		return ResponseEntity.ok(airplaneService.getAirplaneById(id));
	}
}