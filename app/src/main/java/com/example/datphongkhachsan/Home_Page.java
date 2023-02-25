package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class Home_Page extends AppCompatActivity {

    GridView glRoom;
    ArrayList<room> rooms;
    FirebaseFirestore db ;
    ImageView imgToLogin;
    Button btnThuong, btnVip,btnDoi,btnDon,btnTrong;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        glRoom = findViewById(R.id.glRoom);
        imgToLogin = findViewById(R.id.imgToLogin);
        rooms = new ArrayList<>();
        db = FirebaseFirestore.getInstance();
        btnThuong = findViewById(R.id.btnThuong);
        btnVip = findViewById(R.id.btnVip);
        btnDoi = findViewById(R.id.btnDoi);
        btnDon = findViewById(R.id.btnDon);
        btnTrong = findViewById(R.id.btnTrong);
        adapterRoom adapter = new adapterRoom(Home_Page.this, rooms);
        // lấy id của User
        Bundle Bundle = getIntent().getExtras();
        String id = Bundle.getString("id");

        imgToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Home_Page.this, id, Toast.LENGTH_SHORT).show();
                Intent mIntent = new Intent(Home_Page.this, Form_Info_User.class);
                Bundle mBundle = new Bundle();
                mBundle.putString("idUser", id);
                mIntent.putExtras(mBundle);
                startActivity(mIntent);
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
                                int price = Integer.parseInt(doc.get("Price").toString());
                                rooms.add(new room(name, kind, status, price));
//                                Toast.makeText(Home_Page.this, name + kind + status + price, Toast.LENGTH_SHORT).show();
                            }
                            glRoom.setAdapter(adapter);
                        }
                    }
                });

        btnVip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorizontalScrollView("Vip");
            }
        });

        btnThuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorizontalScrollView("Thường");
            }
        });

        btnDon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorizontalScrollView("Đơn");
            }
        });

        btnDoi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnHorizontalScrollView("Đôi");
            }
        });

        btnTrong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<room> room2 = new ArrayList<>();
                adapterRoom rooms2 = new adapterRoom(Home_Page.this, room2);
                String status = "Phòng trống";
                db.collection("rooms")
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                QuerySnapshot snapshots = task.getResult();
                                if(task.isSuccessful()){
                                    for (QueryDocumentSnapshot doc : snapshots){
                                        if (String.valueOf(doc.get("Status")).equals(status)){
                                            String name = String.valueOf(doc.get("NameRoom"));
                                            int price = Integer.parseInt(doc.get("Price").toString());
                                            String kind = String.valueOf(doc.get("KindRoom"));
                                            room2.add(new room(name, kind, status, price));
                                        }
                                    }
                                    glRoom.setAdapter(rooms2);
                                }
                            }
                        });
            }
        });
    }

    public void btnHorizontalScrollView(String kind){
        ArrayList<room> room2 = new ArrayList<>();
        adapterRoom rooms2 = new adapterRoom(Home_Page.this, room2);
        db.collection("rooms")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        QuerySnapshot snapshots = task.getResult();
                        if(task.isSuccessful()){
                            for (QueryDocumentSnapshot doc : snapshots){
                                if (String.valueOf(doc.get("KindRoom")).equals(kind)){
                                    String name = String.valueOf(doc.get("NameRoom"));
                                    String status = String.valueOf(doc.get("Status"));
                                    int price = Integer.parseInt(doc.get("Price").toString());
                                    room2.add(new room(name, kind, status, price));
                                }
                            }

                            glRoom.setAdapter(rooms2);
                        }
                    }
                });
    }

}