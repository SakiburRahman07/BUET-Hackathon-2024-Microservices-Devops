package com.sakib.train_service.dao;

import com.sakib.train_service.model.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SeatDao extends JpaRepository<Seat, Long> {
    Optional<Seat> findByTrainIdAndDate(Long trainId, String date);  // Custom query method
}
