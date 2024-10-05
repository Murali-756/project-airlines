package com.wipro.training.Dto;

import com.wipro.training.model.Passenger;

public class SeatBookingDetail {
	private String seatNumber;
    private Passenger passenger;
    private String bookingStatus;

    // Constructors
    public SeatBookingDetail() {}

    public SeatBookingDetail(String seatNumber, Passenger passenger, String bookingStatus) {
        this.seatNumber = seatNumber;
        this.passenger = passenger;
        this.bookingStatus = bookingStatus;
    }

    // Getters and Setters
    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        this.passenger = passenger;
    }

    public String getBookingStatus() {
        return bookingStatus;
    }

    public void setBookingStatus(String bookingStatus) {
        this.bookingStatus = bookingStatus;
    }
}