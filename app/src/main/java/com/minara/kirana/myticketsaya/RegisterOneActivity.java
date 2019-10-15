package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterOneActivity extends AppCompatActivity {

    LinearLayout llBack;
    Button btnContinueRegisterOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        llBack = findViewById(R.id.ll_registeroneact_back);
        btnContinueRegisterOne = findViewById(R.id.btn_registeroneact_continue);

        llBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSignIn = new Intent(RegisterOneActivity.this, SignInActivity.class);
                startActivity(backtoSignIn);
            }
        });

        btnContinueRegisterOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoRegisterTwo = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                startActivity(backtoRegisterTwo);
            }
        });
    }
}
