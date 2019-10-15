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

public class SuccessRegisterActivity extends AppCompatActivity {

    Button btnExplore;
    TextView appTitle, appSubTitle;
    ImageView iv_succes_register;
    Animation app_splash, btt, ttb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        btnExplore = findViewById(R.id.btn_successregisteract_explorenow);
        appTitle = findViewById(R.id.tv_successregisteract_title);
        appSubTitle = findViewById(R.id.tv_successregisteract_subtitle);
        iv_succes_register = findViewById(R.id.iv_successregisteract_image);

        // load anim
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);

        // jalankan animasi
        btnExplore.startAnimation(btt);
        appTitle.startAnimation(ttb);
        appSubTitle.startAnimation(ttb);
        iv_succes_register.startAnimation(app_splash);


        btnExplore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome = new Intent(SuccessRegisterActivity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });
    }
}
