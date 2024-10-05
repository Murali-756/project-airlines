package com.wipro.training.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "booking")
public class Booking {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY,generator="booking_seq")
	@SequenceGenerator(initialValue=43521,allocationSize=1, name = "booking_seq")

    private Long id;


   // @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "flight_id")
    private Flight flight;

    @Column(name = "number_of_seats", nullable = false)
    private Integer numberOfSeats;

    @Column(name = "adult_passengers", nullable = false)
    private Integer adultPassengers;

    @Column(name = "child_passengers", nullable = false)
    private Integer childPassengers;

    @Column(name = "infant_passengers", nullable = false)
    private Integer infantPassengers;


    @Column(name = "booking_date", nullable = false)
    private LocalDate bookingDate;

    // Default constructor
    public Booking() {
    }

    // Parameterized constructor
    public Booking(Long id,  Flight flight, Integer numberOfSeats,
                   Integer adultPassengers, Integer childPassengers, Integer infantPassengers,
                    LocalDate bookingDate) {
        this.id = id;
     
        this.flight = flight;
        this.numberOfSeats = numberOfSeats;
        this.adultPassengers = adultPassengers;
        this.childPassengers = childPassengers;
        this.infantPassengers = infantPassengers;
        this.bookingDate = bookingDate;
    }

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

   

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public Integer getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(Integer numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public Integer getAdultPassengers() {
        return adultPassengers;
    }

    public void setAdultPassengers(Integer adultPassengers) {
        this.adultPassengers = adultPassengers;
    }

    public Integer getChildPassengers() {
        return childPassengers;
    }

    public void setChildPassengers(Integer childPassengers) {
        this.childPassengers = childPassengers;
    }

    public Integer getInfantPassengers() {
        return infantPassengers;
    }

    public void setInfantPassengers(Integer infantPassengers) {
        this.infantPassengers = infantPassengers;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }
   
}

