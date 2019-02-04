package com.example.vaughan.airlinereservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ReservationHelper extends SQLiteOpenHelper {
    private static final String TAG = "ReservationLog";

    private static final int VERSION            = 1;
    public static final String DATABASE_NAME    = "reservation.db";


    public ReservationHelper(Context context){
        super(context,ReservationSchema.NAME,null,VERSION);
    }

    private SQLiteDatabase db;
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create table " + ReservationSchema.NAME + "(" + ReservationSchema.Attribtues.FlightNo + ", " +
        ReservationSchema.Attribtues.numTicks + ", "+ReservationSchema.Attribtues.ISCANCELED+ ", " + ReservationSchema.Attribtues.reservationId + ", " + ReservationSchema.Attribtues.UserName +", "+ "Primary key (" +
                ReservationSchema.Attribtues.UserName + ", " + ReservationSchema.Attribtues.FlightNo + ")" +")" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private long insertReservation(Reservation reservation){
        ContentValues cv = getContentValues(reservation);
        db = this.getWritableDatabase();
        return db.insert(PersonSchema.Name,null,cv);
    }

    public long addReservationItem(Reservation reservation){
        if(this.getReservation(reservation.getRes_id()) == null){
            return insertReservation(reservation);
        }else{
            return updateReservation(reservation);
        }
    }
    private int updateReservation(Reservation reservation) {
        db = this.getWritableDatabase();
        ContentValues cv = getContentValues(reservation);
        String whereClause;
        return 0;
    }
    private Reservation getReservation(UUID resUUID){
        String whereClause = ReservationSchema.Attribtues.reservationId + " =? ";
        String[] whereArgs = {resUUID.toString()};
        ReservationCursorWrapper cursor = new ReservationCursorWrapper(this.queryDb(PersonSchema.Name,null,whereClause,whereArgs));
        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getReservation();
        } finally {
            cursor.close();
        }

    }
    public List<Reservation> getReservations(){

        List<Reservation> reservations = new ArrayList<>();
        ReservationCursorWrapper cursor = new ReservationCursorWrapper(getReadableDatabase().rawQuery("Select * from " +ReservationSchema.NAME,null));
        try{
            if(cursor.getCount() == 0){
                Log.d(TAG, "getLogItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                reservations.add(cursor.getReservation());
                cursor.moveToNext();
            }
        }finally {

            cursor.close();
        }

        return reservations;
    }

    private Cursor queryDb(String DBname, String[] cols, String whereClause, String[] whereArgs){
        db = this.getWritableDatabase();
        try{
            return db.query(ReservationSchema.NAME,cols,whereClause,whereArgs,null,null,null);
        }catch(Exception e){
            Log.d(TAG,"problem in person");
            return null;
        }
    }
    public static ContentValues getContentValues(Reservation reservation){
        ContentValues cv = new ContentValues();
        cv.put(ReservationSchema.Attribtues.FlightNo,reservation.getFlight_no());
        cv.put(ReservationSchema.Attribtues.numTicks,reservation.getNumTickets());
        cv.put(ReservationSchema.Attribtues.UserName,reservation.getUsername());
        cv.put(ReservationSchema.Attribtues.reservationId,reservation.getRes_id().toString());
        cv.put(ReservationSchema.Attribtues.ISCANCELED,reservation.getIsCancled());
        return cv;
    }

    public List<Reservation> getUsersReservations(String username){
        List<Reservation> reservations = new ArrayList<>();
        ReservationCursorWrapper cursor = new ReservationCursorWrapper(getReadableDatabase().rawQuery("Select * from " +ReservationSchema.NAME + " where " +ReservationSchema.Attribtues.UserName + " = " + username,null));
        try{
            if(cursor.getCount() == 0){
                Log.d(TAG, "getLogItems returned nothing...");
                return null;
            }
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                reservations.add(cursor.getReservation());
                cursor.moveToNext();
            }
        }finally {

            cursor.close();
        }

        return reservations;
    }
    public void updateReservation(){

    }

}
