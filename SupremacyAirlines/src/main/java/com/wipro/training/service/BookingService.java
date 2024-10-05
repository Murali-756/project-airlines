package com.wipro.training.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.wipro.training.model.Booking;
import com.wipro.training.repository.BookingRepository;

@Service
public class BookingService {
	
	@Autowired
    private BookingRepository bookingRepository;

    
	public Booking getBookingById(Long bookingId) {
		return bookingRepository.findById(bookingId).orElseThrow(() -> new RuntimeException("Booking not found"));
	}
	  
	  /*  public Booking createBooking(Booking booking) {
	        // Set the booking date to current time
	    	
	        booking.setBookingDate(LocalDateTime.now());
	        return bookingRepository.save(booking);
	    }*/
	    
/*	public void deleteBooking(Long bookingId) {
		if (bookingRepository.existsById(bookingId)) {
			bookingRepository.deleteById(bookingId);
		} else {
			throw new RuntimeException("Booking not found");
		}
	}*/
	 
	    public Booking createBooking(Booking booking) {
	        booking.setBookingDate(LocalDate.now());
	        return bookingRepository.save(booking);
	    }
	
}