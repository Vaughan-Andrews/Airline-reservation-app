package com.example.vaughan.airlinereservation;

import android.database.Cursor;
import android.database.CursorWrapper;

import java.util.UUID;

public class ReservationCursorWrapper extends CursorWrapper {
    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public ReservationCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Reservation getReservation(){
        String uuid = getString(getColumnIndex(ReservationSchema.Attribtues.reservationId));
        String username = getString(getColumnIndex(ReservationSchema.Attribtues.UserName));
        String flightno = getString(getColumnIndex(ReservationSchema.Attribtues.FlightNo));
        int ticks = Integer.parseInt(getString(getColumnIndex(ReservationSchema.Attribtues.numTicks)));
        int canceled = Integer.parseInt(getString(getColumnIndex(ReservationSchema.Attribtues.ISCANCELED)));
        Reservation reservation = new Reservation();
        reservation.setFlight_no(flightno);
        reservation.setNumTickets(ticks);
        reservation.setRes_id(UUID.fromString(uuid));
        reservation.setUsername(username);
        return reservation;
    }
}
