package com.wipro.training.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.exception.ResourceNotFoundException;
import com.wipro.training.model.Flight;
import com.wipro.training.repository.BookingRepository;
import com.wipro.training.repository.FlightRepository;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/flights")
public class AdminFlightController {
	@Autowired
    private FlightRepository flightRepository;
	
    @Autowired
    private BookingRepository bookingRepository;
    //http://localhost:8088/api/flights/add
    @PostMapping("/add")
    public ResponseEntity<Flight> addFlight(@RequestBody Flight flight) {
        if (flight.getAvailableSeats() <= 0 || flight.getFare() <= 0) {
            throw new IllegalArgumentException("Invalid flight details");
        }
        Flight savedFlight = flightRepository.save(flight);
        return ResponseEntity.ok(savedFlight);
    }

    //http://localhost:8088/api/flights/delete/{id}
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteFlight(@PathVariable Long id) throws ResourceNotFoundException {
        Flight flight = flightRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Flight not found"));

        if (bookingRepository.existsByFlightId(id)) {
            throw new IllegalStateException("Flight cannot be deleted as it has bookings");
        }

        flightRepository.delete(flight);
        return ResponseEntity.ok("Flight deleted successfully");
    }
    //http://localhost:8088/api/flights/view
    @GetMapping("/view")
    public ResponseEntity<List<Flight>> viewFlights() {
        List<Flight> flights = flightRepository.findAll();
        return ResponseEntity.ok(flights);
    }
}