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

public class ReserveSeat extends AppCompatActivity {
    EditText username;
    EditText password;
    Button login;
    TextView currentUser;
    Spinner flights;
    Spinner tickets;
    PersonHelper personHelper;
    Person currentLoged;
    FlightsHelper flightsHelper;
    List<Flight> flightList;
    Button reserve;
    ReservationHelper reservationHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_seat);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        currentUser = (TextView) findViewById(R.id.currentUser);
        flights = (Spinner) findViewById(R.id.flights);
        tickets = (Spinner) findViewById(R.id.tickets);
        personHelper = new PersonHelper(ReserveSeat.this);
        flightsHelper = new FlightsHelper(ReserveSeat.this);
        Integer TicketCount[] = {1,2,3,4,5,6,7};
        reserve = (Button)findViewById(R.id.reserve);
        reservationHelper = new ReservationHelper(ReserveSeat.this);
         reserve.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 Flight flight = (Flight) flights.getSelectedItem();
                 int ticks = Integer.parseInt(tickets.getSelectedItem().toString());
                 Reservation reservation = new Reservation();
                 reservation.setFlight_no(flight.getFlight_No());
                 reservation.setUsername(username.getText().toString());
                 reservation.setNumTickets(ticks);
                 reservationHelper.addReservationItem(reservation);
                 Toast.makeText(ReserveSeat.this,"Success",Toast.LENGTH_LONG).show();
                 Intent intent = MainMenu.newIntent(ReserveSeat.this);
                 startActivity(intent);
             }
         });

       flightList = flightsHelper.getFlights();
        flights.setAdapter(populateDropDown(flightList));
        tickets.setAdapter(populatetickets(TicketCount));
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                currentLoged = personHelper.verifyLogin(user,pass);
                if(currentLoged == null){
                    Toast.makeText(ReserveSeat.this,"Login Failed try again",Toast.LENGTH_LONG).show();
                    return;
                }else {
                    currentUser.setText("You are currently logged in under the username " + username.getText().toString());
                }
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,ReserveSeat.class);
        return intent;
    }
    private ArrayAdapter<Flight> populateDropDown(List<Flight> flightList){
        ArrayAdapter<Flight> flights = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,flightList);
        return flights;
    }
    private ArrayAdapter<Integer> populatetickets(Integer []ticks){
        ArrayAdapter<Integer> tickets = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_dropdown_item,ticks);
        return tickets;
    }

}
