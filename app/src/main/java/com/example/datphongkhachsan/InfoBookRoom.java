package com.example.datphongkhachsan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class InfoBookRoom extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    TextView tvNameUser;
    userInfo user = new userInfo("","","","");
//    BookRoomHotel bookRoom;
    ListView lvBookRoomHotel;
    List<BookRoomHotel> bookRoomHotels = new ArrayList<BookRoomHotel>();
    List<room> rooms = new ArrayList<room>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_book_room);
        tvNameUser = findViewById(R.id.tvNameUser);
        lvBookRoomHotel = findViewById(R.id.lvBookRoomHotel);
        Bundle bundle = getIntent().getExtras();
        String idUser = bundle.getString("idUser","");
        adapterBookRoomHotel adapter = new adapterBookRoomHotel(InfoBookRoom.this,bookRoomHotels);

        db.collection("BookRoom")
                .whereEqualTo("User", idUser)
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot documentSnapshots) {
                        if(documentSnapshots.isEmpty()){
                            Toast.makeText(InfoBookRoom.this, "Bạn chưa đặt phòng nào", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            for (QueryDocumentSnapshot doc : documentSnapshots){
                                bookRoomHotels.add(new BookRoomHotel(doc.get("Room").toString(),doc.get("User").toString(),doc.get("Time").toString()));

                            }
                            lvBookRoomHotel.setAdapter(adapter);

                        }
                    }
                });
//




            db.collection("Users").document(idUser).collection("usersInfo")
                        .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if(task.isSuccessful()){
                                QuerySnapshot snapshots = task.getResult();
                                for (QueryDocumentSnapshot doc : snapshots){
                                    user.setEmail(doc.get("email").toString());
                                    user.setFullName(doc.get("fullname").toString());
                                    user.setAddress(doc.get("address").toString());
                                    user.setPhone(doc.get("phone").toString());
                                    tvNameUser.setText(user.getFullName());
                                }
                            }
                        }
                    });

//        Toast.makeText(this, bookRoomHotels.get(0).getTime(), Toast.LENGTH_SHORT).show();



    }

//    public room getRoom(String idRoom){
//                List<room> rm = new ArrayList<room>();
//                db.collection("rooms").document(idRoom)
//                .get()
//                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                    @Override
//                    public void onSuccess(DocumentSnapshot documentSnapshot) {
//                        String name = String.valueOf(documentSnapshot.get("NameRoom"));
//                        String type = String.valueOf(documentSnapshot.get("KindRoom"));
//                        String status = String.valueOf(documentSnapshot.get("Status"));
//                        int price = Integer.parseInt(documentSnapshot.get("Price").toString());
//                        String idRoom1 = documentSnapshot.getId();
////                            rooms.add(new room(name,type,status,price,idRoom1));
//                            room r1 = new room(name,type,status,price,idRoom1);
//                            rm.add(r1);
//                    }
//                });
//                return rm.get(0);
//    }

}
