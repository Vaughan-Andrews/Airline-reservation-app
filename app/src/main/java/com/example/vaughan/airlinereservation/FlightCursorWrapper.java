package com.example.vaughan.airlinereservation;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.Date;
import java.util.UUID;

public class FlightCursorWrapper extends CursorWrapper {
    public FlightCursorWrapper(Cursor cursor){
        super(cursor);
    }
    public Flight getFlight(){
        String uuid = getString(getColumnIndex(FlightsSchema.Attributes.UUID));
        String flightNo = getString(getColumnIndex(FlightsSchema.Attributes.FLIGHT_NO));
        double cost = Double.parseDouble(getString(getColumnIndex(FlightsSchema.Attributes.COST)));
        String destination = getString(getColumnIndex(FlightsSchema.Attributes.DESTINATION));
        String origin = getString(getColumnIndex(FlightsSchema.Attributes.ORIGIN));
        int capacity = Integer.parseInt(getString(getColumnIndex(FlightsSchema.Attributes.CAPACITY)));
        Date date = new Date(getLong(getColumnIndex(FlightsSchema.Attributes.TIME)));

        Flight flight = new Flight(capacity,origin,destination,date,flightNo,cost);

        return flight;


    }
}
