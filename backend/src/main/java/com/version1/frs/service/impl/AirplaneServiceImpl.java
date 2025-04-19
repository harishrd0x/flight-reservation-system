package com.version1.frs.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;
import com.version1.frs.model.Airplane;
import com.version1.frs.repository.AirplaneRepository;
import com.version1.frs.service.AirplaneService;

@Service
public class AirplaneServiceImpl implements AirplaneService {

    @Autowired
    private AirplaneRepository airplaneRepository;

    // ---------------------- Create ----------------------
    @Override
    public AirplaneResponse addAirplane(AirplaneRequest request) {
        if (airplaneRepository.existsByAirplaneNumber(request.getAirplaneNumber())) {
            throw new RuntimeException("Airplane with this number already exists");
        }
        Airplane airplane = mapToEntity(request);
        return mapToResponse(airplaneRepository.save(airplane));
    }

    // ---------------------- Read ----------------------
    @Override
    public List<AirplaneResponse> getAllAirplanes() {
        return airplaneRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public AirplaneResponse getAirplaneById(Long id) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with ID: " + id));
        return mapToResponse(airplane);
    }

    @Override
    public AirplaneResponse getAirplaneByNumber(String airplaneNumber) {
        Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
                .orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
        return mapToResponse(airplane);
    }

    // ---------------------- Update ----------------------
    @Override
    public AirplaneResponse updateAirplane(Long id, AirplaneRequest request) {
        Airplane airplane = airplaneRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Airplane not found with ID: " + id));
        updateEntity(airplane, request);
        return mapToResponse(airplaneRepository.save(airplane));
    }

    @Override
    public AirplaneResponse updateAirplane(String airplaneNumber, AirplaneRequest request) {
        Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
                .orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
        updateEntity(airplane, request);
        return mapToResponse(airplaneRepository.save(airplane));
    }

    // ---------------------- Delete ----------------------
    @Override
    public String deleteAirplane(Long id) {
        if (!airplaneRepository.existsById(id)) {
            throw new RuntimeException("Airplane not found with ID: " + id);
        }
        airplaneRepository.deleteById(id);
        return "Airplane deleted successfully.";
    }

    @Override
    public String deleteAirplane(String airplaneNumber) {
        Airplane airplane = airplaneRepository.findByAirplaneNumber(airplaneNumber)
                .orElseThrow(() -> new RuntimeException("Airplane not found with number: " + airplaneNumber));
        airplaneRepository.delete(airplane);
        return "Airplane deleted successfully.";
    }

    // ---------------------- Validation ----------------------
    @Override
    public boolean airplaneNumberExists(String airplaneNumber) {
        return airplaneRepository.existsByAirplaneNumber(airplaneNumber);
    }

    // ---------------------- Filters / Search ----------------------
    @Override
    public List<AirplaneResponse> searchByName(String keyword) {
        return airplaneRepository.findByAirplaneNameContainingIgnoreCase(keyword).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirplaneResponse> filterByManufacturer(String manufacturer) {
        return airplaneRepository.findByManufacturerIgnoreCase(manufacturer).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirplaneResponse> filterByModel(String model) {
        return airplaneRepository.findByAirplaneModelIgnoreCase(model).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirplaneResponse> findByCapacityGreaterThanEqual(int minCapacity) {
        return airplaneRepository.findByCapacityGreaterThanEqual(minCapacity).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public List<AirplaneResponse> findByCapacityBetween(int min, int max) {
        return airplaneRepository.findByCapacityBetween(min, max).stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    // ---------------------- Mapping Helpers ----------------------
    private Airplane mapToEntity(AirplaneRequest request) {
        Airplane airplane = new Airplane();
        updateEntity(airplane, request);
        return airplane;
    }

    private void updateEntity(Airplane airplane, AirplaneRequest request) {
        airplane.setAirplaneName(request.getAirplaneName());
        airplane.setAirplaneNumber(request.getAirplaneNumber());
        airplane.setAirplaneModel(request.getAirplaneModel());
        airplane.setManufacturer(request.getManufacturer());
        airplane.setCapacity(request.getCapacity());
    }

    private AirplaneResponse mapToResponse(Airplane airplane) {
        AirplaneResponse response = new AirplaneResponse();
        response.setAirplaneId(airplane.getAirplaneId());
        response.setAirplaneName(airplane.getAirplaneName());
        response.setAirplaneNumber(airplane.getAirplaneNumber());
        response.setAirplaneModel(airplane.getAirplaneModel());
        response.setManufacturer(airplane.getManufacturer());
        response.setCapacity(airplane.getCapacity());
        return response;
    }
}
