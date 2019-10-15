package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignInActivity extends AppCompatActivity {


    TextView tvNewAccount;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvNewAccount = findViewById(R.id.tv_signinact_newaccount);
        btnSignIn = findViewById(R.id.btn_signinact_signin);

        tvNewAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoRegisterOne = new Intent(SignInActivity.this, RegisterOneActivity.class);
                startActivity(gotoRegisterOne);
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome = new Intent(SignInActivity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });
    }
}
