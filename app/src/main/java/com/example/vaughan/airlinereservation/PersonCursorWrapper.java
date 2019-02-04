package com.example.vaughan.airlinereservation;

import android.database.Cursor;
import android.database.CursorWrapper;
import java.util.UUID;

public class PersonCursorWrapper extends CursorWrapper {

    /**
     * Creates a cursor wrapper.
     *
     * @param cursor The underlying cursor to wrap.
     */
    public PersonCursorWrapper(Cursor cursor) {
        super(cursor);
    }
    public Person getPerson(){
        String uuid = getString(getColumnIndex(PersonSchema.Attributes.UUID));
        String username = getString(getColumnIndex(PersonSchema.Attributes.userName));
        String password = getString(getColumnIndex(PersonSchema.Attributes.password));
        Person person = new Person(UUID.fromString(uuid));
        person.setPassword(password);
        person.setUsername(username);
        return person;

    }
}
