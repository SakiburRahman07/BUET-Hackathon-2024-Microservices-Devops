package com.sakib.train_service.controller;


import com.sakib.train_service.model.Train;
import com.sakib.train_service.service.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/trains")
@CrossOrigin(origins = "http://localhost:5177")
public class TrainController {

    @Autowired
    private TrainService trainService;

    // Endpoint to add a train
    @PostMapping("/addTrain")
    public ResponseEntity<Train> addTrain(@RequestBody Train train) {
        Train savedTrain = trainService.addTrain(train);
        return ResponseEntity.ok(savedTrain);
    }

    // Endpoint to get all trains
    @GetMapping("/allTrain")
    public ResponseEntity<List<Train>> getAllTrains() {
        List<Train> trains = trainService.getAllTrains();
        return ResponseEntity.ok(trains);
    }

    // Endpoint to get train by id
    @GetMapping("searchTrain/{id}")
    public ResponseEntity<Train> getTrainById(@PathVariable Long id) {
        Optional<Train> train = trainService.getTrainById(id);
        return train.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @PostMapping("/search")
    public ResponseEntity<List<Train>> searchTrains(@RequestBody Map<String, String> searchData) {
        String fromStation = searchData.get("fromStation");
        String toStation = searchData.get("toStation");

        // Fetch matching trains based on fromStation and toStation
        List<Train> matchingTrains = trainService.getTrainsByStations(fromStation, toStation);

        // Return the matching trains
        return ResponseEntity.ok(matchingTrains);
    }

    @GetMapping("/trains")
    public ResponseEntity<List<Train>> getTrainsByStations(
            @RequestParam String fromStation,
            @RequestParam String toStation) {

        List<Train> trains = trainService.getTrainsByStations(fromStation, toStation);
        return ResponseEntity.ok(trains);
    }

}
