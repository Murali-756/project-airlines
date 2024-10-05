package com.wipro.training.repository;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.training.model.Flight;

public interface FlightRepository extends JpaRepository<Flight, Long> {
   
	List<Flight> findBySourceAndDestination(String source, String destination);
    
    List<Flight> findByDepartureDateAndReturnDate(LocalDate returnDate, LocalDate departureDate);
	   
    List<Flight> findBySourceAndDestinationAndDepartureDateBetween(
    		    String source, 
	            String destination, 
	            LocalDate departureDateStart, 
	            LocalDate departureDateEnd);
	    
	    List<Flight> findBySourceAndDestinationAndReturnDateBetween(
	            String source, 
	            String destination, 
	            LocalDate returnDateStart, 
	            LocalDate returnDateEnd);
	   
}
