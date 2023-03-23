package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class UserActivity extends AppCompatActivity {
    TextView tvName,tvPhone,tvAddress,tvEmail;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    Button editInfo,DelInfo;
    ImageButton btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        tvName = findViewById(R.id.tvName);
        tvAddress = findViewById(R.id.tvAddress);
        tvEmail = findViewById(R.id.tvEmail);
        tvPhone = findViewById(R.id.tvPhone);
        editInfo = findViewById(R.id.editInfo);
        DelInfo = findViewById(R.id.DelInfo);
        btnLogout =  findViewById(R.id.btnLogout);
        Bundle Bundle = getIntent().getExtras();
        String id = Bundle.getString("idUser");
        userInfo Users = new userInfo("","","","");
        final String[] idUserInfo = {""};
        final CollectionReference reference = db.collection("Users").document(id).collection("usersInfo");
        reference.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        reference.get()
                                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                                    @Override
                                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                        QuerySnapshot snapshot = task.getResult();
                                        userInfo user =new userInfo("","","","");
                                        if (task.isSuccessful()){
                                            String content = "";
                                            for (QueryDocumentSnapshot document: snapshot){
                                                user = new userInfo(String.valueOf(document.get("fullname")), String.valueOf(document.get("phone"))
                                                , String.valueOf(document.get("address")), String.valueOf(document.get("email")));
                                                content = document.getId();
                                            }
                                            idUserInfo[0] = content;
                                        }
                                        tvName.setText(user.getFullName());
                                        tvAddress.setText(user.getAddress());
                                        tvPhone.setText(user.getPhone());
                                        tvEmail.setText(user.getEmail());
                                        Users.setFullName(user.getFullName());
                                        Users.setAddress(user.getAddress());
                                        Users.setPhone(user.getPhone());
                                        Users.setEmail(user.getEmail());
                                    }
                                });
                    }
                });
        editInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Form_Info_User.class);
                Bundle bundle = new Bundle();
                bundle.putString("id",id);
                bundle.putString("idUserInfo",idUserInfo[0]);
                bundle.putString("name",Users.getFullName());
                bundle.putString("email",Users.getEmail());
                bundle.putString("phone",Users.getPhone());
                bundle.putString("address",Users.getAddress());
                intent.putExtras(bundle);
                startActivity(intent);

            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        DelInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference.document(idUserInfo[0]).delete()
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(UserActivity.this, "Xóa thành Công", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(UserActivity.this, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                            }
                        });
            }
        });
    }
}