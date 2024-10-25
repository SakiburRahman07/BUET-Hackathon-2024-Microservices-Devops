package com.sakib.train_service.dao;

import com.sakib.train_service.model.Booking;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookDao extends JpaRepository<Booking, Long> {
    Booking findById(long id);

}
