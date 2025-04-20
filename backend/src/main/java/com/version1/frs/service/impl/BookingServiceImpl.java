package com.version1.frs.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.version1.frs.dto.BookingRequest;
import com.version1.frs.dto.BookingResponse;
import com.version1.frs.model.Booking;
import com.version1.frs.model.Flight;
import com.version1.frs.model.User;
import com.version1.frs.model.Wallet;
import com.version1.frs.repository.BookingRepository;
import com.version1.frs.repository.FlightRepository;
import com.version1.frs.repository.UserRepository;
import com.version1.frs.repository.WalletRepository;
import com.version1.frs.service.BookingService;

import jakarta.transaction.Transactional;

@Service
public class BookingServiceImpl implements BookingService {

    @Autowired private BookingRepository bookingRepository;
    @Autowired private UserRepository userRepository;
    @Autowired private FlightRepository flightRepository;
    @Autowired private WalletRepository walletRepository;

    @Transactional // we don't want money deducted if booking fails, both should go through or none
    @Override
    public BookingResponse bookFlight(BookingRequest request, Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Flight flight = flightRepository.findById(request.getFlightId())
                .orElseThrow(() -> new RuntimeException("Flight not found"));

        Wallet wallet = walletRepository.findByUser_UserId(userId)
                .orElseThrow(() -> new RuntimeException("Wallet not found"));

        BigDecimal flightPrice = flight.getPrice();

        if (wallet.getBalance().compareTo(flightPrice) < 0) {
            throw new RuntimeException("Insufficient wallet balance.");
        }

        wallet.setBalance(wallet.getBalance().subtract(flightPrice));
        walletRepository.save(wallet);

        Booking booking = new Booking();
        booking.setUser(user);
        booking.setFlight(flight);
        booking.setBookingTime(LocalDateTime.now());
        booking.setTotalAmount(flightPrice);

        bookingRepository.save(booking);

        return mapToDto(booking);
    }

    @Override
    public List<BookingResponse> getBookingsByUser(Long userId) {
        return bookingRepository.findByUserUserId(userId)
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<BookingResponse> getAllBookings(Long customerId) {
        List<Booking> bookings;

        if (customerId != null) {
            bookings = bookingRepository.findByUserUserId(customerId);
        } else {
            bookings = bookingRepository.findAll();
        }

        return bookings.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public BookingResponse getBookingById(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        return mapToDto(booking);
    }

    @Override
    public void deleteBooking(Long bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new RuntimeException("Booking not found"));
        bookingRepository.delete(booking);
    }

    private BookingResponse mapToDto(Booking booking) {
        return new BookingResponse(
                booking.getBookingId(),
                booking.getUser().getUserId(),
                booking.getFlight().getId(),
                booking.getBookingTime(),
                booking.getTotalAmount()
        );
    }
}
