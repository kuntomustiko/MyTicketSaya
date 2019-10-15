package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class SplashScreen extends AppCompatActivity {

    Animation app_splash, btt;
    ImageView tvSplashLogo;
    TextView tvSplashTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        tvSplashLogo = findViewById(R.id.iv_splashscreen_logo);
        tvSplashTitle = findViewById(R.id.tv_splashscreen_title);

        // load anim
        app_splash = AnimationUtils.loadAnimation(this, R.anim.app_splash);
        btt = AnimationUtils.loadAnimation(this, R.anim.btt);

        // jalankan animasi
        tvSplashLogo.startAnimation(app_splash);
        tvSplashTitle.startAnimation(btt);

        //setting time untuk 2 detik
        Thread thread = new Thread(){
            public void run(){
                try{
                    sleep(2000);
                } catch (InterruptedException e){
                    e.printStackTrace();
                } finally {
                    startActivity(new Intent(SplashScreen.this, GetStartedActivity.class));
                    finish();
                }
            }
        };

        thread.start();
    }

}
