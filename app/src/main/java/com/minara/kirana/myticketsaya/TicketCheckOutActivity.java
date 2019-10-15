package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class TicketCheckOutActivity extends AppCompatActivity {

    ImageView ivNotiveUang;
    Button btnPlus, btnMinus, btnPayNow;
    TextView tvJumlahTicket, tvTotalHarga, tvMyBalance;
    Integer valueJumlahTicket = 1;
    Integer myBalance = 200;
    Integer valueTotalHarga = 0;
    Integer valueHargaTicket = 50;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_check_out);

        ivNotiveUang = findViewById(R.id.noticeUang);
        btnPlus = findViewById(R.id.btn_plus);
        btnMinus = findViewById(R.id.btn_minus);
        btnPayNow = findViewById(R.id.btn_ticketcheckoutact_paynow);
        tvJumlahTicket = findViewById(R.id.tv_ticketceckoutact_jumlahticket);
        tvMyBalance = findViewById(R.id.tv_ticketcheckoutact_mybalance);
        tvTotalHarga = findViewById(R.id.tv_ticketcheckoutact_totalharga);

        // default jumlah ticket awal
        tvJumlahTicket.setText(valueJumlahTicket.toString());
        // default btn minus hilang
        btnMinus.animate().alpha(0).setDuration(300).start();
        btnMinus.setEnabled(false);

        tvMyBalance.setText(myBalance.toString());
        tvTotalHarga.setText("US$ " + valueTotalHarga.toString()+" ");
        valueTotalHarga = valueJumlahTicket * valueHargaTicket;
        tvTotalHarga.setText("US$ " + valueTotalHarga.toString()+" ");

        ivNotiveUang.setVisibility(View.GONE);


        btnPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueJumlahTicket +=1;
                tvJumlahTicket.setText(valueJumlahTicket.toString());

                if (valueJumlahTicket > 1){
                    btnMinus.animate().alpha(1).setDuration(300).start();
                    btnMinus.setEnabled(true);
                }

                valueTotalHarga = valueJumlahTicket * valueHargaTicket;
                tvTotalHarga.setText("US$ " + valueTotalHarga.toString()+" ");

                if (valueTotalHarga > myBalance){
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
                valueJumlahTicket -=1;
                tvJumlahTicket.setText(valueJumlahTicket.toString());

                if (valueJumlahTicket < 2){
                    btnMinus.animate().alpha(0).setDuration(300).start();
                    btnMinus.setEnabled(false);
                }

                valueTotalHarga = valueJumlahTicket * valueHargaTicket;
                tvTotalHarga.setText("US$ " + valueTotalHarga.toString()+" ");

                if (valueTotalHarga < myBalance){
                    btnPayNow.animate().translationY(0).alpha(1).setDuration(350).start();
                    btnPayNow.setEnabled(true);
                    tvMyBalance.setTextColor(Color.parseColor("#203DD1"));
                    ivNotiveUang.setVisibility(View.GONE);
                }
            }
        });
    }
}
