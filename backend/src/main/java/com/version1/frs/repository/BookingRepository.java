package com.version1.frs.repository;

import com.version1.frs.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BookingRepository extends JpaRepository<Booking, Long> {
    List<Booking> findByUserUserId(int userId); // findByUserId - review
}