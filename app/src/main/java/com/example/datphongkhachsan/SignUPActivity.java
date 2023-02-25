package com.example.datphongkhachsan;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.HashMap;
import java.util.Map;


public class SignUPActivity extends AppCompatActivity {


    EditText  edtSignUpUsers,edtSignUpPassword,edtConfirmPassword;
    Button btnSignUp;
    TextView tvToLogin;
    ImageView hidePass,hidePassConfirm;
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_upactivity);
        edtSignUpUsers = findViewById(R.id.edtSignUpUsers);
        edtSignUpPassword = findViewById(R.id.edtSignUpPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        btnSignUp = findViewById(R.id.btnSignUp);
        tvToLogin = findViewById(R.id.tvToLogin);
        hidePass = findViewById(R.id.hidePass);
        hidePassConfirm = findViewById(R.id.hidePassConfirm);


        tvToLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SignUPActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });

        hidePass.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(edtSignUpPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    edtSignUpPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePass.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
                    edtSignUpPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePass.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });

        hidePassConfirm.setOnClickListener(new View.OnClickListener() {
//            Drawable drawable = hidePass.getDrawable();

            @Override
            public void onClick(View v) {
                if(edtConfirmPassword.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_off_24));
                    edtConfirmPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    hidePassConfirm.setImageResource(R.drawable.ic_baseline_visibility_off_24);
                }
                else {
//                    hidePass.setImageDrawable(getResources().getDrawable(R.drawable.ic_baseline_visibility_24));
                    edtConfirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    hidePassConfirm.setImageResource(R.drawable.ic_baseline_visibility_24);
                }
            }
        });


        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userAccount = edtSignUpUsers.getText().toString();
                String password = edtSignUpPassword.getText().toString();
                String confirm = edtConfirmPassword.getText().toString();
                if(password.equals(confirm) && (userAccount != null || password != null)){
                    Map<String, Object> user = new HashMap<>();
                    user.put("userName",userAccount);
                    user.put("passWord",password);

                    db.collection("Users")
                            .add(user)
                            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                @Override
                                public void onSuccess(DocumentReference documentReference) {
                                    Log.d(TAG, "DocumentSnapshot added with ID: " + documentReference.getId());
                                    Toast.makeText(SignUPActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(SignUPActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.w(TAG, "Error adding document", e);
                                    Toast.makeText(SignUPActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
                                }
                            });


                }
                else {
                    Toast.makeText(SignUPActivity.this, "Nhập sai xác thực mật khẩu xin nhập lại", Toast.LENGTH_SHORT).show();
                }
            }
        });




    }

//    public Boolean checkKegistration(String username, String password){
//        final int counts = 0;
//        db.collection("Users")
//                .get()
//                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
//                    @Override
//                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
//                        QuerySnapshot snapshot = task.getResult();
//                        int count = 0;
//                        if(task.isSuccessful()){
//                            for(QueryDocumentSnapshot doc : snapshot){
//                                if(String.valueOf((doc.get("userName"))).equals(username)){
//                                    count +=1;
//                                }
//                            }
//                        }
//                        counts = count;
//                    }
//                });
//        if(counts == 0){
//            return false;
//        }else {
//            return true;
//        }
//
//
//    }

}