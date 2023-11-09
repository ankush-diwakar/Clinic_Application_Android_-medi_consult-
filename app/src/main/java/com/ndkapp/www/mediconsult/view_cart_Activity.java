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
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class view_cart_Activity extends AppCompatActivity {

    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateBtn,timeBtn,checkoutBtn,backBtn;
    ArrayList list;
    HashMap item;
    ListView ls;
    SimpleAdapter sa;
    TextView tvTotal_price;
    Button setDate;
    Button setTime;
    private String[][] packages = {};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_cart);
        getSupportActionBar().hide();

        dateBtn =findViewById(R.id.buttonAppDate);
        timeBtn = findViewById(R.id.buttonAppTime_vc);
        checkoutBtn = findViewById(R.id.Cart_CheckOut);
        backBtn = findViewById(R.id.btnback_an);
        tvTotal_price = findViewById(R.id.total_cart_cost);
        ls = findViewById(R.id.listview_vc);
        setDate = (Button) findViewById(R.id.buttonAppDate);
        setTime = (Button) findViewById(R.id.buttonAppTime_vc);

        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username","").toString();

        Database db = new Database(getApplicationContext(),"medicare",null,1);
          float total_amt = 0;
          ArrayList dbData = db.getCartData(username,"lab");

        packages = new String[dbData.size()][];
        for(int i=0;i<packages.length;i++){
            packages[i] = new String[5];
        }

        for(int i=0;i<dbData.size();++i){
            String arrData = dbData.get(i).toString();
            String[] strData = arrData.split(java.util.regex.Pattern.quote("$"));
            packages[i][0] = strData[0];
            packages[i][4] = "Cost : "+strData[1]+"/-";
            total_amt  = total_amt + Float.parseFloat(strData[1]);
        }
        tvTotal_price.setText("Total cost : "+total_amt);

        list = new ArrayList();
        for(int i=0;i<packages.length;++i){
            item = new HashMap<String,String>();
            item.put("line1",packages[i][0]);
            item.put("line2",packages[i][1]);
            item.put("line3",packages[i][2]);
            item.put("line4",packages[i][3]);
            item.put("line5",packages[i][4]);
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_line,
                new String[] {"line1","line2","line3","line4","line5"},
                new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e});

        ls.setAdapter(sa);


        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(view_cart_Activity.this, MainActivity.class);
                startActivity(i1);
            }
        });

        checkoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(view_cart_Activity.this,Book_Activity.class);
                i1.putExtra("price",tvTotal_price.getText());
                i1.putExtra("date",setDate.getText());
                i1.putExtra("time",setTime.getText());
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

                        setTime.setText(timeText);
                    }
                }, hour, minute, false);
        tpd.show();
    }
}