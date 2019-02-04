package com.example.vaughan.airlinereservation;

import java.util.Date;
import java.util.UUID;

public class Flight {
    private int capacity;
    private String origin;
    private String destination;
    private Date time;
    private String flight_No;
    private double cost;
    private UUID flightID;


    public UUID getFlightID() {
        return flightID;
    }

    public Flight(int capacity, String origin, String destination, Date time, String flight_No, double cost) {
        flightID = UUID.randomUUID();

        this.capacity = capacity;
        this.origin = origin;
        this.destination = destination;
        this.time = time;
        this.flight_No = flight_No;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Flight{" +
                "capacity=" + capacity +
                ", origin='" + origin + '\'' +
                ", destination='" + destination + '\'' +
                ", time=" + time +
                ", flight_No=" + flight_No +
                ", cost=" + cost +
                '}';
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getFlight_No() {
        return flight_No;
    }

    public void setFlight_No(String flight_No) {
        this.flight_No = flight_No;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }
}
