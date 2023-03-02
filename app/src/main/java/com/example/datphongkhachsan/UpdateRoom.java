package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.firestore.QuerySnapshot;

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
        rbVip = findViewById(R.id.rbVipUpdate);
        rbDon = findViewById(R.id.rbDonUpdate);
        rbDoi = findViewById(R.id.rbDoiUpdate);
        rbThuong = findViewById(R.id.rbThuongUpdate);
        rbTrong = findViewById(R.id.rbTrongUpdate);
        rbDaThue = findViewById(R.id.rbDaThueUpdate);
        rgRoomKind = findViewById(R.id.rgRoomKind);
        rgStatus = findViewById(R.id.rgStatus);
        btnUpdate = findViewById(R.id.btnUpdate);
        rooms = new ArrayList<>();


        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                    roomKind = rbDon.getText().toString();
                }

                if(rbTrong.isChecked()) {
                    // RadioButton 1 được chọn
                    roomStatus = rbTrong.getText().toString();
                } else if(rbDaThue.isChecked()) {
                    // RadioButton 2 được chọn
                    roomStatus = rbDaThue.getText().toString();
                }
                    String roomName = edtRoomName.getText().toString();
                    String roomPrice = edtPrice.getText().toString();
                if(roomName != null || roomPrice != null ){
                    Map<String, Object> items = new HashMap<>();
                    items.put("NameRoom", edtRoomName.getText().toString());
                    items.put("Price", edtPrice.getText().toString());
                    items.put("KindRoom", roomKind);
                    items.put("Status", roomStatus);
                    db.collection("rooms").document(idRoom)
                            .update(items)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Intent intent = new Intent(UpdateRoom.this , EditRoomActivity.class);
                                    Bundle bd = new Bundle();
                                    bd.putString("NameRoomUpdate",roomName);
                                    bd.putString("KindRoomUpdate",roomKind);
                                    bd.putString("PriceUpdate",roomPrice);
                                    bd.putString("StatusUpdate",roomStatus);
                                    bd.putString("IdUpdate",idRoom);
                                    intent.putExtras(bd);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(UpdateRoom.this, "Sửa phòng thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
                else {
                    Toast.makeText(UpdateRoom.this, "Không được để trống phần nhập", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}