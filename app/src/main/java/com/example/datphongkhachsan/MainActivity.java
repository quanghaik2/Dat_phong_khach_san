package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        FirebaseDatabase database = FirebaseDatabase.getInstance();

    }

    // Write a message to the database
    // https://hothel-database-default-rtdb.asia-southeast1.firebasedatabase.app

//    myRef.setValue("Hello, World!");


}