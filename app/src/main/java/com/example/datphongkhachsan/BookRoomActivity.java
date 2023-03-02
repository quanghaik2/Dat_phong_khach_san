package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class BookRoomActivity extends AppCompatActivity {
    EditText edtTime;
    Button btnBookRoom;
    FirebaseFirestore db;
    room rm ;
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

        DateFormat fmtDateAndTime;
        fmtDateAndTime = DateFormat.getDateTimeInstance();
        Calendar myCalendar = Calendar.getInstance();
        DatePickerDialog.OnDateSetListener d = new DatePickerDialog.OnDateSetListener()
        {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }
            private void updateLabel() {
                edtTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
            }

        };
        TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener()
        {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                myCalendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                myCalendar.set(Calendar.MINUTE, minute);
                updateLabel();
            }
            private void updateLabel() {
                edtTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
            }
        };
        edtTime.setOnClickListener(v -> {
            new DatePickerDialog(BookRoomActivity.this, d,
                    myCalendar.get(Calendar.YEAR),
                    myCalendar.get(Calendar.MONTH),
                    myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            edtTime.setText(fmtDateAndTime.format(myCalendar.getTime()));
        });

        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Map<String,Object> items = new HashMap<>();
                items.put("Room",idRoom);
                items.put("User",idUser);
                items.put("Time", edtTime.getText().toString());
                Toast.makeText(BookRoomActivity.this, idUser, Toast.LENGTH_SHORT).show();
                db.collection("BookRoom")
                        .add(items)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(BookRoomActivity.this, "Đặt phòng thành công", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(BookRoomActivity.this, InfoBookRoom.class);
                                Bundle bundle = new Bundle();
                                bundle.putString("idUser",idUser);
                                intent.putExtras(bundle);
                                startActivity(intent);
                                Map<String,Object> status = new HashMap<>();
                                status.put("Status","Đã thuê");
                                db.collection("rooms").document(idRoom).update(status);
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