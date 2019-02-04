package com.example.vaughan.airlinereservation;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AccountCreate extends AppCompatActivity {
    EditText username;
    EditText password;
    Button submit;
    PersonHelper personHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_create);
        username = (EditText) findViewById(R.id.newUserName);
        password = (EditText) findViewById(R.id.newPassword);
        submit = (Button) findViewById(R.id.submit);

        personHelper = new PersonHelper(this.getApplicationContext());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // verify that username is unqiue and password follows certains things
                Intent intent = null;
                String user = username.getText().toString();
                String pass = password.getText().toString();
                Person person = new Person(user,pass);
                if(isValidPassword(pass) && isUniqueUserName(user)) {
                    personHelper.addLogItem(person);
                    Toast.makeText(getApplicationContext(),"Account successfully created",Toast.LENGTH_LONG).show();
                    intent  = MainMenu.newIntent(AccountCreate.this);
                }else{
                    Toast.makeText(getApplication().getApplicationContext(),"Username or password invalid Try again",Toast.LENGTH_LONG).show();
                }
                if(intent != null){
                    startActivity(intent);
                }
            }
        });

    }
    private boolean isValidPassword(String pass){
        int numCount = 0;
        int length = pass.length();
        if(length < 5){
            return false;
        }
        for(int i = 0; i < length;i++){
            if(Character.isDigit(pass.charAt(i))){
                numCount++;
            }
        }
        if(numCount <= 1){
            return false;
        }
        return true;
    }
    private boolean isUniqueUserName(String user){
        if(user.length() < 5){
            return false;
        }
        PersonHelper help = new PersonHelper(this.getApplicationContext());
        return help.checkUsername(user);
    }
    public static Intent newIntent(Context context){
        return new Intent(context,AccountCreate.class);
    }



}