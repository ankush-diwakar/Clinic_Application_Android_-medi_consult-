package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Book_Activity extends AppCompatActivity {

    EditText edName,edAdd,edCon,edPin;
    Button btnBook;
    String[] price;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book);
        getSupportActionBar().hide();

        edName = findViewById(R.id.etFullname);
        edAdd = findViewById(R.id.etAddress);
        edCon = findViewById(R.id.etContact);
        edPin = findViewById(R.id.etPinCode);
        btnBook = findViewById(R.id.btnBook_the_order);

        Intent intent = getIntent();
        price = intent.getStringExtra("price").toString().split(java.util.regex.Pattern.quote(":"));
        final String date = intent.getStringExtra("date");
        final String time = intent.getStringExtra("time");

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                Database db = new Database(getApplicationContext(),"medicare",null,1);
                db.addOrder(username,edName.getText().toString(),edAdd.getText().toString(),edCon.getText().toString(),Integer.parseInt(edPin.getText().toString()),date,time,Float.parseFloat(price[1].toString()),"lab");
                db.removecart(username,"lab");
                Toast.makeText(Book_Activity.this, "Booking done successfully!", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(Book_Activity.this, MainActivity.class);
                startActivity(i1);

            }
        });

    }
}