package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class Book_appointment_Activity extends AppCompatActivity {
    EditText et1,et2,et3,et4;
    String Fullname;
    String Address;
    String Contact;
    String Fees;
    Button dt,tm;

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_appointment);
        getSupportActionBar().hide();
        et1 = findViewById(R.id.etFullname);
        et2 = findViewById(R.id.etAddress);
        et3 = findViewById(R.id.etPinCode);
        et4 = findViewById(R.id.etContact);
        dt = findViewById(R.id.buttonAppDate);
        tm = findViewById(R.id.buttonAppDate);

        et1.setKeyListener(null);
        et2.setKeyListener(null);
        et3.setKeyListener(null);
        et4.setKeyListener(null);

        Intent it = getIntent();
        Fullname = it.getStringExtra("text2");
        Address = it.getStringExtra("text3");
        Contact = it.getStringExtra("text4");
        Fees = it.getStringExtra("text5");

        et1.setText(Fullname);
        et2.setText(Address);
        et3.setText(Contact);
        et4.setText(Fees);

        Button backBtn = (Button) findViewById(R.id.btnBack);
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Book_appointment_Activity.this, Doctor_detail.class);
                startActivity(i1);
            }
        });

        Button BookBtn = (Button) findViewById(R.id.btnBook_the_order);
        BookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Database db = new Database(getApplicationContext(),"medicare",null,1);
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();

                db.addOrder(username,et1.getText().toString(),et2.getText().toString(),et3.getText().toString(),0,dt.getText().toString(),tm.getText().toString(),Float.parseFloat(et4.getText().toString()),"appointment");
                Toast.makeText(Book_appointment_Activity.this, "Appointment booked!!!", Toast.LENGTH_SHORT).show();
                Intent i1 = new Intent(Book_appointment_Activity.this, MainActivity.class);
                startActivity(i1);
            }
        });
    }

    public void SetDate(View v) {
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        // Create a new instance of DatePickerDialog and return it.
        DatePickerDialog dpd = new DatePickerDialog(this, new
                DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day)
                    {
                        String dateText = Integer.toString(day) + "/" + Integer.toString(month
                                + 1) + "/" + Integer.toString(year);
                        Button setDate = (Button) findViewById(R.id.buttonAppDate);
                        setDate.setText(dateText);
                    }
                }, year, month, day);

        // set maximum date to be selected as today
        dpd.getDatePicker().setMinDate(c.getTimeInMillis());

        dpd.show();
    }

    public void SetTime(View v) {
        final Calendar c = Calendar.getInstance();
        int hour = c.get(Calendar.HOUR_OF_DAY);
        int minute = c.get(Calendar.MINUTE);
        // Create a new instance TimePickerDialog and return it.
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourOfDay, int minute) {
                        String timeText = Integer.toString(hourOfDay) + ":" +
                                Integer.toString(minute);
                        Button setTime = (Button) findViewById(R.id.buttonAppTime_vc);
                        setTime.setText(timeText);
                    }
                }, hour, minute, false);
        tpd.show();
    }
}