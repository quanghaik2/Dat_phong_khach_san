package com.example.datphongkhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

public class adapterBookRoomHotel extends BaseAdapter {
    Context context;
    List<BookRoomHotel> datas;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    public adapterBookRoomHotel(Context context, List<BookRoomHotel> datas) {
        this.context = context;
        this.datas = datas;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public List<BookRoomHotel> getDatas() {
        return datas;
    }

    public void setDatas(List<BookRoomHotel> datas) {
        this.datas = datas;
    }



    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(context);
        convertView = inflater.inflate(R.layout.list_view_book_room_hotel,parent,false);
        TextView tvNameRoom = convertView.findViewById(R.id.tvNameRoom);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvDateTime = convertView.findViewById(R.id.tvDateTime);
        ImageButton btnAbortRoom = convertView.findViewById(R.id.btnAbortRoom);

        db.collection("rooms").document(datas.get(position).getIdRoom())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        String name = String.valueOf(documentSnapshot.get("NameRoom"));
                        String type = String.valueOf(documentSnapshot.get("KindRoom"));
                        String status = String.valueOf(documentSnapshot.get("Status"));
                        int price = Integer.parseInt(documentSnapshot.get("Price").toString());
                        String idRoom1 = documentSnapshot.getId();
                        tvNameRoom.setText(name);
                        tvPrice.setText(String.valueOf(price));
                    }
                });
        btnAbortRoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.collection("BookRoom").document(datas.get(position).getIdRoom()).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(context, "Xóa thành công", Toast.LENGTH_SHORT).show();
                            }
//                            adapterBookRoomHotel.this.notifyDataSetChanged();
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });

        tvDateTime.setText(datas.get(position).getTime());
        return convertView;
    }
}
