package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.github.florent37.shapeofview.shapes.CircleView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class HomeActivity extends AppCompatActivity {

    LinearLayout llPisaMenu,llCandiMenu,
    llMonasMenu,llSphinxMenu,
    llTorriMenu,llPagodaMenu ;

    CircleView ivProfile;

    // TODO 13 - a

    ImageView ivPhoto;
    TextView tvUserBalance, tvNamaLengkap, tvBio;

    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    // key yang sedang login
    String username_key_new = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        getUsernameLocal();

        ivPhoto = findViewById(R.id.pict_homeact_profile);
        tvUserBalance = findViewById(R.id.tv_homeact_title_userbalance);
        tvNamaLengkap = findViewById(R.id.tv_homeact_title_namalengkap);
        tvBio = findViewById(R.id.tv_homeact_title_bio);

        llPisaMenu = findViewById(R.id.ll_homeact_pisa);
        llTorriMenu = findViewById(R.id.ll_homeact_torri);
        llPagodaMenu = findViewById(R.id.ll_homeact_pagoda);
        llCandiMenu = findViewById(R.id.ll_homeact_candi);
        llSphinxMenu = findViewById(R.id.ll_homeact_sphinx);
        llMonasMenu = findViewById(R.id.ll_homeact_monas);

        ivProfile = findViewById(R.id.iv_homeact_profile);

        reference = FirebaseDatabase.getInstance().getReference().child("Users")
                .child(username_key_new);

        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                tvNamaLengkap.setText(dataSnapshot.child("nama_lengkap").getValue().toString());
                tvBio.setText(dataSnapshot.child("bio").getValue().toString());
                tvUserBalance.setText("US $" + dataSnapshot.child("user_balance").getValue().toString());

                // belum bisa
                Picasso.with(HomeActivity.this)
                        .load(dataSnapshot.child("url_photo_profile")
                                .getValue().toString()).centerCrop().fit().into(ivPhoto);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        llPisaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);
                // meletakkan data kepada intent
                gotoDetailTicket.putExtra("jenis_ticket","Pisa");
                startActivity(gotoDetailTicket);
            }
        });

        llTorriMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);

                gotoDetailTicket.putExtra("jenis_ticket","Torri");

                startActivity(gotoDetailTicket);
            }
        });
        llPagodaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);

                gotoDetailTicket.putExtra("jenis_ticket","Pagoda");

                startActivity(gotoDetailTicket);
            }
        });
        llCandiMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);

                gotoDetailTicket.putExtra("jenis_ticket","Candi");

                startActivity(gotoDetailTicket);
            }
        });
        llSphinxMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);

                gotoDetailTicket.putExtra("jenis_ticket","Sphinx");

                startActivity(gotoDetailTicket);
            }
        });
        llMonasMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicket = new Intent(HomeActivity.this, TicketDetailActivity.class);

                gotoDetailTicket.putExtra("jenis_ticket","Monas");

                startActivity(gotoDetailTicket);
            }
        });

        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(gotoProfile);
            }
        });
    }

    // ambil data dari local sharedpreference yang sebelumnya telah di simpan dari registerone
    public void getUsernameLocal(){
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key,"");
    }
}
