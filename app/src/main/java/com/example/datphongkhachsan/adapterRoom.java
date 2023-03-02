package com.example.datphongkhachsan;

//import static androidx.core.content.ContextCompat.startActivity;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class adapterRoom extends BaseAdapter {
    Context context;
    List<room> data;
    String idUser;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public adapterRoom(Context context, List<room> data, String idUser) {
        this.context = context;
        this.data = data;
        this.idUser = idUser;

    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView =inflater.inflate(R.layout.listviewhome,parent,false);
        TextView tvNameRoom = convertView.findViewById(R.id.tvNameRoom);
        TextView tvKindRoom = convertView.findViewById(R.id.tvKindRoom);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        Button btnRoomDetail = convertView.findViewById(R.id.btnRoomDetail);
        Button btnBookRoom = convertView.findViewById(R.id.btnBookRoom);

        tvNameRoom.setText(data.get(position).getRoomName());
        tvKindRoom.setText(data.get(position).getKindRoom());
        tvStatus.setText(data.get(position).getStatus());
        String color = "";
        if(data.get(position).getStatus().equals("Đã thuê")){
             color = "#FF0000";

        }else {
            color = "#33FF00";
        }
        tvStatus.setTextColor(Color.parseColor(color));

        tvPrice.setText(String.valueOf(data.get(position).getPrice()) + ".đ");

        btnRoomDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , Chi_tiet_phong.class);
                Bundle bundle = new Bundle();
                bundle.putString("nameRoom", data.get(position).getRoomName());
                bundle.putString("kindRoom", data.get(position).getKindRoom());
                bundle.putInt("price",data.get(position).getPrice());
                bundle.putString("status",data.get(position).getStatus());
                bundle.putString("id",data.get(position).getId());
                bundle.putString("idUser",idUser);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        btnBookRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (data.get(position).getStatus().equals("Trống") ){
                    Intent intent = new Intent(context, BookRoomActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("idRoom", data.get(position).getId());
                    bundle.putString("idUser",idUser);
                    intent.putExtras(bundle);
                    context.startActivity(intent);
                    db.collection("BookRoom").whereEqualTo("Room",data.get(position).getId())
                            .get()
                            .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                                @Override
                                public void onSuccess(QuerySnapshot documentSnapshots) {
                                    if (documentSnapshots.isEmpty()){
                                        Intent intent = new Intent(context , BookRoomActivity.class);
                                        Bundle bundle = new Bundle();
                                        bundle.putString("idRoom", data.get(position).getId());
                                        bundle.putString("idUser",idUser);
                                        Toast.makeText(context, idUser, Toast.LENGTH_SHORT).show();
                                        intent.putExtras(bundle);
                                        context.startActivity(intent);
                                    }
                                    else {
                                        Toast.makeText(context, "Bạn đã đặt phòng này rồi", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                }
                else {
                    Toast.makeText(context, "Phòng này đã được đặt", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return convertView;
    }
}
