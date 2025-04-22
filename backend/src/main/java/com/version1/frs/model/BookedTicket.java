package com.version1.frs.model;

import jakarta.persistence.*;

@Entity
@Table(name = "TBL_BOOKED_TICKETS")
public class BookedTicket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TICKET_ID")
    private Long ticketId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOKING_ID", nullable = false)
    private Booking booking;

    @Column(name = "PASSENGER_NAME", nullable = false)
    private String passengerName;

    @Column(name = "PASSENGER_AGE", nullable = false)
    private int passengerAge;

    @Column(name = "PASSENGER_GENDER", nullable = false)
    private String passengerGender;

    // -------------------- Getters and Setters --------------------

    public Long getTicketId() {
        return ticketId;
    }

    public void setTicketId(Long ticketId) {
        this.ticketId = ticketId;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getPassengerName() {
        return passengerName;
    }

    public void setPassengerName(String passengerName) {
        this.passengerName = passengerName;
    }

    public int getPassengerAge() {
        return passengerAge;
    }

    public void setPassengerAge(int passengerAge) {
        this.passengerAge = passengerAge;
    }

    public String getPassengerGender() {
        return passengerGender;
    }

    public void setPassengerGender(String passengerGender) {
        this.passengerGender = passengerGender;
    }
}
