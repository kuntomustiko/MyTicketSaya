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

public class GetStartedActivity extends AppCompatActivity {

    Button btnSignIn, btnNewAccount;

    Animation ttb, btt;

    ImageView ivLogo;
    TextView tvTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        btnSignIn = (Button) findViewById(R.id.btn_getstartedact_signin);
        btnNewAccount = (Button) findViewById(R.id.btn_getstartedact_newaccount);
        ivLogo = findViewById(R.id.iv_getstartedact_logo);
        tvTitle = findViewById(R.id.tv_getstartedact_title);

        // load anim
        ttb = AnimationUtils.loadAnimation(this, R.anim.ttb);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        // jalankan animasi
        ivLogo.startAnimation(ttb);
        tvTitle.startAnimation(ttb);
        btnSignIn.startAnimation(btt);
        btnNewAccount.startAnimation(btt);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoSignIn = new Intent(GetStartedActivity.this, SignInActivity.class);
                startActivity(gotoSignIn);
            }
        });

        btnNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoRegisterOne = new Intent(GetStartedActivity.this, RegisterOneActivity.class);
                startActivity(gotoRegisterOne);
            }
        });


    }
}
