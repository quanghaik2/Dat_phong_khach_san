package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUPActivity extends AppCompatActivity {

    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://hotel-database-e38fc-default-rtdb.asia-southeast1.firebasedatabase.app/");

    EditText  edtSignUpEmail,edtSignUpPassword,edtConfirmPassword;
    Button btnSignUp;
    TextView tvToLogin;
    ImageView hidePass,hidePassConfirm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        edtSignUpEmail = findViewById(R.id.edtSignUpEmail);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvToLogin = findViewById(R.id.tvToLogin);
        hidePass = findViewById(R.id.hidePass);
        hidePassConfirm = findViewById(R.id.hidePassConfirm);


        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUPActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        hidePass.setOnClickListener(new View.OnClickListener() {
//            Drawable drawable = hidePass.getDrawable();

            @Override
            public void onClick(View v) {
                if(edtSignUpPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    edtSignUpPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePass.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
                    edtSignUpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePass.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });

        hidePassConfirm.setOnClickListener(new View.OnClickListener() {
//            Drawable drawable = hidePass.getDrawable();

            @Override
            public void onClick(View v) {
                if(edtConfirmPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassConfirm.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
                    edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassConfirm.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });





//        btnSignUp.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//                // lay du lieu tu Edit Text
//                String email = edtSignUpEmail.getText().toString();
//                String password = edtSignUpPassword.getText().toString();
//                String confirm = edtConfirmPassword.getText().toString();
//
//                if(password.equals(confirm)){
//                    databaseReference.child("email").setValue(email);
//                    databaseReference.child("password").setValue(password);
//                }
//
//                finish();
//            }
//        });

//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("messages");
//
//        myRef.setValue("Hello World!");

    }
}