package com.sakib.train_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Train {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String trainName;
    private int coachPerTrain;
    private int numberOfSeatsPerCoach;
    private String fromStation;
    private String toStation;

    // Constructor with parameters
    public Train(String trainName, int coachPerTrain, int numberOfSeatsPerCoach, String fromStation, String toStation) {
        this.trainName = trainName;
        this.coachPerTrain = coachPerTrain;
        this.numberOfSeatsPerCoach = numberOfSeatsPerCoach;
        this.fromStation = fromStation;
        this.toStation = toStation;
    }
}
