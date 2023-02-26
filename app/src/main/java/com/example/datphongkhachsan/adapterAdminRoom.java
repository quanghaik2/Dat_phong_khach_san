package com.example.datphongkhachsan;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class adapterAdminRoom extends BaseAdapter {
    Context context;
    ArrayList<room> data;
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
        tvPrice.setText(String.valueOf(data.get(position).getPrice()) + ".đ");
        return convertView;
    }
}
