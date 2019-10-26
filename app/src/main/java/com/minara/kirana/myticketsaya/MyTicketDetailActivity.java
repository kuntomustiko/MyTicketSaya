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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

public class MyTicketDetailActivity extends AppCompatActivity {

    Button btnBuyTicket;
    LinearLayout btnBack;
    DatabaseReference reference;

    TextView tv_myticketdetailact_namawisata, tv_myticketdetailact_lokasi,
            tv_myticketdetailact_date_wisata, tv_myticketdetailact_time_wisata,
            tv_myticketdetailact_ketentuan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_ticket_detail);

        btnBuyTicket = findViewById(R.id.btn_myTicketDetailAct_payNow);
        btnBack = findViewById(R.id.ll_myTicketDetailAct_back);

        tv_myticketdetailact_namawisata = findViewById(R.id.tv_myticketdetailact_namawisata);
        tv_myticketdetailact_lokasi = findViewById(R.id.tv_myticketdetailact_lokasi);
        tv_myticketdetailact_date_wisata = findViewById(R.id.tv_myticketdetailact_date_wisata);
        tv_myticketdetailact_time_wisata = findViewById(R.id.tv_myticketdetailact_time_wisata);
        tv_myticketdetailact_ketentuan = findViewById(R.id.tv_myticketdetailact_ketentuan);

        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String nama_wisata_baru = bundle.getString("nama_wisata");

        // mengambil data dari firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(nama_wisata_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    tv_myticketdetailact_namawisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                    tv_myticketdetailact_lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                    tv_myticketdetailact_date_wisata.setText(dataSnapshot.child("time_wisata").getValue().toString());
                    tv_myticketdetailact_time_wisata.setText(dataSnapshot.child("date_wisata").getValue().toString());
                    tv_myticketdetailact_ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(MyTicketDetailActivity.this, MyProfileActivity.class);
                startActivity(gotoProfile);
            }
        });

    }
}
