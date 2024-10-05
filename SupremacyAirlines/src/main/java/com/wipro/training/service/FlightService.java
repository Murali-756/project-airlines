package com.wipro.training.service;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.training.model.Flight;
import com.wipro.training.repository.FlightRepository;

@Service
public class FlightService {
    @Autowired
    private FlightRepository flightRepository;

 
    public List<Flight> searchFlightsByWay(String source, String destination) {
        return flightRepository.findBySourceAndDestination(source, destination);
    }
    
    public List<Flight> searchFlightsByTime(LocalDate returnDate, LocalDate departureDate) {
        return flightRepository.findByDepartureDateAndReturnDate(departureDate, returnDate);
    }
    
    public List<Flight> searchFlights(
            String source,
            String destination,
            LocalDate departureDate,
            LocalDate returnDate) {

        // If returnDateTime is provided, search for flights within the given date range
     if (returnDate != null) {
            return flightRepository.findBySourceAndDestinationAndDepartureDateBetween
            		(source,destination,departureDate, returnDate );             
              } 
       
     else {
            // Search only for the departure flights
            return flightRepository.findBySourceAndDestinationAndDepartureDateBetween
            		(source,destination, departureDate,
            				departureDate.plusDays(1) ); // Assuming searches are done within a day
            
        }
    }
    public Optional<Flight> getFlightById(Long id) {
        return flightRepository.findById(id);
    }
    
   
    }

