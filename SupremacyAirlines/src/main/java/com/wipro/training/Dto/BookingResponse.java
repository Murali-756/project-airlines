package com.wipro.training.Dto;

import java.util.List;

import com.wipro.training.model.Passenger;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


public class BookingResponse {
	
	private String status;
    private Double totalAmount;
    private List<SeatBookingDetail> bookedSeats;
    private String message; // Optional, to include error messages
    @ManyToOne
    @JoinColumn(name="passenger_id")
    public Passenger passenger;
    
    // Constructors
    public BookingResponse() {}

    public BookingResponse(String status, Double totalAmount, List<SeatBookingDetail> bookedSeats, String message) {
        this.status = status;
        this.totalAmount = totalAmount;
        this.bookedSeats = bookedSeats;
        this.message = message;
    }

    // Getters and Setters
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<SeatBookingDetail> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<SeatBookingDetail> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

	public Passenger getPassenger() {
		return passenger;
	}

	public void setPassenger(Passenger passenger) {
		this.passenger = passenger;
	}
}
