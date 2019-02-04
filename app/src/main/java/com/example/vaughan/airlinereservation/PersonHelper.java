package com.example.vaughan.airlinereservation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import android.util.Log;
import java.util.List;
import java.util.UUID;

public class PersonHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "person.db";
    private static final String TAG = "person";
    private SQLiteDatabase db;
    private static final int VERSION = 1;

    public PersonHelper(Context context){
        super(context,DATABASE_NAME,null,VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + PersonSchema.Name + " ( _id integer primary key autoincrement, " +
        PersonSchema.Attributes.userName + ", " + PersonSchema.Attributes.password+", " + PersonSchema.Attributes.UUID+ ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
    private long insertPerson(Person person){
        ContentValues cv = getContentValues(person);
        db = this.getWritableDatabase();
        return db.insert(PersonSchema.Name,null,cv);
    }
    public long addLogItem(Person person){
        if(this.getPerson(person.getPersonID()) == null){
            return insertPerson(person);
        }else{
            return updatePerson(person);
        }
    }
    private int updatePerson(Person person) {
        db = this.getWritableDatabase();
        ContentValues cv = getContentValues(person);
        String whereClause;
        return 0;
    }
    private Person getPerson(UUID personUUID){
        String whereClause = PersonSchema.Attributes.UUID + " =? ";
        String[] whereArgs = {personUUID.toString()};
        PersonCursorWrapper cursor = new PersonCursorWrapper(this.queryDb(PersonSchema.Name,null,whereClause,whereArgs));
        try {
            if (cursor.getCount() == 0){
                Log.d(TAG, "No results from getLogItem");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getPerson();
        } finally {
            cursor.close();
        }

    }
    public List<Person> getPersons(){
        return null;
    }

    private Cursor queryDb(String DBname,String[] cols,String whereClause,String[] whereArgs){
        db = this.getWritableDatabase();
        try{
            return db.query(PersonSchema.Name,cols,whereClause,whereArgs,null,null,null);
        }catch(Exception e){
            Log.d(TAG,"problem in person");
            return null;
        }
    }
    public static ContentValues getContentValues(Person person){
        ContentValues cv = new ContentValues();
        cv.put(PersonSchema.Attributes.password,person.getPassword());
        cv.put(PersonSchema.Attributes.userName,person.getUsername());
        cv.put(PersonSchema.Attributes.UUID,person.getPersonID().toString());
        return cv;
    }
    public boolean checkUsername(String username){
        String cols[] = {PersonSchema.Attributes.userName};
        String whereClause[] = {username};
        PersonCursorWrapper cursor = new PersonCursorWrapper(this.queryDb(PersonSchema.Name,cols,PersonSchema.Attributes.userName +" =? ",whereClause));

        cursor.moveToFirst();
        while(!cursor.isAfterLast()){
            return false;
        }
        return true;
    }
    public Person verifyLogin(String username,String password){
        String cols[] = {PersonSchema.Attributes.userName,PersonSchema.Attributes.password};
        String whereArgs[] = {username,password};
        String whereClause = PersonSchema.Attributes.userName + " =? AND " + PersonSchema.Attributes.password + " =? ";
        PersonCursorWrapper cursor = new PersonCursorWrapper(this.queryDb(PersonSchema.Name,null,whereClause,whereArgs));
        System.out.println(cursor.getCount());
        if(cursor.getCount() == 1){
            cursor.moveToFirst();
            return cursor.getPerson();
        }
        return null;
    }

}
