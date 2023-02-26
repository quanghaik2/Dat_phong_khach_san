package com.example.datphongkhachsan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Chi_tiet_phong extends AppCompatActivity {
    TextView tvRoomName,tvRoomKind,tvPrice,tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chi_tiet_phong);
        tvRoomName = findViewById(R.id.tvRoomName);
        tvRoomKind = findViewById(R.id.tvRoomeKind);
        tvPrice = findViewById(R.id.tvPrice);
        tvStatus = findViewById(R.id.tvStatus);

        Bundle bd = getIntent().getExtras();
        String name = bd.getString("nameRoom","");
        String kind = bd.getString("kindRoom","");
        String price =String.valueOf(bd.getInt("price",1));
        String status = bd.getString("status","");
        String idRoom = bd.getString("id","");

        tvRoomName.setText(name);
        tvRoomKind.setText(kind);
        tvPrice.setText(price + ".đ");
        tvStatus.setText(status);
        Toast.makeText(this, name + " " + kind, Toast.LENGTH_SHORT).show();

    }
}