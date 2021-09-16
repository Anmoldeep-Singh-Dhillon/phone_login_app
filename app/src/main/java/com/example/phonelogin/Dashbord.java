package com.example.phonelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.firebase.auth.FirebaseAuth;

public class Dashbord extends AppCompatActivity {
Button button;
RadioGroup rg;
RadioButton r1,r2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashbord);
        button=findViewById(R.id.logout_button);
        rg = findViewById(R.id.radioGroup);
        r1=findViewById(R.id.radioButton1);
        r2=findViewById(R.id.radioButton2);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(Dashbord.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}