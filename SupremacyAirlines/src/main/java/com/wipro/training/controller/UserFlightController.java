package com.wipro.training.controller;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.model.Flight;
import com.wipro.training.repository.FlightRepository;
import com.wipro.training.service.FlightService;
@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("/api/flights")
public class UserFlightController {

	@Autowired
	private FlightService flightService;
	@Autowired 
	private FlightRepository flightRepository;
	
	//http://localhost:8088/api/flights/search?source=JFK&destination=LAX&departureDate=2024-12-01T12:00:00

	@GetMapping("/search")
	public ResponseEntity<?> searchFlights(
			//  @RequestParam String oneWayOrReturn,
			@RequestParam  String source,
			@RequestParam  String destination,
			@RequestParam  LocalDate departureDate,
			@RequestParam(required = false) LocalDate returnDate) {

		try {
			List<Flight> flights = flightService.searchFlights(source, destination, departureDate, returnDate);
			if (flights.isEmpty()) {
				return ResponseEntity.noContent().build();
			}
			return ResponseEntity.ok(flights);
		} catch (DateTimeParseException e) {
			return ResponseEntity.ok("Inavali Data");
		}	
	}
	
	 @GetMapping("/search/view")
	    public ResponseEntity<List<Flight>> viewFlights() {
	        List<Flight> flights = flightRepository.findAll();
	        return ResponseEntity.ok(flights);
	    }
	 
	 @GetMapping("/search/sd")
	 public ResponseEntity<?>searchFlightsByWay(@RequestParam String source, @RequestParam String destination){
		 try {
				List<Flight> flights = flightService.searchFlightsByWay(source, destination);
				if (flights.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.ok(flights);
			} catch (DateTimeParseException e) {
				return ResponseEntity.ok("Flights Not Found");
			}	
	 }
	 
	 @GetMapping("/search/t")
	 public ResponseEntity<?>searchFlightsByDate(@RequestParam LocalDate returnDate,  @RequestParam LocalDate departureDate){
		 try {
				List<Flight> flights = flightService.searchFlightsByTime( departureDate, returnDate);
				if (flights.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.ok(flights);
			} catch (DateTimeParseException e) {
				return ResponseEntity.ok("Flights Not Found");
			}	
	 }
	
}

