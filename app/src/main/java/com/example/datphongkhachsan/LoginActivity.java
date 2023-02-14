package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    TextView tvToSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvToSignUp = findViewById(R.id.tvToSignUp);
        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent integer = new Intent(LoginActivity.this, SignUPActivity.class);
                startActivity(integer);
            }
        });
    }
}