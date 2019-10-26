package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MyProfileActivity extends AppCompatActivity {
    LinearLayout llMenaraPisa;
    Button btnEditProfile, btn_myprofileact_backhome, btn_myprofileact_signout;

    TextView tvNamaLengkap, tvBio;
    ImageView ivPhotoProfile;

    DatabaseReference reference, reference2;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";
    String username_key_new = "";

    RecyclerView rv_myprofileact_myticket_place;
    ArrayList<MyTicket> myTicketArrayList;
    TicketAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        getUsernameLocal();

        rv_myprofileact_myticket_place = findViewById(R.id.rv_myprofileact_myticket_place);
        btn_myprofileact_backhome = findViewById(R.id.btn_myprofileact_backhome);
        btn_myprofileact_signout = findViewById(R.id.btn_myprofileact_signout);

        rv_myprofileact_myticket_place.setLayoutManager(new LinearLayoutManager(this));
        myTicketArrayList = new ArrayList<MyTicket>();

        btnEditProfile = findViewById(R.id.btn_myprofileact_editprofile);
        tvNamaLengkap = findViewById(R.id.tv_myprofileact_namalengkap);
        tvBio = findViewById(R.id.tv_myprofileact_bio);
        ivPhotoProfile = findViewById(R.id.iv_myprofileact_profile);


        reference = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvNamaLengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                tvBio.setText(dataSnapshot.child("bio").getValue().toString());

                Picasso.with(MyProfileActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(ivPhotoProfile);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoEditProfile = new Intent(MyProfileActivity.this, EditProfileActivity.class);
                startActivity(gotoEditProfile);
            }
        });
        btn_myprofileact_signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // menghapus isi / nilai / value dari username local
                // menyimpan data kepada local storage (handphone)
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key,null);
                editor.apply();

                Intent gotosignin = new Intent(MyProfileActivity.this, SignInActivity.class);
                startActivity(gotosignin);
                finish();
            }
        });
        btn_myprofileact_backhome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotohome = new Intent(MyProfileActivity.this, HomeActivity.class);
                startActivity(gotohome);
            }
        });

        // mengambil data dari firebase dengan MyTickets
        reference2 = FirebaseDatabase.getInstance().getReference().child("MyTickets").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    MyTicket p = dataSnapshot1.getValue(MyTicket.class);
                    myTicketArrayList.add(p);
                }

                adapter = new TicketAdapter(MyProfileActivity.this, myTicketArrayList);
                rv_myprofileact_myticket_place.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    // ambil data dari local sharedpreference yang sebelumnya telah di simpan dari registerone
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
