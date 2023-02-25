package com.example.datphongkhachsan;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class LoginActivity extends AppCompatActivity {
    TextView tvToSignUp;
    ImageView hidePassLogin;
    EditText edtLoginUser, edtLoginPassword;
    Button btnLogin;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        tvToSignUp = findViewById(R.id.tvToSignUp);
        edtLoginPassword = findViewById(R.id.edtLoginPassword);
        edtLoginUser = findViewById(R.id.edtLoginUser);
        hidePassLogin = findViewById(R.id.hidePassLogin);
        btnLogin = findViewById(R.id.btnLogin);

        tvToSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent integer = new Intent(LoginActivity.this, SignUPActivity.class);
                startActivity(integer);
            }
        });

        hidePassLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(edtLoginPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    edtLoginPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
                    edtLoginPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassLogin.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = edtLoginUser.getText().toString();
                String password = edtLoginPassword.getText().toString();
//                Adapter<Object> adapter = new Adapter<Object>;
//                Map<String, Object> Users = new HashMap<String, Object>();
                db.collection("Users").
                        whereEqualTo("userName",username)
                        .whereEqualTo("passWord",password)
                        .get()
                        .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                            @Override
                            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                                if (task.isSuccessful()) {
                                   if(task.getResult() != null) {
                                       String id = "";
                                       for (QueryDocumentSnapshot document : task.getResult()) {
//                                           Log.d(TAG, document.getId() + " => " + document.getData());
                                                id = document.getId();
                                       }

                                       Toast.makeText(LoginActivity.this, "Đăng nhập thành công", Toast.LENGTH_SHORT).show();

                                       Intent intent = new Intent(LoginActivity.this, Home_Page.class);
                                       Bundle mBundle = new Bundle();
                                       mBundle.putString("id", id);
                                       intent.putExtras(mBundle);
                                       startActivity(intent);

                                   }
                                } else {
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }
        });


    }
}