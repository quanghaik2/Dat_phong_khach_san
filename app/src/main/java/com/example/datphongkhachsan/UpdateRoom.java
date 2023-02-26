package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class UpdateRoom extends AppCompatActivity {
    ArrayList<room> rooms;
    RadioButton rbVip,rbThuong,rbDon,rbDoi,rbTrong,rbDaThue;
    RadioGroup rgStatus,rgRoomKind;
    EditText edtRoomName, edtPrice;
    Button btnUpdate;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    String roomKind = "";
    String roomStatus ="";
    String idRoom = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_room);
        Bundle bundle = getIntent().getExtras();
        idRoom = bundle.getString("idRooom","");
        edtPrice = findViewById(R.id.edtPrice);
        edtRoomName = findViewById(R.id.edtRoomName);
        rbVip = findViewById(R.id.rbVip);
        rbDon = findViewById(R.id.rbDon);
        rbDoi = findViewById(R.id.rbDoi);
        rbThuong = findViewById(R.id.rbThuong);
        rbTrong = findViewById(R.id.rbTrong);
        rbDaThue = findViewById(R.id.rbDaThue);
        rgRoomKind = findViewById(R.id.rgRoomKind);
        rgStatus = findViewById(R.id.rgStatus);
        btnUpdate = findViewById(R.id.btnUpdate);
        rooms = new ArrayList<>();
        if(rbVip.isChecked()) {
            // RadioButton 1 được chọn
            roomKind = rbVip.getText().toString();
        } else if(rbThuong.isChecked()) {
            // RadioButton 2 được chọn
            roomKind = rbThuong.getText().toString();
        } else if(rbDon.isChecked()) {
            // RadioButton 3 được chọn
            roomKind = rbDoi.getText().toString();
        } else if(rbDoi.isChecked()){
            // Không có RadioButton nào được chọn
            roomKind = rbDon.getText().toString();
        }

        if(rbTrong.isChecked()) {
            // RadioButton 1 được chọn
            roomStatus = rbTrong.getText().toString();
        } else if(rbDaThue.isChecked()) {
            // RadioButton 2 được chọn
            roomStatus = rbDaThue.getText().toString();
        }


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Map<String, Object> items = new HashMap<>();
                items.put("KindRoom", edtRoomName.getText().toString());
                items.put("Price", edtPrice.getText().toString());
                items.put("KindRoom", roomKind);
                items.put("Status", roomStatus);
                Toast.makeText(UpdateRoom.this, idRoom, Toast.LENGTH_SHORT).show();
                db.collection("rooms").document(idRoom)
                        .update(items)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UpdateRoom.this, "Sửa phòng thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UpdateRoom.this, "Sửa phòng thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}