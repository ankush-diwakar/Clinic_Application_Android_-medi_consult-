package com.ndkapp.www.mediconsult;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class registration_activity extends AppCompatActivity {

    EditText email,pass,repass,username;
    Button signUp;
    TextView redirectorTosignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        getSupportActionBar().hide();
        username = (EditText) findViewById(R.id.etAddress);
        email = (EditText) findViewById(R.id.etFullname);
        pass = (EditText) findViewById(R.id.etPinCode);
        repass = (EditText) findViewById(R.id.etContact);
        redirectorTosignIn = (TextView) findViewById(R.id.tvSignIn);
        signUp = (Button) findViewById(R.id.btnBook_the_order);
        final Database db = new Database(getApplicationContext(),"medicare",null,1);


        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String em = email.getText().toString();
                String unm =username.getText().toString();
                String ps = pass.getText().toString();
                String rp = repass.getText().toString();
                if(em.length()==0 || unm.length()==0 || ps.length()==0 || rp.length()==0){
                    Toast.makeText(registration_activity.this, "Please fill all details!", Toast.LENGTH_SHORT).show();
                }
                if(validatePassword(ps) && validateEmail(em)){
                    db.register(unm,em,ps);
                    Toast.makeText(registration_activity.this, "Registered Successfully!", Toast.LENGTH_SHORT).show();
                    Intent redirect = new Intent(registration_activity.this,login_by.class);
                    startActivity(redirect);
                }else{
                    Toast.makeText(registration_activity.this, "Password or email invalid!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        redirectorTosignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent redirect = new Intent(registration_activity.this,login_by.class);
                startActivity(redirect);
            }
        });

    }

    public static boolean validatePassword(String password) {
        if (password == null || password.length() < 8) {
            return false;
        }

        boolean hasCapitalLetter = false;
        boolean hasUniqueCharacter = false;
        Set<Character> uniqueCharacters = new HashSet<>();

        for (char c : password.toCharArray()) {
            if (Character.isUpperCase(c)) {
                hasCapitalLetter = true;
            }
            if (!uniqueCharacters.contains(c)) {
                uniqueCharacters.add(c);
            } else {
                hasUniqueCharacter = true;
            }
        }

        return hasCapitalLetter && !hasUniqueCharacter && uniqueCharacters.size() >= 8;
    }

    //email validation
    public static boolean validateEmail(String email) {
        if (email == null) {
            return false;
        }

        // Email regex pattern
        Pattern pattern = Pattern.compile("^\\w+([\\.-]?\\w+)*@\\w+([\\.-]?\\w+)*(\\.\\w{2,3})+$");

        // Match email against pattern
        Matcher matcher = pattern.matcher(email);

        // Return whether email matches pattern or not
        return matcher.matches();
    }


}