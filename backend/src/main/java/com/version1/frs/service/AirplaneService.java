package com.version1.frs.service;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;

import java.util.List;

public interface AirplaneService {

    String addAirplane(AirplaneRequest request);

    String deleteAirplane(Long id);

    List<Airplane> getAllAirplanes();

    Airplane getAirplaneById(Long id);

    String updateAirplane(Long id, AirplaneRequest request);
}