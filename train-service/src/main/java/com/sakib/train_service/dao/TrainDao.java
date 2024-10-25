package com.sakib.train_service.dao;

import com.sakib.train_service.model.Train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrainDao extends JpaRepository<Train, Long> {
    List<Train> findByFromStationAndToStation(String fromStation, String toStation);

}

