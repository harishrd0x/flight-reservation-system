package com.version1.frs.repository;
import org.springframework.data.repository.CrudRepository;

import com.version1.frs.model.Airplane;

public interface AirplaneRepository extends CrudRepository<Airplane, Long> {
}
