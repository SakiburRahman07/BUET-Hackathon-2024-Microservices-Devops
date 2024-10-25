package com.sakib.train_service.service;


import com.sakib.train_service.dao.TrainDao;
import com.sakib.train_service.model.Train;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TrainService {

    @Autowired
    private TrainDao trainDao;



    // Method to fetch trains by fromStation and toStation
    public List<Train> getTrainsByStations(String fromStation, String toStation) {
        return trainDao.findByFromStationAndToStation(fromStation, toStation);
    }

    public Train addTrain(Train train) {
        return trainDao.save(train);
    }

    public List<Train> getAllTrains() {
        return trainDao.findAll();
    }

    public Optional<Train> getTrainById(Long id) {
        return trainDao.findById(id);
    }
}
