package com.wipro.training.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.wipro.training.model.Seat;

public interface SeatRepository extends JpaRepository<Seat, Long> {
    List<Seat> findByFlightIdAndIsAvailableTrue(Long flightId);

    List<Seat> findByFlightId(Long flightId);
    
// Ensure this method exists if you need to find seats by number
  Optional<Seat> findBySeatNumberAndFlightId(String seatNumber, Long flightId);


}
