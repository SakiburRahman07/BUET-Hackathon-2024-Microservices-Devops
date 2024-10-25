package com.sakib.train_service.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Seat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainId;
    private String date;
    private Integer totalSeat = 20;
    private Integer availableSeat=20;

    public Long getId() {
        return id;
    }

    public Long getTrainId() {
        return trainId;
    }

    public String getDate() {
        return date;
    }

    public Integer getTotalSeat() {
        return totalSeat;
    }

    public Integer getAvailableSeat() {
        return availableSeat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrainId(Long trainId) {
        this.trainId = trainId;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTotalSeat(Integer totalSeat) {
        this.totalSeat = totalSeat;
    }

    public void setAvailableSeat(Integer availableSeat) {
        this.availableSeat = availableSeat;
    }
}
