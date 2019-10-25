package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class MyProfileActivity extends AppCompatActivity {
    LinearLayout llMenaraPisa;
    Button btnEditProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        llMenaraPisa = findViewById(R.id.ll_myprofileact_menarapisa);
        llMenaraPisa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoMyTicketDetail = new Intent(MyProfileActivity.this, MyTicketDetailActivity.class);
                startActivity(gotoMyTicketDetail);
            }
        });

        btnEditProfile = findViewById(R.id.btn_myprofileact_editprofile);
        btnEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoEditProfile = new Intent(MyProfileActivity.this, EditProfileActivity.class);
                startActivity(gotoEditProfile);
            }
        });
    }
}
