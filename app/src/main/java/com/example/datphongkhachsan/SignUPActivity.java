package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;

public class SignUPActivity extends AppCompatActivity {

    EditText  edtSignUpEmail,edtSignUpPassword,edtConfirmPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
    }
}