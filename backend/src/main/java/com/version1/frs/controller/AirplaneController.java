package com.version1.frs.controller;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;
import com.version1.frs.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/airplanes")
public class AirplaneController {

    @Autowired
    private AirplaneService airplaneService;

    @PostMapping
    public ResponseEntity<String> addAirplane(@RequestBody AirplaneRequest request) {
        return ResponseEntity.ok(airplaneService.addAirplane(request));
    }

    @GetMapping
    public ResponseEntity<List<Airplane>> getAllAirplanes() {
        return ResponseEntity.ok(airplaneService.getAllAirplanes());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airplane> getAirplaneById(@PathVariable Long id) {
        return ResponseEntity.ok(airplaneService.getAirplaneById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateAirplane(@PathVariable Long id, @RequestBody AirplaneRequest request) {
        return ResponseEntity.ok(airplaneService.updateAirplane(id, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteAirplane(@PathVariable Long id) {
        return ResponseEntity.ok(airplaneService.deleteAirplane(id));
    }
}