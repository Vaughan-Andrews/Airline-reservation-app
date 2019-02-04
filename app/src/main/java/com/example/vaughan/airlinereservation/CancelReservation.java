package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CancelReservation extends AppCompatActivity {
    Spinner cancels;
    EditText cancelUser;
    EditText cancelPass;
    TextView currentLog;
    Button login;
    Button cancel;
    PersonHelper personHelper;
    List<Reservation> reservations;
    Person currentLoged;
    ReservationHelper resHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cancel_reservation);
        cancel = findViewById(R.id.cancelFlight);
        login = findViewById(R.id.login);
        cancelUser = findViewById(R.id.cancelUsername);
        cancelPass = findViewById(R.id.cancelPassword);
        cancels = findViewById(R.id.flightsToCancel);
        currentLog = findViewById(R.id.cancelCurrUser);
        resHelp = new ReservationHelper(this.getApplicationContext());


        personHelper = new PersonHelper(this.getApplicationContext());
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = cancelUser.getText().toString();
                String pass = cancelPass.getText().toString();
                currentLoged = personHelper.verifyLogin(user,pass);
                if(currentLoged == null){
                    Toast.makeText(CancelReservation.this,"Login Failed try again",Toast.LENGTH_LONG).show();
                    populate();
                }else {
                    currentLog.setText("You are currently logged in under the username " + currentLoged.getUsername());
                }
            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(CancelReservation.this,"Canceled",Toast.LENGTH_LONG).show();
                Intent intent = MainMenu.newIntent(CancelReservation.this);
                startActivity(intent);
            }
        });
    }
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,CancelReservation.class);
        return intent;
    }
    private ArrayAdapter<Reservation> populateDropDown(List<Reservation> resList){
        ArrayAdapter<Reservation> flights = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,resList);
        return flights;
    }
    private void populate(){
        reservations = resHelp.getUsersReservations(currentLoged.getUsername());
        cancels.setAdapter(populateDropDown(reservations));
    }
}
