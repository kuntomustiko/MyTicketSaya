package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterOneActivity extends AppCompatActivity {

    LinearLayout llBack;
    Button btnContinueRegisterOne;

    // TODO 12 - a
    EditText edtUsername, edtPassword, edtEmailAddress;
    DatabaseReference reference;
    String USERNAME_KEY = "usernamekey";
    String username_key = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_one);

        edtUsername = findViewById(R.id.edt_registeroneact_username);
        edtPassword = findViewById(R.id.edt_registeroneact_password);
        edtEmailAddress = findViewById(R.id.edt_registeroneact_emailaddress);

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

                // 12 - a1
                // menyimpan data kepada local storage (handphone)
                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(username_key, edtUsername.getText().toString());
                editor.apply();

                // simpan kepada database
                // akan menuju ke users dan berdasarkan nama dari edittext
                reference = FirebaseDatabase.getInstance().getReference()
                        .child("Users").child(edtUsername.getText().toString());

                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        // memasukkan data ke database
                        dataSnapshot.getRef().child("username").setValue(edtUsername.getText().toString());
                        dataSnapshot.getRef().child("password").setValue(edtPassword.getText().toString());
                        dataSnapshot.getRef().child("email_address").setValue(edtEmailAddress.getText().toString());
                        dataSnapshot.getRef().child("user_balance").setValue(900);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                Intent gotoRegisterTwo = new Intent(RegisterOneActivity.this, RegisterTwoActivity.class);
                startActivity(gotoRegisterTwo);
            }
        });
    }
}
