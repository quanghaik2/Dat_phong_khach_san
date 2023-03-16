package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EditRoomActivity extends AppCompatActivity {

    ArrayList<room> rooms;
    RadioButton rbVip,rbThuong,rbDon,rbDoi,rbTrong,rbDaThue;
    RadioGroup rgStatus,rgRoomKind;
    EditText edtRoomName, edtPrice;
    Button btnAdd;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    ListView lvRoom;
    String roomKind = "";
    String roomStatus ="";
    String idRoom = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_room);
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
        btnAdd = findViewById(R.id.btnAdd);
        lvRoom = findViewById(R.id.lvRoom);
        rooms = new ArrayList<>();
        adapterAdminRoom adapter = new adapterAdminRoom(EditRoomActivity.this, rooms);




        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String roomName = edtRoomName.getText().toString();
                String roomPrice = edtPrice.getText().toString();
                if(roomName.length() != 0 && roomPrice.length() != 0){
                    db.collection("rooms").whereEqualTo("NameRoom", roomName)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                                    if(queryDocumentSnapshots.isEmpty()){
                                        if(rbVip.isChecked()) {
                                            // RadioButton 1 được chọn
                                            roomKind = rbVip.getText().toString();
                                        } else if(rbThuong.isChecked()) {
                                            // RadioButton 2 được chọn
                                            roomKind = rbThuong.getText().toString();
                                        } else if(rbDon.isChecked()) {
                                            // RadioButton 3 được chọn
                                            roomKind = rbDon.getText().toString();
                                        } else if(rbDoi.isChecked()){
                                            // Không có RadioButton nào được chọn
                                            roomKind = rbDoi.getText().toString();
                                        }

                                        if(rbTrong.isChecked()) {
                                            // RadioButton 1 được chọn
                                            roomStatus = rbTrong.getText().toString();
                                        } else if(rbDaThue.isChecked()) {
                                            // RadioButton 2 được chọn
                                            roomStatus = rbDaThue.getText().toString();
                                        }

                                        Map<String, Object> items = new HashMap<>();
                                        items.put("NameRoom", roomName);
                                        items.put("KindRoom", roomKind);
                                        items.put("Status", roomStatus);
                                        items.put("Price", roomPrice);
                                        db.collection("rooms")
                                                .add(items)
                                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                                    @Override
                                                    public void onSuccess(DocumentReference documentReference) {
                                                        Toast.makeText(EditRoomActivity.this, "Thêm phòng thành công", Toast.LENGTH_SHORT).show();
                                                        String id = documentReference.getId();
                                                        rooms.add(new room(roomName, roomKind, roomStatus, Integer.parseInt(roomPrice),id));
                                                        lvRoom.setAdapter(adapter);
                                                        adapter.notifyDataSetChanged();
                                                    }
                                                })
                                                .addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(EditRoomActivity.this, "Thêm phòng thất bại", Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                    }
                                    else {
                                        Toast.makeText(EditRoomActivity.this, "Phòng đã tồn tại", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });

                }
                else {
                    Toast.makeText(EditRoomActivity.this, "Không được để nhập bị trống", Toast.LENGTH_SHORT).show();
                }
            }
        });

        db.collection("rooms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot snapshots = task.getResult();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : snapshots){
                                String name = String.valueOf(doc.get("NameRoom"));
                                String kind = String.valueOf(doc.get("KindRoom"));
                                String status = String.valueOf(doc.get("Status"));
                                String idRoom = doc.getId();
                                int price = 0;
                                if (doc.get("Price").toString().equals("")){
                                    price = 0;
                                }
                                else {
                                    price = Integer.parseInt(doc.get("Price").toString());
                                }
                                rooms.add(new room(name, kind, status, price,idRoom));
//                                Toast.makeText(Home_Page.this, name + kind + status + price, Toast.LENGTH_SHORT).show();
                            }
                            lvRoom.setAdapter(adapter);
                            adapter.notifyDataSetChanged();
                        }
                    }
                });

//        lvRoom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                edtRoomName.setText(rooms.get(position).getRoomName());
//                edtPrice.setText(String.valueOf(rooms.get(position).getPrice()));
//                idRoom = rooms.get(position).getId();
//            }
//        });

//        Bundle bundle = getIntent().getExtras();
//        String NameRoomUpdate = bundle.getString("NameRoomUpdate","");
//        String KindRoomUpdate = bundle.getString("KindRoomUpdate","");
//        String PriceUpdate = bundle.getString("PriceUpdate","");
//        String StatusUpdate = bundle.getString("StatusUpdate","");
//        String idRoomUpdate = bundle.getString("IdUpdate","");
//        rooms.add(new room(NameRoomUpdate, KindRoomUpdate,StatusUpdate, Integer.parseInt(PriceUpdate), idRoomUpdate));
//        lvRoom.setAdapter(adapter);
//        adapter.notifyDataSetChanged();


    }
}