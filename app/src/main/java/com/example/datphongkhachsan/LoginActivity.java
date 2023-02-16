package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {
    TextView tvToSignUp;
    ImageView hidePassLogin;
    EditText edtloginEmail, edtLoginPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvToSignUp = findViewById(R.id.tvToSignUp);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtloginEmail = findViewById(R.id.edtLoginEmail);
        hidePassLogin = findViewById(R.id.hidePassLogin);
        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent integer = new Intent(LoginActivity.this, SignUPActivity.class);
                startActivity(integer);
            }
        });

        hidePassLogin.setOnClickListener(new View.OnClickListener() {
//            Drawable drawable = hidePass.getDrawable();

            @Override
            public void onClick(View v) {
                if(edtLoginPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    edtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
                    edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });
    }
}