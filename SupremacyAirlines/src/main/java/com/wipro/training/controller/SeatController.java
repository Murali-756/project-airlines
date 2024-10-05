package com.wipro.training.controller;

import java.util.Collections;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.Dto.BookingRequest;
import com.wipro.training.Dto.BookingResponse;
import com.wipro.training.model.Flight;
import com.wipro.training.model.Passenger;
import com.wipro.training.model.Seat;
import com.wipro.training.repository.PassengerRepository;
import com.wipro.training.service.SeatService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/flights")
public class SeatController {

	@Autowired
	private SeatService seatService;

	@Autowired
	private PassengerRepository pRepo;
	
	 // Endpoint to add new seats
    @PostMapping("/{flightId}/addseat")
    public ResponseEntity<List<Seat>> addSeats(
            @PathVariable Long flightId,
            @RequestBody List<Seat> seats) {
        try {
            List<Seat> addedSeats = seatService.addSeats(flightId, seats);
            return ResponseEntity.ok(addedSeats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    // Endpoint to get seats by flightId
    @GetMapping("/{flightId}/seat")
    public ResponseEntity<List<Seat>> getSeatsByFlightId(@PathVariable Long flightId) {
        try {
            List<Seat> seats = seatService.getSeatsByFlightId(flightId);
            return ResponseEntity.ok(seats);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
	
	 @PostMapping("/{flightId}/book")
	    public ResponseEntity<BookingResponse> bookSeats(
	            @PathVariable Long flightId,
	            @RequestBody List<BookingRequest> bookingRequests) {

	        try {
	            BookingResponse bookingResponse = seatService.bookSeats(flightId, bookingRequests);
	            return ResponseEntity.ok(bookingResponse);
	        } catch (Exception e) {
	            BookingResponse errorResponse = new BookingResponse("error", 0.00, Collections.emptyList(), "An error occurred: " + e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse);
	        }
	    }
	 
	//localhost:8088/api/flights/details
	 @GetMapping("details")
	 public ResponseEntity<List<Passenger>> getAllPassengers(){
		 List<Passenger> passenger=pRepo.findAll();
		 return ResponseEntity.ok(passenger);
	 }
	 
	 //localhost:8088/api/flights/passenger/seatId
	 @GetMapping("/passenger/{seatId}")
		 public ResponseEntity<List<Passenger>> getPassengersByseatId(@PathVariable Long seatId) {
		        try {
		            List<Passenger> passengers = pRepo.getSeatsBySeatId(seatId);
		            return ResponseEntity.ok(passengers);
		        } catch (Exception e) {
		            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		        }
	}
	}

	

