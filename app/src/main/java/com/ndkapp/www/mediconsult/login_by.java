package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login_by extends AppCompatActivity {
    EditText username,password;
    TextView signUpRedirector;
    Button logIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_by);
        getSupportActionBar().hide();

        username = (EditText) findViewById(R.id.etAddress);
        password =(EditText) findViewById(R.id.etPinCode);
        signUpRedirector = (TextView) findViewById(R.id.tvSignup);
        logIn = (Button) findViewById(R.id.btnBook_the_order);
        final Database db = new Database(getApplicationContext(),"medicare",null,1);
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String us = username.getText().toString();
                String ps = password.getText().toString();
                if(us.length()==0 || ps.length()==0){
                    Toast.makeText(login_by.this, "Please fill all the details!", Toast.LENGTH_SHORT).show();
                }else{
                    if(db.login(us,ps)==1){
                        Toast.makeText(login_by.this, "Login Success!", Toast.LENGTH_SHORT).show();

                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("username",us);
                        //to apply or save our data with key and value.

                        Intent i1 = new Intent(login_by.this, Welcome_Screen.class);
                        i1.putExtra("uName",us);
                        startActivity(i1);
                    }else {
                        Toast.makeText(login_by.this, "Invalid username or password", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        signUpRedirector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(login_by.this,registration_activity.class);
                startActivity(redirect);
            }
        });
    }
}