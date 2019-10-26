package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SuccessBuyTicketActivity extends AppCompatActivity {
    Animation app_splash, btt, ttb;

    TextView tvTitle, tvSubTitle;
    ImageView ivIconSuccesTicket;
    Button btnMyDashboard, btnViewTicket;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_buy_ticket);

        tvTitle  = findViewById(R.id.tv_succesbuyticketact_title);
        tvSubTitle  = findViewById(R.id.tv_succesbuyticketact_subtitle);
        ivIconSuccesTicket  = findViewById(R.id.iv_succesbuyticketact_icon);
        btnMyDashboard  = findViewById(R.id.btn_succesbuyticketact_mydashboard);
        btnViewTicket  = findViewById(R.id.btn_succesbuyticketact_viewticket);

        // load anim
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        // run animate
        ivIconSuccesTicket.startAnimation(app_splash);

        tvTitle.startAnimation(ttb);
        tvSubTitle.startAnimation(ttb);

        btnViewTicket.startAnimation(btt);
        btnMyDashboard.startAnimation(btt);

        btnMyDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotohome = new Intent(SuccessBuyTicketActivity.this, HomeActivity.class);
                startActivity(gotohome);

            }
        });

        btnViewTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoprofile = new Intent(SuccessBuyTicketActivity.this, MyProfileActivity.class);
                startActivity(gotoprofile);

            }
        });
    }
}
