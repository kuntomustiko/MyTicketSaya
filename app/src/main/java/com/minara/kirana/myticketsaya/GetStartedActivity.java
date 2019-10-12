package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class GetStartedActivity extends AppCompatActivity {

    Button btnSignIn, btn_newAccount_create_getStarted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started);

        btnSignIn = (Button) findViewById(R.id.btn_signIn);
        btn_newAccount_create_getStarted = (Button) findViewById(R.id.btn_newAccount_create_getStarted);

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoSignIn = new Intent(GetStartedActivity.this, SignInActivity.class);
                startActivity(gotoSignIn);
            }
        });

        btn_newAccount_create_getStarted.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoRegisterOne = new Intent(GetStartedActivity.this, RegisterOneActivity.class);
                startActivity(gotoRegisterOne);
            }
        });
    }
}
