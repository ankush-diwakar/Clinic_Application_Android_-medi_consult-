package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class lab_test_Activity extends AppCompatActivity {

    private final String[][] pakages = {
            {"pakage1 : Full body cheakup","","","","2000"},
            {"pakage2 : Mental Health Check-up","","","","1000"},
            {"pakage3 : Diabetic Check-up","","","","800"},
            {"pakage4 : Women’s Health Check-up","","","","3000"},
            {"pakage5 : Cardiac Check-up","","","","5000"},
    };

    private String[] pakageData = {
            "includes physical examination\n"+ "medical history\n"+ "laboratory tests\n" + "to identify any underlying health issues.",
            "includes a thorough assessment\n"+
                    "of mental health issues such as depression, anxiety\n"+
                    "and stress-related disorders.\n"+
                    "It includes counseling and therapy\n"+
                    "sessions to manage these issues.",
            "This check-up is recommended for \n"+
                    "individuals with diabetes or at high risk\n"+
                    "of developing diabetes. \n"+
                    "It includes regular monitoring\n"+
                    "of blood sugar levels, lipid profile,\n"+
                    "and renal function tests.",
            "Women-specific health check-ups include Pap smear,\n"+
                    "breast examination, and pelvic examination.\n"+
                    "These check-ups also focus on identifying \n"+
                    "and managing any gynecological issues.",
            "This check-up is recommended for individuals\n"+
                    "with a family history of heart disease,\n"+
                    "high blood pressure, or high cholesterol levels.\n"+
                    "It includes a complete cardiac evaluation, \n"+
                    "including stress tests, \n"+
                    "ECG, and echocardiography."
    };

    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    Button btnGoToCart,btnBack;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test);
        getSupportActionBar().hide();

        btnGoToCart = findViewById(R.id.Cart_CheckOut);
        btnBack = findViewById(R.id.btnback_an);


        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(lab_test_Activity.this, MainActivity.class);
                startActivity(i1);
            }
        });

        list = new ArrayList();
        for(int i=0;i<pakages.length;++i){
            item = new HashMap<String,String>();
            item.put("line1",pakages[i][0]);
            item.put("line2",pakages[i][1]);
            item.put("line3",pakages[i][2]);
            item.put("line4",pakages[i][3]);
            item.put("line5","Total cost: "+pakages[i][4] +"/-");
            list.add(item);
        }

        sa = new SimpleAdapter(this,list,
            R.layout.multi_line,
            new String[] {"line1","line2","line3","line4","line5"},
            new int[] {R.id.line_a,R.id.line_b,R.id.line_c,R.id.line_d,R.id.line_e}
        );

        listView = findViewById(R.id.listview_vc);
        listView.setAdapter(sa);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent i1 = new Intent(lab_test_Activity.this, lab_test_details_Activity.class);
                i1.putExtra("text1",pakages[i][0]);
                i1.putExtra("text2",pakageData[i]);
                i1.putExtra("text3",pakages[i][4]);
                startActivity(i1);
            }
        });

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(lab_test_Activity.this, view_cart_Activity.class);
                startActivity(i1);
            }
        });
    }
}