package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterOneActivity extends AppCompatActivity {

    LinearLayout btn_back;
    Button btn_continue_registerOne;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        btn_back = findViewById(R.id.btn_back_registerOne);
        btn_continue_registerOne = findViewById(R.id.btn_continue_registerOne);

        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSignIn = new Intent(RegisterOneActivity.this, SignInActivity.class);
                startActivity(backtoSignIn);
            }
        });

        btn_continue_registerOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoRegisterTwo = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                startActivity(backtoRegisterTwo);
            }
        });
    }
}
