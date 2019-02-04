package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageLogin extends AppCompatActivity {
    Button manageLogin;
    EditText manageUser;
    EditText managePass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_login);
        manageLogin = (Button) findViewById(R.id.Login);
        manageUser = (EditText) findViewById(R.id.manageUser);
        managePass = (EditText) findViewById(R.id.managePass);
        manageLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = manageUser.getText().toString();
                String pass = managePass.getText().toString();

                if(user.equals("admin2") && pass.equals("admin2")){
                    Toast.makeText(ManageLogin.this, "Login Succesfull", Toast.LENGTH_SHORT).show();
                    Intent intent = ManageSystem.newIntent(ManageLogin.this);
                    startActivity(intent);
                }else{
                    Toast.makeText(ManageLogin.this, "Invalid credentials try again", Toast.LENGTH_SHORT).show();
                    return;
                }
            }
        });
    }

    public static Intent newIntent(Context context){
        Intent intent = new Intent(context, ManageLogin.class);
        return intent;
    }
}
