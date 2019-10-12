package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class RegisterTwoActivity extends AppCompatActivity {
    LinearLayout btn_back;

    Button btn_continue_registerTwo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_two);
        btn_continue_registerTwo = findViewById(R.id.btn_continue_registerTwo);

        btn_back = findViewById(R.id.btn_back_registerOne);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSignIn = new Intent(RegisterTwoActivity.this, RegisterOneActivity.class);
                startActivity(backtoSignIn);
            }
        });

        btn_continue_registerTwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent backtoSuccess = new Intent(RegisterTwoActivity.this, SuccessRegisterActivity.class);
                startActivity(backtoSuccess);
            }
        });
    }
}
