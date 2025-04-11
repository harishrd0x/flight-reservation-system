package com.version1.frs.service;

import java.util.List;

import com.version1.frs.dto.AirplaneRequest;
import com.version1.frs.model.Airplane;

public interface AirplaneService {
	String addAirplane(AirplaneRequest request);

	String updateAirplane(Long id, AirplaneRequest request);

	String deleteAirplane(Long id);

	List<Airplane> getAllAirplanes();

	Airplane getAirplaneById(Long id);
}