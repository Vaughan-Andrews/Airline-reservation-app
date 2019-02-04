package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

public class AddFlight extends AppCompatActivity {
    EditText flightno;
    EditText origin;
    EditText destination;
    EditText capacity;
    EditText cost;
    EditText time;
    Button addflight;
    FlightsHelper flightsHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_flight);
        flightno = findViewById(R.id.flightNumber);
        origin = findViewById(R.id.origin);
        destination = findViewById(R.id.destination);
        capacity = findViewById(R.id.capacity);
        cost = findViewById(R.id.cost);
        time = findViewById(R.id.time);
        addflight = findViewById(R.id.addflight);
        flightsHelper = new FlightsHelper(this);
        addflight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String flightnum = flightno.getText().toString();
                String origins = origin.getText().toString();
                String dest = destination.getText().toString();
                int capac =Integer.parseInt(capacity.getText().toString());
                double costs = Double.parseDouble(cost.getText().toString());
                Date date = new Date(Long.parseLong(time.getText().toString()));
                Flight flight = new Flight(capac,origins,dest,date,flightnum,costs);
                if(flightsHelper.checkFlightNo(flightnum)){
                    flightsHelper.insertFlight(flight);
                    Toast.makeText(AddFlight.this,"Flight added successfully",Toast.LENGTH_LONG).show();
                    Intent intent = MainMenu.newIntent(AddFlight.this);
                    startActivity(intent);
                }else{
                    Toast.makeText(AddFlight.this,"Flight already Exists",Toast.LENGTH_LONG).show();
                }



            }
        });
    }
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,AddFlight.class);
        return intent;
    }
}
