package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.Random;

public class TicketCheckOutActivity extends AppCompatActivity {

    ImageView ivNotiveUang;
    Button btnPlus, btnMinus, btnPayNow;
    TextView tvJumlahTicket, tvTotalHarga, tvMyBalance, tv_ticketcheckoutact_nama_wisata,
            tv_ticketcheckoutact_lokasi, tv_ticketcheckoutact_ketentuan;
    Integer valueJumlahTicket = 1;
    Integer myBalance = 0;
    Integer valueTotalHarga = 0;
    Integer valueHargaTicket = 0;
    Integer valuesisabalance = 0;

    DatabaseReference reference, reference2, reference3, reference4;

    // ambil dari local storage
    String USERNAME_KEY = "usernamekey";
    String username_key = "";

    String username_key_new = "";

    String date_wisata = "";
    String time_wisata = "";

    // generate normor integer secara random untuk nomor transaksi
    Integer nomor_transaksi = new Random().nextInt();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_check_out);

        getUsernameLocal();

        // mengambil data dari intent
        Bundle bundle = getIntent().getExtras();
        final String jenis_ticket_baru = bundle.getString("jenis_ticket");

        ivNotiveUang = findViewById(R.id.noticeUang);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnPayNow = findViewById(R.id.btn_ticketcheckoutact_paynow);
        tvJumlahTicket = findViewById(R.id.tv_ticketceckoutact_jumlahticket);
        tvMyBalance = findViewById(R.id.tv_ticketcheckoutact_mybalance);
        tvTotalHarga = findViewById(R.id.tv_ticketcheckoutact_totalharga);

        tv_ticketcheckoutact_nama_wisata = findViewById(R.id.tv_ticketcheckoutact_nama_wisata);
        tv_ticketcheckoutact_lokasi = findViewById(R.id.tv_ticketcheckoutact_lokasi);
        tv_ticketcheckoutact_ketentuan = findViewById(R.id.tv_ticketcheckoutact_ketentuan);

        // default jumlah ticket awal
        tvJumlahTicket.setText(valueJumlahTicket.toString());
        // default btn minus hilang
        btnMinus.animate().alpha(0).setDuration(300).start();
        btnMinus.setEnabled(false);

        tvTotalHarga.setText("US$ " + valueTotalHarga.toString() + nomor_transaksi);

        ivNotiveUang.setVisibility(View.GONE);

        // mengambil data Users dari firebase untuk di ambil user_balance nya aja
        reference2 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
        reference2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                myBalance = Integer.valueOf(dataSnapshot.child("user_balance").getValue().toString());
                tvMyBalance.setText(myBalance.toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        // ambil data dari firebase berdasarkan intent
        reference = FirebaseDatabase.getInstance().getReference().child("Wisata").child(jenis_ticket_baru);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // menimpa data yang ada dengan data yang baru
                tv_ticketcheckoutact_nama_wisata.setText(dataSnapshot.child("nama_wisata").getValue().toString());
                tv_ticketcheckoutact_lokasi.setText(dataSnapshot.child("lokasi").getValue().toString());
                tv_ticketcheckoutact_ketentuan.setText(dataSnapshot.child("ketentuan").getValue().toString());

                // menyimpan ke dalam variabel
                date_wisata = dataSnapshot.child("date_wisata").getValue().toString();
                time_wisata = dataSnapshot.child("time_wisata").getValue().toString();

                valueHargaTicket = Integer.valueOf(dataSnapshot.child("harga_ticket").getValue().toString());

                // agar ketika pertama kali dijalankan valuetotalharga sudah sesuai dengan yang ada di firebase
                valueTotalHarga = valueJumlahTicket * valueHargaTicket;
                tvTotalHarga.setText("US$ " + valueTotalHarga.toString() + " ");


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueJumlahTicket += 1;
                tvJumlahTicket.setText(valueJumlahTicket.toString());

                if (valueJumlahTicket > 1) {
                    btnMinus.animate().alpha(1).setDuration(300).start();
                    btnMinus.setEnabled(true);
                }

                valueTotalHarga = valueJumlahTicket * valueHargaTicket;
                tvTotalHarga.setText("US$ " + valueTotalHarga.toString() + " ");

                if (valueTotalHarga > myBalance) {
                    btnPayNow.animate().translationY(250).alpha(0).setDuration(350).start();
                    btnPayNow.setEnabled(false);
                    tvMyBalance.setTextColor(Color.parseColor("#D1206B"));
                    ivNotiveUang.setVisibility(View.VISIBLE);
                }
            }
        });
        btnMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueJumlahTicket -= 1;
                tvJumlahTicket.setText(valueJumlahTicket.toString());

                if (valueJumlahTicket < 2) {
                    btnMinus.animate().alpha(0).setDuration(300).start();
                    btnMinus.setEnabled(false);
                }

                valueTotalHarga = valueJumlahTicket * valueHargaTicket;
                tvTotalHarga.setText("US$ " + valueTotalHarga.toString() + " ");

                if (valueTotalHarga < myBalance) {
                    btnPayNow.animate().translationY(0).alpha(1).setDuration(350).start();
                    btnPayNow.setEnabled(true);
                    tvMyBalance.setTextColor(Color.parseColor("#203DD1"));
                    ivNotiveUang.setVisibility(View.GONE);
                }
            }
        });


        btnPayNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // menyimpan data user yang mengklik paynow ke firebase lalu membuat tabel baru "MyTickets"
                reference3 = FirebaseDatabase.getInstance().getReference()
                        .child("MyTickets")
                        .child(username_key_new)
                        .child(tv_ticketcheckoutact_nama_wisata.getText().toString() + nomor_transaksi);
                reference3.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        reference3.getRef().child("id_ticket").setValue(tv_ticketcheckoutact_nama_wisata.getText().toString() + nomor_transaksi);
                        reference3.getRef().child("nama_wisata").setValue(tv_ticketcheckoutact_nama_wisata.getText().toString());
                        reference3.getRef().child("lokasi").setValue(tv_ticketcheckoutact_lokasi.getText().toString());
                        reference3.getRef().child("ketentuan").setValue(tv_ticketcheckoutact_ketentuan.getText().toString());
                        reference3.getRef().child("jumlah_ticket").setValue(tv_ticketcheckoutact_ketentuan.toString());

                        reference3.getRef().child("date_wisata").setValue(date_wisata);
                        reference3.getRef().child("time_Wisata").setValue(time_wisata);

                        Intent gotosuccessticket = new Intent(TicketCheckOutActivity.this, SuccessBuyTicketActivity.class);
                        startActivity(gotosuccessticket);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                // untuk membuat menyimpan sisa balance setelah membli ticket
                reference4 = FirebaseDatabase.getInstance().getReference().child("Users").child(username_key_new);
                reference4.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        valuesisabalance = myBalance - valueTotalHarga;
                        reference4.getRef().child("user_balance").setValue(valuesisabalance);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
    }

    // ambil data dari local sharedpreference yang sebelumnya telah di simpan dari registerone
    public void getUsernameLocal() {
        SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
        username_key_new = sharedPreferences.getString(username_key, "");
    }
}
