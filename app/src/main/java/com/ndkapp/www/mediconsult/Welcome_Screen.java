package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class Welcome_Screen extends AppCompatActivity {
    Timer t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen);
        getSupportActionBar().hide();

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();
        TextView tv = findViewById(R.id.textView8);
        Intent it = getIntent();
        String title = it.getStringExtra("uName");
        tv.setText("Welcome "+ title +" !");

        t = new Timer();
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Intent i1 = new Intent(Welcome_Screen.this, MainActivity.class);
                startActivity(i1);
                finish();
            }
        },2000);
    }
}