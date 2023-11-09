package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class findDoctor extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_doctor);
        getSupportActionBar().hide();

        CardView exit = findViewById(R.id.crd_6);
        CardView family_physicians = findViewById(R.id.crd_1);
        CardView dietician = findViewById(R.id.crd_2);
        CardView dentist = findViewById(R.id.crd_3);
        CardView surgeon = findViewById(R.id.crd_4);
        CardView cardiologists = findViewById(R.id.crd_5);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(findDoctor.this, MainActivity.class);
                startActivity(i1);
            }
        });

        family_physicians.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(findDoctor.this, Doctor_detail.class);
                i2.putExtra("title","family Physicians");
                startActivity(i2);
            }
        });

        dietician.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(findDoctor.this, Doctor_detail.class);
                i3.putExtra("title","Dietician");
                startActivity(i3);
            }
        });

        dentist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(findDoctor.this, Doctor_detail.class);
                i3.putExtra("title","Dentist");
                startActivity(i3);
            }
        });

        surgeon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(findDoctor.this, Doctor_detail.class);
                i3.putExtra("title","Surgeon");
                startActivity(i3);
            }
        });

        cardiologists.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(findDoctor.this, Doctor_detail.class);
                i3.putExtra("title","Cardiologists");
                startActivity(i3);
            }
        });
    }
}