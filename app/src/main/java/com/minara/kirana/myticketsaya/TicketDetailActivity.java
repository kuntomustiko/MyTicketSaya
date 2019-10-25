package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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

public class TicketDetailActivity extends AppCompatActivity {

    // todo 13-b
    Button btnBuyTicket;
    TextView tv_location, tv_title, tv_photospot_ticket,tv_wifi_ticket, tv_festival_ticket, tv_shortDetail_ticket;
    ImageView iv_ticketdetailact_bgheader;

    DatabaseReference reference;

    LinearLayout ll_ticketdetailact_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        btnBuyTicket = findViewById(R.id.btn_ticketdetailact_buyticket);

        tv_location = findViewById(R.id.tv_ticketdetailact_location);
        tv_title = findViewById(R.id.tv_myticketdetailact_title);
        tv_photospot_ticket = findViewById(R.id.tv_photospot_ticket);
        tv_wifi_ticket = findViewById(R.id.tv_wifi_ticket);
        tv_festival_ticket = findViewById(R.id.tv_festival_ticket);
        tv_shortDetail_ticket = findViewById(R.id.tv_shortDetail_ticket);
        iv_ticketdetailact_bgheader = findViewById(R.id.iv_ticketdetailact_bgheader);

        ll_ticketdetailact_back = findViewById(R.id.ll_ticketdetailact_back);

        // mengambil data dari ticket
        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("jenis_ticket");

        // ambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // menimpa data yang ada dengan data yang baru
                tv_title.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                tv_location.setText(dataSnapshot.child("lokasi").getValue().toString());
                tv_photospot_ticket.setText(dataSnapshot.child("is_photo_spot").getValue().toString());
                tv_wifi_ticket.setText(dataSnapshot.child("is_wifi").getValue().toString());
                tv_festival_ticket.setText(dataSnapshot.child("is_festival").getValue().toString());
                tv_shortDetail_ticket.setText(dataSnapshot.child("short_desc").getValue().toString());

                Picasso.with(TicketDetailActivity.this)
                        .load(dataSnapshot.child("url_thumbnail")
                                .getValue().toString()).centerCrop().fit().into(iv_ticketdetailact_bgheader);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoTicketCheckOut = new Intent(TicketDetailActivity.this, TicketCheckOutActivity.class);
                gotoTicketCheckOut.putExtra("jenis_ticket",jenis_ticket_baru);

                startActivity(gotoTicketCheckOut);
            }
        });

        ll_ticketdetailact_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoback = new Intent(TicketDetailActivity.this, HomeActivity.class);
                startActivity(gotoback);
            }
        });
    }
}
