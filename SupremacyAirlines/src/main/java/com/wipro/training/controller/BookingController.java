package com.wipro.training.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wipro.training.exception.ResourceNotFoundException;
import com.wipro.training.model.Booking;
import com.wipro.training.model.Flight;
import com.wipro.training.repository.BookingRepository;
import com.wipro.training.service.BookingService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
@RequestMapping("api/bookings")
public class BookingController {

	private BookingService bookingService;
	    
	public BookingController(BookingService bookingService) {
		super();
		this.bookingService = bookingService;
	}
	@Autowired
	private BookingRepository bookingRepository;

	@PostMapping
    public ResponseEntity<String> createBooking(@RequestBody Booking booking) {
      
		//return bookingService.createBooking(booking);}
		 try {
        	
            Booking createdBooking = bookingService.createBooking(booking);
            
            if(createdBooking != null) {
				return ResponseEntity.ok("Continue to Seat Seleaction");
			} else {
				return ResponseEntity.badRequest().body("Booking Failed Retry Again");
			}}
		catch(Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).
					body("An Error Occurred : "+e.getMessage());
		}
	
    }
	
	/*@GetMapping("/{id}")
	 public Optional<Booking> searchBookings(@RequestParam Long id){
		 try {
				Optional<Booking> bookings = bookingService.searchBookings( id);
				if (bookings.isEmpty()) {
					return ResponseEntity.noContent().build();
				}
				return ResponseEntity.ok(bookings);
			} catch (DateTimeParseException e) {
				return ResponseEntity.notFound().build();
			}	
		return bookingService.searchBooking(id);
	 }*/
	 
        /*@GetMapping
		public Optional<Booking> searchBooking(@RequestParam Long id) {
			return bookingRepository. findById(id);
		}*/

/*	 @PostMapping
	    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
	        if (booking.getFlight() == null) {
	            return ResponseEntity.badRequest().body(null);
	        }
	        Booking savedBooking = bookingService.createBooking(booking);
	        return ResponseEntity.ok(savedBooking);
	    }*/
        
        @DeleteMapping("/delete/{id}")
        public ResponseEntity<String> deleteBooking(@PathVariable Long id) throws ResourceNotFoundException {
            Booking b = bookingRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Booking not found"));

            bookingRepository.delete(b);
            return ResponseEntity.ok("Booking cancel successfully");
        }
        @GetMapping("/{id}")
        public ResponseEntity<Optional<Booking>> getFlight(@PathVariable Long id)  {
            Optional<Booking>  bookings = bookingRepository.findById(id);
                      return ResponseEntity.ok(bookings);
        }
        
      

}


