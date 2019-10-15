package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.github.florent37.shapeofview.shapes.CircleView;

public class HomeActivity extends AppCompatActivity {

    LinearLayout llPisaMenu;
    CircleView ivProfile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        llPisaMenu = findViewById(R.id.ll_homeact_pisa);
        llPisaMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoDetailTicketPisa = new Intent(HomeActivity.this, TicketDetailActivity.class);
                startActivity(gotoDetailTicketPisa);
            }
        });

        ivProfile = findViewById(R.id.iv_homeact_profile);
        ivProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoProfile = new Intent(HomeActivity.this, MyProfileActivity.class);
                startActivity(gotoProfile);
            }
        });
    }
}
