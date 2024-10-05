package com.wipro.training.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Flight {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY,generator="flight_seq")
	@SequenceGenerator(initialValue=17201,allocationSize=1, name = "flight_seq")
	private Long id;
	private String flightNumber;
	private String source;
	private String destination;
	private LocalDate departureDate;
	private LocalDate returnDate;
	private Integer availableSeats;
	private Double fare;
	 private String flightClass; 
	 
	 
	public String getFlightClass() {
		return flightClass;
	}
	public void setFlightClass(String flightClass) {
		this.flightClass = flightClass;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFlightNumber() {
		return flightNumber;
	}
	public void setFlightNumber(String flightNumber) {
		this.flightNumber = flightNumber;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public LocalDate getDepartureDate() {
		return departureDate;
	}
	public void setDepartureDate(LocalDate departureDate) {
		this.departureDate = departureDate;
	}
	public LocalDate getReturnDate() {
		return returnDate;
	}
	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}
	public Integer getAvailableSeats() {
		return availableSeats;
	}
	public void setAvailableSeats(Integer availableSeats) {
		this.availableSeats = availableSeats;
	}
	public Double getFare() {
		return fare;
	}
	public void setFare(Double fare) {
		this.fare = fare;
	}
	public Flight(Long id, String flightNumber, String source, String destination, LocalDate departureDate,
			LocalDate returnDate, Integer availableSeats, Double fare,String flightClass) {
		super();
		this.id = id;
		this.flightNumber = flightNumber;
		this.source = source;
		this.destination = destination;
		this.departureDate = departureDate;
		this.returnDate = returnDate;
		this.availableSeats = availableSeats;
		this.fare = fare;
		this.flightClass=flightClass;
	}
	public Flight() {
	}
	

}