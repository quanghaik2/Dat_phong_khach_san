package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class Chi_tiet_phong extends AppCompatActivity {
    TextView tvRoomName,tvRoomKind,tvPrice,tvStatus;
    Button btnBookRoom;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);
        tvRoomName = findViewById(R.id.tvRoomName);
        tvRoomKind = findViewById(R.id.tvRoomeKind);
        tvPrice = findViewById(R.id.tvPrice);
        tvStatus = findViewById(R.id.tvStatus);
        btnBookRoom = findViewById(R.id.btnBookRoom);

        Bundle bd = getIntent().getExtras();
        String name = bd.getString("nameRoom","");
        String kind = bd.getString("kindRoom","");
        String price =String.valueOf(bd.getInt("price",1));
        String status = bd.getString("status","");
        String idRoom = bd.getString("id","");
        String idUser = bd.getString("idUser","");

        tvRoomName.setText(name);
        tvRoomKind.setText(kind);
        tvPrice.setText(price + ".đ");
        tvStatus.setText(status);

        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (status.equals("Trống")){
                    db.collection("BookRoom").whereEqualTo("Room",idRoom)
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    if (documentSnapshots.isEmpty()){
                                        Intent intent = new Intent(Chi_tiet_phong.this , BookRoomActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("idRoom", idRoom);
                                        bundle.putString("idUser",idUser);
                                        intent.putExtras(bundle);
                                        startActivity(intent);
                                        Toast.makeText(Chi_tiet_phong.this, idUser, Toast.LENGTH_SHORT).show();
                                    }
                                    else {
                                        Toast.makeText(Chi_tiet_phong.this, "Phòng đã được đặt", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(Chi_tiet_phong.this, "Phòng đã được đặt", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}