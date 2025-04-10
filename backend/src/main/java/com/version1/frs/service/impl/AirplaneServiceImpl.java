package com.version1.frs.service.impl;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.service.AirplaneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    @Override
    public String addAirplane(AirplaneRequest request) {
        Airplane airplane = new Airplane();
        airplane.setName(request.getName());
        airplane.setModel(request.getModel());
        airplane.setManufacturer(request.getManufacturer());
        airplane.setCapacity(request.getCapacity());

        airplaneRepository.save(airplane);
        return "Airplane added successfully.";
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
        return airplaneRepository.findById(id).orElse(null);
    }

    @Override
    public String updateAirplane(Long id, AirplaneRequest request) {
        Optional<Airplane> optional = airplaneRepository.findById(id);
        if (optional.isPresent()) {
            Airplane airplane = optional.get();
            airplane.setName(request.getName());
            airplane.setModel(request.getModel());
            airplane.setManufacturer(request.getManufacturer());
            airplane.setCapacity(request.getCapacity());
            airplaneRepository.save(airplane);
            return "Airplane updated successfully.";
        }
        return "Airplane not found.";
    }
}