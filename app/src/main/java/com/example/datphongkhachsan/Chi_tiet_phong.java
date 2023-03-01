package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Chi_tiet_phong extends AppCompatActivity {
    TextView tvRoomName,tvRoomKind,tvPrice,tvStatus;
    Button btnBookRoom;

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
        Toast.makeText(this, name + " " + kind, Toast.LENGTH_SHORT).show();

        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chi_tiet_phong.this , BookRoomActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("idRoom", idRoom);
                bundle.putString("idUser",idUser);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

    }
}