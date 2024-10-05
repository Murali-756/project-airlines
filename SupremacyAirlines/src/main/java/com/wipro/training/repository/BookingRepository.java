package com.wipro.training.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.training.model.Booking;


public interface BookingRepository extends JpaRepository<Booking, Long> {
	   
	Optional<Booking> findById(Long id);
		
		boolean existsByFlightId(Long id);
	}