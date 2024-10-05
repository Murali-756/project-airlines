package com.wipro.training.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.training.Dto.BookingRequest;
import com.wipro.training.Dto.BookingResponse;
import com.wipro.training.Dto.SeatBookingDetail;
import com.wipro.training.model.Flight;
import com.wipro.training.model.Passenger;
import com.wipro.training.model.Seat;
import com.wipro.training.repository.FlightRepository;
import com.wipro.training.repository.PassengerRepository;
import com.wipro.training.repository.SeatRepository;

@Service
public class SeatService {

	 @Autowired
	    private FlightRepository flightRepository;

	    @Autowired
	    private SeatRepository seatRepository;

	    @Autowired
	    private PassengerRepository passengerRepository;
	    
	    // Method to add new seats
	    public List<Seat> addSeats(Long flightId, List<Seat> seats) {
	        for (Seat seat : seats) {
	            seat.setFlightId(flightId);
	            seat.setIsAvailable(true); // Set initial availability status
	        }
	        return seatRepository.saveAll(seats);
	    }

	    // Method to get seats by flightId
	    public List<Seat> getSeatsByFlightId(Long flightId) {
	        return seatRepository.findByFlightId(flightId);
	    }
	

	    public BookingResponse bookSeats(Long flightId, List<BookingRequest> bookingRequests) {
	        Flight flight = flightRepository.findById(flightId)
	            .orElseThrow(() -> new IllegalArgumentException("Flight not found"));

	        List<Seat> availableSeats = seatRepository.findByFlightIdAndIsAvailableTrue(flightId);

	        // Map seat number to seat object for quick lookup
	        Map<String, Seat> seatMap = availableSeats.stream()
	            .collect(Collectors.toMap(Seat::getSeatNumber, seat -> seat));

	        List<SeatBookingDetail> seatBookingDetails = new ArrayList<>();
	        double fare = flight.getFare();

	        for (BookingRequest request : bookingRequests) {
	            Seat seat = seatMap.get(request.getSeatNumber());

	            if (seat == null) {
	                throw new IllegalArgumentException("Seat number " + request.getSeatNumber() + " not found.");
	            }

	            if (!seat.getIsAvailable()) {
	                throw new IllegalStateException("Seat number " + request.getSeatNumber() + " is already booked.");
	            }

	            seat.setIsAvailable(false);

	            Passenger passenger = new Passenger();
	            passenger.setName(request.getPassengerName());
	            passenger.setEmail(request.getPassengerEmail());
	            passenger.setPhoneNumber(request.getPassengerPhoneNumber());
	            passenger.setSeat(seat);
	            passengerRepository.save(passenger);

	            seat.setPassenger(passenger);
	            seatBookingDetails.add(new SeatBookingDetail(seat.getSeatNumber(), passenger, "booked"));
	        }

	        seatRepository.saveAll(availableSeats);

	        flight.setAvailableSeats(flight.getAvailableSeats() - seatBookingDetails.size());
	        flightRepository.save(flight);

	        double totalAmount = seatBookingDetails.size() * fare;

	        BookingResponse response = new BookingResponse();
	        response.setStatus("success");
	        response.setTotalAmount(totalAmount);
	        response.setBookedSeats(seatBookingDetails);
	        response.setMessage("Continue to  Do Payment");
	        return response;
	    }
	    }