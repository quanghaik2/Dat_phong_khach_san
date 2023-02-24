package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home_Page extends AppCompatActivity {

    GridView glRoom;
    ArrayList<room> rooms;
    FirebaseFirestore db ;
    ImageView imgToLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        glRoom = findViewById(R.id.glRoom);
        imgToLogin = findViewById(R.id.imgToLogin);
        rooms = new ArrayList<>();
        db = FirebaseFirestore.getInstance();

        imgToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home_Page.this, Form_Info_User.class);
                startActivity(intent);
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
                            adapterRoom adapter = new adapterRoom(Home_Page.this, rooms);
                            glRoom.setAdapter(adapter);
                        }
                    }
                });


    }
}