package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Write a message to the database
    // https://hothel-database-default-rtdb.asia-southeast1.firebasedatabase.app
    FirebaseDatabase database = FirebaseDatabase.getInstance("https://hothel-database-default-rtdb.asia-southeast1.firebasedatabase.app");
    DatabaseReference myRef = database.getReference("message");
//    myRef.setValue("Hello, World!");


}