package com.example.datphongkhachsan;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.content.Context;

import java.util.List;


public class adapterRoom extends BaseAdapter {
    Context context;
    List<room> data;

    public adapterRoom(Context context, List<room> data) {
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
        convertView =inflater.inflate(R.layout.listviewhome,parent,false);
        TextView tvNameRoom = convertView.findViewById(R.id.tvNameRoom);
        TextView tvKindRoom = convertView.findViewById(R.id.tvKindRoom);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);
        TextView tvStatus = convertView.findViewById(R.id.tvStatus);

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

        return convertView;
    }
}
