package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterTwoActivity extends AppCompatActivity {

    LinearLayout llback;
    Button btnContinueRegisterTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);

        btnContinueRegisterTwo = findViewById(R.id.btn_registertwoact_continue);
        llback = findViewById(R.id.ll_registeroneact_back);

        llback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSignIn = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(backtoSignIn);
            }
        });

        btnContinueRegisterTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSuccess = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                startActivity(backtoSuccess);
            }
        });
    }
}
