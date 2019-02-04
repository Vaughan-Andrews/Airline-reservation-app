package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainMenu extends AppCompatActivity {
    Button accountCreate;
    Button reserveSeat;
    Button cancelReservation;
    Button manageSystem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);

        accountCreate = (Button) this.findViewById(R.id.accountCreate);
        reserveSeat = (Button)findViewById(R.id.reserveSeat);
        cancelReservation = (Button)findViewById(R.id.cancelReservation);
        manageSystem = (Button)findViewById(R.id.manageSystem);
        manageSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ManageLogin.newIntent(MainMenu.this);
                startActivity(intent);
            }
        });
        reserveSeat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = ReserveSeat.newIntent(MainMenu.this);
                startActivity(intent);
            }
        });
        cancelReservation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CancelReservation.newIntent(MainMenu.this);
                startActivity(intent);
            }
        });
        accountCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = AccountCreate.newIntent(MainMenu.this);
                startActivity(intent);
            }
        });



    }
    public static Intent newIntent(Context context){
        Intent intent = new Intent(context,MainMenu.class);
        return intent;
    }
}
