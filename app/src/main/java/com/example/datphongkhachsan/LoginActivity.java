package com.example.datphongkhachsan;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    TextView tvToSignUp;
    ImageView hidePassLogin;
    EditText edtLoginEmail, edtLoginPassword;
    Button btnLogin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvToSignUp = findViewById(R.id.tvToSignUp);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        hidePassLogin = findViewById(R.id.hidePassLogin);
        btnLogin = findViewById(R.id.btnLogin);

        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent integer = new Intent(LoginActivity.this, SignUPActivity.class);
                startActivity(integer);
            }
        });

        hidePassLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(edtLoginPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    edtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
                    edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });



    }
}