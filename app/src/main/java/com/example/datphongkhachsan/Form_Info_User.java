package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Form_Info_User extends AppCompatActivity {
    EditText edtFullName, edtEmail, edtPhone,edtAddress;
    Button btnComplete;
//    List<userInfo> users = new ArrayList<userInfo>();
    FirebaseFirestore db;
    ImageView imgBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_info_user);
        edtFullName = findViewById(R.id.edtFullName);
        edtAddress = findViewById(R.id.edtAddress);
        edtEmail = findViewById(R.id.edtEmail);
        edtPhone = findViewById(R.id.edtPhone);
        imgBack = findViewById(R.id.imgBack);
        String fullName = edtFullName.getText().toString();
        String address = edtAddress.getText().toString();
        String phone = edtPhone.getText().toString();
        String email = edtEmail.getText().toString();
        db = FirebaseFirestore.getInstance();
//        final CollectionReference reference = db.collection("Users");
        btnComplete = findViewById(R.id.btnComplete);
        Bundle bundle = getIntent().getExtras();
        String idUser = bundle.getString("id");
        String idUserInfo = bundle.getString("idUserInfo");
        String bdName = bundle.getString("name");
        String bdEmail = bundle.getString("email");
        String bdPhone = bundle.getString("phone");
        String bdAddress = bundle.getString("address");
        edtFullName.setText(bdName);
        edtAddress.setText(bdAddress);
        edtPhone.setText(bdPhone);
        edtEmail.setText(bdEmail);


        btnComplete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                    if (fullName == "" || address == "" || phone == "" || email == ""){
                            Map<String, Object> items = new HashMap<>();
                            items.put("fullname", edtFullName.getText().toString());
                            items.put("address", edtAddress.getText().toString());
                            items.put("phone", edtPhone.getText().toString());
                            items.put("email", edtEmail.getText().toString());
                            db.collection("Users").document(idUser).collection("usersInfo")
                                    .document(idUserInfo)
                                    .update(items)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void unused) {
                                            Toast.makeText(Form_Info_User.this, "Sửa thành công", Toast.LENGTH_SHORT).show();
                                        }
                                    })
                                    .addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(Form_Info_User.this, "Sửa thất bại", Toast.LENGTH_SHORT).show();
                                        }
                                    });



//                }
//                    else {
//                        Toast.makeText(Form_Info_User.this, "Hãy điền đủ thông tin", Toast.LENGTH_SHORT).show();
//                    }


            }
        });

        imgBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(Form_Info_User.this, UserActivity.class);
//                startActivity(intent);
//                setContentView(R.layout.activity_user);
            }
        });
    }
}