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
public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long trainid;
    private String start;
    private String destination;
    private String date;
    private String bookingseat;

    public Long getId() {
        return id;
    }

    public Long getTrainid() {
        return trainid;
    }

    public String getStart() {
        return start;
    }

    public String getDestination() {
        return destination;
    }

    public String getDate() {
        return date;
    }

    public String getBookingseat() {
        return bookingseat;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTrainid(Long trainid) {
        this.trainid = trainid;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setBookingseat(String bookingseat) {
        this.bookingseat = bookingseat;
    }
}
