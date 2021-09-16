package com.example.phonelogin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hbb20.CountryCodePicker;

public class MainActivity extends AppCompatActivity {
    private CountryCodePicker ccp;
    private Button b1;
    private EditText mobile_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
         //       WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        ccp = findViewById(R.id.ccp);
        b1 = findViewById(R.id.continuebutton);
        mobile_number = findViewById(R.id.user_mobile);
        ccp.registerCarrierNumberEditText(mobile_number);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mobile_number.getText().toString().isEmpty()){
                    Toast.makeText(MainActivity.this, "Enter Your mobile number", Toast.LENGTH_SHORT).show();
                }

                else{

                Intent intent = new Intent(MainActivity.this,OtpActivity.class);
                intent.putExtra("mobile",ccp.getFullNumberWithPlus().trim());
                intent.putExtra("mobile_number",mobile_number.getText().toString());
                startActivity(intent);
            }}
        });
    }
}