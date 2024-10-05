package com.wipro.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.training.model.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long> {

	List<Passenger> getSeatsBySeatId(Long seatId);
   
}
