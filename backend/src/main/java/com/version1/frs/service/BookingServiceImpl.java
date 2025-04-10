//package com.version1.frs.service;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import com.version1.frs.model.Bookings;
//import com.version1.frs.repository.BookingRepository;
//
//import java.util.Optional;
//
//@Service
//public class BookingServiceImpl implements BookingService {
//
//    @Autowired
//    private BookingRepository bookingRepository;
//
//    public Bookings createBooking(Bookings booking) {
//        return bookingRepository.save(booking);
//    }
//
//    @Override
//    public Booking getBookingById(Long id) {
//        Optional<Booking> booking = bookingRepository.findById(id);
//        return booking.orElseThrow(() -> new RuntimeException("Booking not found"));
//    }
//
//    @Override
//    public Iterable<Booking> getAllBookings() {
//        return bookingRepository.findAll();
//    }
//
//    @Override
//    public void cancelBooking(Long id) {
//        Booking booking = getBookingById(id);
//        booking.setStatus("Cancelled");
//        bookingRepository.save(booking);
//    }
//}
