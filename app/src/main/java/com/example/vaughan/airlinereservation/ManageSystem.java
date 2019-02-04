package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.util.List;


public class ManageSystem extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    Button addFLight;
    PersonHelper personHelper;
    ReservationHelper reservationHelper;
    FlightsHelper flightsHelper;
    List<Flight> flights;
    List<Reservation> reservations;
    List<Person> people;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_system);
        addFLight = findViewById(R.id.addFlightView);
        flights = flightsHelper.getFlights();
        reservations = reservationHelper.getReservations();
        people = personHelper.getPersons();
        addFLight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AddFlight.newIntent(ManageSystem.this);
                startActivity(intent);
            }
        });
    }


    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,ManageSystem.class);
        return intent;
    }
}
