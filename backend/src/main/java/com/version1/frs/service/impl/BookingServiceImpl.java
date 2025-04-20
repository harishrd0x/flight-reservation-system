//package com.version1.frs.service.impl;
//
//import java.time.LocalDateTime;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.version1.frs.dto.BookingRequest;
//import com.version1.frs.dto.BookingResponse;
//import com.version1.frs.model.Booking;
//import com.version1.frs.model.Flight;
//import com.version1.frs.model.User;
//import com.version1.frs.model.Wallet;
//import com.version1.frs.repository.BookingRepository;
//import com.version1.frs.repository.FlightRepository;
//import com.version1.frs.repository.UserRepository;
//import com.version1.frs.repository.WalletRepository;
//import com.version1.frs.service.BookingService;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    @Autowired private BookingRepository bookingRepository;
//    @Autowired private UserRepository userRepository;
//    @Autowired private FlightRepository flightRepository;
//    @Autowired private WalletRepository walletRepository;
//
//    @Override
//    public BookingResponse bookFlight(BookingRequest request) {
//        User user = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new RuntimeException("User not found"));
//
//        Flight flight = flightRepository.findById(request.getFlightId())
//                .orElseThrow(() -> new RuntimeException("Flight not found"));
//
//        Wallet wallet = walletRepository.findByUser_UserId(user.getUserId())
//                .orElseThrow(() -> new RuntimeException("Wallet not found"));
//
//        double flightPrice = flight.getPrice();
//
//        if (wallet.getBalance() < flightPrice) {
//            throw new RuntimeException("Insufficient wallet balance.");
//        }
//
//        // Deduct amount from wallet
//        wallet.setBalance(wallet.getBalance() - flightPrice);
//        walletRepository.save(wallet);
//
//        Booking booking = new Booking();
//        booking.setUser(user);
//        booking.setFlight(flight);
//        booking.setBookingTime(LocalDateTime.now());
//        booking.setTotalAmount(flightPrice);
//
//
//        bookingRepository.save(booking);
//
//        return new BookingResponse(
//                booking.getBookingId(),
//                user.getUserId(),
//                flight.getFlightId(),
//                flight.getFlightNumber(),
//                booking.getBookingTime()
//        );
//    }
//
//    @Override
//    public List<BookingResponse> getBookingsByUser(int userId) {
//        return bookingRepository.findByUserUserId(userId)
//                .stream()
//                .map(b -> new BookingResponse(
//                        b.getBookingId(),
//                        b.getUser().getUserId(),
//                        b.getFlight().getFlightId(),
//                        b.getFlight().getFlightNumber(),
//                        b.getBookingTime()))
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public List<BookingResponse> getAllBookings() {
//        return bookingRepository.findAll()
//                .stream()
//                .map(b -> new BookingResponse(
//                        b.getBookingId(),
//                        b.getUser().getUserId(),
//                        b.getFlight().getFlightId(),
//                        b.getFlight().getFlightNumber(),
//                        b.getBookingTime()))
//                .collect(Collectors.toList());
//    }
//}