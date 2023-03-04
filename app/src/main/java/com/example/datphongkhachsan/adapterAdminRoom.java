package com.example.datphongkhachsan;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class adapterAdminRoom extends BaseAdapter {
    Context context;
    ArrayList<room> data;

    public adapterAdminRoom(Context context, ArrayList<room> data) {
        this.context = context;
        this.data = data;
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
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        convertView =inflater.inflate(R.layout.liview_admin_room,parent,false);
        TextView tvNameRoom = convertView.findViewById(R.id.tvNameRoom);
        TextView tvKindRoom = convertView.findViewById(R.id.tvKindRoom);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);
        ImageView imgUpdate = convertView.findViewById(R.id.imgUpdate);
        ImageView imgDelete = convertView.findViewById(R.id.imgDelete);

        tvNameRoom.setText(data.get(position).getRoomName());
        tvKindRoom.setText(data.get(position).getKindRoom());
        tvStatus.setText(data.get(position).getStatus());
        tvPrice.setText("Giá: "+String.valueOf(data.get(position).getPrice()) + ".VND/Giờ");
        imgDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("rooms").document(data.get(position).getId()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
        imgUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context , UpdateRoom.class);
                Bundle bundle = new Bundle();
                bundle.putString("idRooom", data.get(position).getId());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }
}
