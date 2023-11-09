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

public class lab_test_details_Activity extends AppCompatActivity {

    TextView totalcost,title;
    EditText etDetails;
    Button backBtn,addToCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lab_test_details);
        getSupportActionBar().hide();

        totalcost = findViewById(R.id.cost);
        title = findViewById(R.id.title);
        etDetails = findViewById(R.id.editTextTextMultiLine);
        backBtn = findViewById(R.id.btnback_an);
        addToCart = findViewById(R.id.btn_lt_addToCart);

        etDetails.setKeyListener(null);
        final Intent intent = getIntent();
        title.setText(intent.getStringExtra("text1"));
        etDetails.setText(intent.getStringExtra("text2"));
        totalcost.setText("Total cost : "+intent.getStringExtra("text3")+"/-");

        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(lab_test_details_Activity.this, lab_test_Activity.class);
                startActivity(i1);
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String username = sharedPreferences.getString("username","").toString();
                String product = title.getText().toString();
                float price = Float.parseFloat(intent.getStringExtra("text3").toString());

                Database db = new Database(getApplicationContext(),"medicare",null,1);

                if(db.checkCart(username,product)==1){
                    Toast.makeText(lab_test_details_Activity.this, "Product already added to cart!", Toast.LENGTH_SHORT).show();
                }else{
                    db.addCart(username,product,price,"lab");
                    Toast.makeText(lab_test_details_Activity.this, "Product added to cart!", Toast.LENGTH_SHORT).show();
                    Intent i1 = new Intent(lab_test_details_Activity.this, lab_test_Activity.class);
                    startActivity(i1);
                }
            }
        });
    }
}