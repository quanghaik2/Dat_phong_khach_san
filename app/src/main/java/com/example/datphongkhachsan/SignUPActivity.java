package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SignUPActivity extends AppCompatActivity {

    EditText  edtSignUpEmail,edtSignUpPassword,edtConfirmPassword;
    Button btnSignUp;
    TextView tvToLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvToLogin = findViewById(R.id.tvToLogin);

        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUPActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
    }
}