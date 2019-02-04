package com.example.vaughan.airlinereservation;

import java.util.UUID;

public class Reservation {
    private String flight_no;
    private String username;
    private UUID res_id;
    private int numTickets;
    private int isCancled;

    public int getIsCancled() {
        return isCancled;
    }

    public void setIsCancled(int isCancled) {
        this.isCancled = isCancled;
    }

    public Reservation(){
        res_id = UUID.randomUUID();
        isCancled = 0;

    }
    public String getFlight_no() {
        return flight_no;
    }

    public void setFlight_no(String flight_no) {
        this.flight_no = flight_no;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public int getNumTickets() {
        return numTickets;
    }

    public void setNumTickets(int numTickets) {
        this.numTickets = numTickets;
    }

    public UUID getRes_id() {
        return res_id;
    }
    public void setRes_id(UUID uuid){
        this.res_id = uuid;
    }


}
