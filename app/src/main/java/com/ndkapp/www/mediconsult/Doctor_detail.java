package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;

public class Doctor_detail extends AppCompatActivity {
    private String[][] docDetails1 = {
            { "Dr. Anjali Gupta", "Apollo Hospital", "15 years", "+91 9876543210", "800"},
            { "Dr. Rohit Sharma", "Sir Ganga Ram Hospital", "10 years", "+91 9876543290", "1000"},
            {  "Dr. Alok Singh", "Jaslok Hospital", "8 years", "+91 9876543270", "1200"},
            {   "Dr. Neeta Patel", "AIIMS Hospital", "12 years", "+91 9876543250", "900"},
            {  "Dr. Rakesh Verma", "Manipal Hospital", "6 years", "+91 9876543230", "1100"}
    };

    private String[][] docDetails2 = {
            {   "Dr. Nisha Gupta", "Apollo Hospital", "11 years", "+91 9876543299", "950"},
            {   "Dr. Rajesh Singh", "AIIMS Hospital", "7 years", "+91 9876543279", "1150"},
            {   "Dr. Priya Verma", "KIMS Hospital", "9 years", "+91 9876543259", "1050"},
            {   "Dr. Sanjay Patel", "Hinduja Hospital", "14 years", "+91 9876543239", "850"},
            {   "Dr. Preeti Sharma", "JKL Hospital", "5 years", "+91 9876543219", "1250"}
    };

    private String[][] docDetails3 = {
            {"Dr. Ankit Singh", "ABC Hospital", "13 years", "+91 9876543298", "900"},
            {"Dr. Sunita Verma", "AIIMS Hospital", "8 years", "+91 9876543278", "1150"},
            {"Dr. Sameer Patel", "AIIMS Hospital", "11 years", "+91 9876543258", "950"},
            {"Dr. Ritu Sharma", "Ruby Hall Clinic", "7 years", "+91 9876543238", "1100"},
            {"Dr. Abhishek Singh", "LMN Hospital", "10 years", "+91 9876543218", "1000"}
    };

    private String[][] docDetails4 = {
            {   "Dr. Nisha Gupta", "DEF Hospital", "11 years", "+91 9876543299", "950"},
            {   "Dr. Rajesh Singh", "Sir Ganga Ram Hospital", "7 years", "+91 9876543279", "1150"},
            {   "Dr. Priya Verma", "Ruby Hall Clinic", "9 years", "+91 9876543259", "1050"},
            {   "Dr. Sanjay Patel", "AIIMS Hospital", "14 years", "+91 9876543239", "850"},
            {   "Dr. Preeti Sharma", "JKL Hospital", "5 years", "+91 9876543219", "1250"}
    };

    private String[][] docDetails5 = {
            {"Dr. Neha Gupta", "Apollo Hospital", "12 years", "+91 9876543297", "850"},
            {"Dr. Akash Verma", "AIIMS Hospital", "6 years", "+91 9876543277", "1200"},
            {"Dr. Kavita Patel", "AIIMS Hospital", "9 years", "+91 9876543257", "1200"},
            {"Dr. Vikram Singh", "Sir Ganga Ram Hospital", "16 years", "+91 9876543296", "800"},
            {"Dr. Ruchi Sharma", "Ruby Hall Clinic", "13 years", "+91 9876543276", "900"},
            {"Dr. Manish Patel", "Ruby Hall Clinic", "10 years", "+91 9876543256", "1100"}
    };

    String[][] doctor_details = {};
    ArrayList list;
    SimpleAdapter sa;
    HashMap<String,String> item;


    TextView tv;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor_detail);
        getSupportActionBar().hide();

        tv = findViewById(R.id.title);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("family Physicians")==0){
            doctor_details = docDetails1;
        } else if (title.compareTo("Dietician")==0) {
            doctor_details = docDetails2;
        }else if (title.compareTo("Dentist")==0) {
            doctor_details = docDetails3;
        }else if (title.compareTo("Surgeon")==0) {
            doctor_details = docDetails4;
        }else{
            doctor_details = docDetails5;
        }

        btn = findViewById(R.id.btnback_an);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(Doctor_detail.this, findDoctor.class);
                startActivity(i1);
            }
        });

        list = new ArrayList();
        for(int i=0;i<doctor_details.length;i++){
            item = new HashMap<String,String>();
            item.put("line1",doctor_details[i][0]);
            item.put("line2",doctor_details[i][1]);
            item.put("line3",doctor_details[i][2]);
            item.put("line4",doctor_details[i][3]);
            item.put("line5", "Cons Fees: "+doctor_details[i][4]+"/-");
            list.add(item);

            sa = new SimpleAdapter(this, list,
                    R.layout.multi_line,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
                    );
            ListView lst = findViewById(R.id.listview_vc);
            lst.setAdapter(sa);

            lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent i1 = new Intent(Doctor_detail.this, Book_appointment_Activity.class);
                    i1.putExtra("text2",doctor_details[i][0]);
                    i1.putExtra("text3",doctor_details[i][1]);
                    i1.putExtra("text4",doctor_details[i][3]);
                    i1.putExtra("text5",doctor_details[i][4]);

                    startActivity(i1);
                }
            });
        }
    }
}