package com.example.vaughan.airlinereservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class FlightsHelper extends SQLiteOpenHelper {
    private static final String TAG = "FlightLog";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "Flights.db";

    private SQLiteDatabase db;

    public FlightsHelper(Context context){
        super(context,FlightsSchema.NAME,null,VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table FLIGHTS (" + FlightsSchema.Attributes.FLIGHT_NO + " PRIMARY KEY , " +
        FlightsSchema.Attributes.CAPACITY + ", " + FlightsSchema.Attributes.COST + ", " +
        FlightsSchema.Attributes.DESTINATION + ", " + FlightsSchema.Attributes.ORIGIN + ", " +
        FlightsSchema.Attributes.TIME + ", " + FlightsSchema.Attributes.UUID +")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public long insertFlight(Flight flight){
        ContentValues cv = getContentValues(flight);
        db = this.getWritableDatabase();
        return db.insert(FlightsSchema.NAME,null,cv);
    }
    public long addFlight(Flight flight){
        if(this.getFlight(flight.getFlightID()) == null){
            return insertFlight(flight);
        }else{
           return updateFlight(flight);
        }
    }
    private int updateFlight(Flight flight){
        db = this.getWritableDatabase();
        ContentValues cv = FlightsHelper.getContentValues(flight);
        String whereClause = FlightsSchema.Attributes.UUID + " =? ";
        String[] whereArgs = {flight.getFlightID().toString()};
        try{
            return db.update(FlightsSchema.NAME, cv, whereClause, whereArgs);
        } catch (Exception e){
            Log.d(TAG, "something is wrong in updateLogItem");
            return -1;
        }
    }
    public Flight getFlight(UUID flightUUID){
        String whereClause = FlightsSchema.Attributes.UUID + " =? ";
        String[] whereArgs = {flightUUID.toString()};
        FlightCursorWrapper cursor = new FlightCursorWrapper(this.queryDb(FlightsSchema.NAME,whereClause,whereArgs));
        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getFlight();
        } finally {
            cursor.close();
        }

    }

    public List<Flight> getFlights(){
        List<Flight> flights = new ArrayList<>();
        FlightCursorWrapper cursor = new FlightCursorWrapper(getReadableDatabase().rawQuery("Select * from " + FlightsSchema.NAME,null));
        try{
            if(cursor.getCount() == 0){
                Log.d(TAG, "getLogItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                flights.add(cursor.getFlight());
                cursor.moveToNext();
            }
        }finally {

            cursor.close();
        }

        return flights;
    }
    public static ContentValues getContentValues(Flight flight){
        ContentValues cv = new ContentValues();
        cv.put(FlightsSchema.Attributes.CAPACITY, flight.getCapacity());
        cv.put(FlightsSchema.Attributes.COST,flight.getCost());
        cv.put(FlightsSchema.Attributes.DESTINATION,flight.getDestination());
        cv.put(FlightsSchema.Attributes.FLIGHT_NO,flight.getFlight_No());
        cv.put(FlightsSchema.Attributes.ORIGIN,flight.getOrigin());
        cv.put(FlightsSchema.Attributes.TIME,flight.getTime().toString());
        return cv;
    }
    private Cursor queryDb(String dbName,String whereClause,String[] whereArgs){
        db = this.getWritableDatabase();
        try{
            return db.query(FlightsSchema.NAME,
                    null,
                    whereClause,
                    whereArgs,
                    null,
                    null,
                    null);
        }catch (Exception e){
            Log.d(TAG, "Problem in queryDB!!");
            return null;
        }
    }
    public boolean checkFlightNo(String flightNo){
        db = this.getWritableDatabase();
        String[] whereArgs = {flightNo};
        String [] cols = {FlightsSchema.Attributes.FLIGHT_NO};
        Cursor cursor = db.query(FlightsSchema.NAME,cols,FlightsSchema.Attributes.FLIGHT_NO + " =? ",whereArgs,null,null,null);
        if(cursor.getCount() > 0){
            return false;
        }
        return true;
    }
}
