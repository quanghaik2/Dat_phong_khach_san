package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class BookRoomActivity extends AppCompatActivity {
    EditText edtTime;
    Button btnBookRoom;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_room);
        db = FirebaseFirestore.getInstance();
        edtTime = findViewById(R.id.edtTime);
        btnBookRoom = findViewById(R.id.btnBookRoom);

        Bundle bundle = getIntent().getExtras();
        String idUser = bundle.getString("idUser");
        String idRoom = bundle.getString("idRoom");
        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String,Object> items = new HashMap<>();
                items.put("Room",idRoom);
                items.put("User",idUser);
                items.put("Time", edtTime.getText().toString());
                db.collection("BookRoom")
                        .add(items)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(BookRoomActivity.this, "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(BookRoomActivity.this, "Đặt phòng thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}