package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {


    TextView btn_new_account;
    Button btn_signIn_signIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        btn_new_account = findViewById(R.id.btn_newAccount_signIn);
        btn_signIn_signIn = findViewById(R.id.btn_signIn_signIn);

        btn_new_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoRegisterOne = new Intent(SignInActivity.this, RegisterOneActivity.class);
                startActivity(gotoRegisterOne);
            }
        });

        btn_signIn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });
    }
}
