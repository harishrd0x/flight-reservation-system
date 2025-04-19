package com.version1.frs.service;

import java.util.List;
import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.dto.AirplaneResponse;

public interface AirplaneService {

    // -------------------- Create --------------------
    AirplaneResponse addAirplane(AirplaneRequest request);

    // -------------------- Read --------------------
    List<AirplaneResponse> getAllAirplanes();
    AirplaneResponse getAirplaneById(Long id);
    AirplaneResponse getAirplaneByNumber(String airplaneNumber);

    // -------------------- Update --------------------
    AirplaneResponse updateAirplane(Long id, AirplaneRequest request); // Using ID
    AirplaneResponse updateAirplane(String airplaneNumber, AirplaneRequest request); // Using airplaneNumber

    // -------------------- Delete --------------------
    String deleteAirplane(Long id); // Using ID
    String deleteAirplane(String airplaneNumber); // Using airplaneNumber

    // -------------------- Validation --------------------
    boolean airplaneNumberExists(String airplaneNumber);

    // -------------------- Search / Filters --------------------
    List<AirplaneResponse> searchByName(String keyword);
    List<AirplaneResponse> filterByManufacturer(String manufacturer);
    List<AirplaneResponse> filterByModel(String model);
    List<AirplaneResponse> findByCapacityGreaterThanEqual(int minCapacity);
    List<AirplaneResponse> findByCapacityBetween(int min, int max);
}
