package com.wipro.training.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.wipro.training.Dto.BookingResponse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;


@Entity
public class Passenger {

	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	    
	    private String name;
	    private String email;
	    private String phoneNumber;
	    
	   
	   
	    @ManyToOne
	    @JsonBackReference
	    @JoinColumn(name = "seat_id")
	    private Seat seat;
	    

		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getPhoneNumber() {
			return phoneNumber;
		}

		public void setPhoneNumber(String phoneNumber) {
			this.phoneNumber = phoneNumber;
		}

		public Seat getSeat() {
			return seat;
		}

		public void setSeat(Seat seat) {
			this.seat = seat;
		}

		

	
		
	    
}
