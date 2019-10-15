package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class TicketDetailActivity extends AppCompatActivity {

    Button btnBuyTicket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_detail);

        btnBuyTicket = findViewById(R.id.btn_ticketdetailact_buyticket);
        btnBuyTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoTicketCheckOut = new Intent(TicketDetailActivity.this, TicketCheckOutActivity.class);
                startActivity(gotoTicketCheckOut);
            }
        });
    }
}
