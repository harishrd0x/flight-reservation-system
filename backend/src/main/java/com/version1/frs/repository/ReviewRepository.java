package com.version1.frs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.version1.frs.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByFlight_FlightId(Long flightId);
    List<Review> findByUser_UserId(Long userId);
}
