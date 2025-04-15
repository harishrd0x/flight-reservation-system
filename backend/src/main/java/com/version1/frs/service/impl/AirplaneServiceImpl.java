package com.version1.frs.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.service.AirplaneService;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public String addAirplane(AirplaneRequest request) {
        Airplane airplane = new Airplane();
        airplane.setAirplaneName(request.getAirplaneName());
        airplane.setAirplaneModel(request.getAirplaneModel());
        airplane.setManufacturer(request.getManufacturer());
        airplane.setCapacity(request.getCapacity());

        airplaneRepository.save(airplane);
        return "Airplane added successfully.";
    }

    @Override
    public String updateAirplane(Long id, AirplaneRequest request) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found"));

        airplane.setAirplaneName(request.getAirplaneName());
        airplane.setAirplaneModel(request.getAirplaneModel());
        airplane.setManufacturer(request.getManufacturer());
        airplane.setCapacity(request.getCapacity());

        airplaneRepository.save(airplane);
        return "Airplane updated successfully.";
    }

    @Override
    public String deleteAirplane(Long id) {
        airplaneRepository.deleteById(id);
        return "Airplane deleted successfully.";
    }

    @Override
    public List<Airplane> getAllAirplanes() {
        return airplaneRepository.findAll();
    }

    @Override
    public Airplane getAirplaneById(Long id) {
        return airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found"));
    }
}