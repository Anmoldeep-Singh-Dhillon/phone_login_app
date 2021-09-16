package com.example.phonelogin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthOptions;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

public class OtpActivity extends AppCompatActivity {
private EditText otp;
private Button verify;
private TextView number_display;
String phonenumber;
FirebaseAuth mAuth;
String otpid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
       // getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
               // WindowManager.LayoutParams.FLAG_FULLSCREEN);
        mAuth=FirebaseAuth.getInstance();
        setContentView(R.layout.activity_otp);
        otp=findViewById(R.id.otp_text);
        verify=findViewById(R.id.otp_verify_button);
        number_display=findViewById(R.id.mobile_number_displaY);
phonenumber=getIntent().getStringExtra("mobile").toString();
  initiateotp();
Intent intent = getIntent();
String number = getIntent().getStringExtra("mobile_number");
number_display.setText(number);
verify.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        if(otp.getText().toString().isEmpty())
        {
            Toast.makeText(getApplicationContext(), "blank field can't be processed", Toast.LENGTH_SHORT).show();
        }
        else if(otp.getText().toString().length()!=6)
        {
            Toast.makeText(getApplicationContext(), "invalid otp", Toast.LENGTH_SHORT).show();

        }
        else {
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(otpid,otp.getText().toString());
            signInWithPhoneAuthCredential(credential);
        }
    }
});
    }

    private void initiateotp()
    {
        PhoneAuthOptions options =
                PhoneAuthOptions.newBuilder(mAuth)
                        .setPhoneNumber(phonenumber)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
                            @Override
                            public void onCodeSent(@NonNull  String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                              otpid = s;

                            }

                            @Override
                            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                                signInWithPhoneAuthCredential(phoneAuthCredential);
                            }

                            @Override
                            public void onVerificationFailed(@NonNull  FirebaseException e) {
                                Toast.makeText(OtpActivity.this, "error occured", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .build();
        PhoneAuthProvider.verifyPhoneNumber(options);

    }
    private void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(OtpActivity.this,Dashbord.class));
                            finish();
                           // Log.d("TAG", "signInWithCredential:success");
                            //FirebaseUser user = task.getResult().getUser();

                        } else {
                            Toast.makeText(OtpActivity.this, "error occured", Toast.LENGTH_SHORT).show();
                            //if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                // The verification code entered was invalid
                            //}
                        }
                    }
                });
    }
}