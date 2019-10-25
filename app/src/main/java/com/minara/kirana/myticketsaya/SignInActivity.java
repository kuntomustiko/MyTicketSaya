package com.minara.kirana.myticketsaya;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignInActivity extends AppCompatActivity {


    TextView tvNewAccount;
    Button btnSignIn;

    // todo 12 - c
    EditText edtUsername, edtPassword;
    DatabaseReference reference;

    String USERNAME_KEY = "usernamekey";
    String username_key = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        tvNewAccount = findViewById(R.id.tv_signinact_newaccount);
        btnSignIn = findViewById(R.id.btn_signinact_signin);

        edtUsername = findViewById(R.id.edt_signinact_username);
        edtPassword = findViewById(R.id.edt_signinact_password);

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

                String username = edtUsername.getText().toString();
                final String password = edtPassword.getText().toString();

                btnSignIn.setEnabled(false);
                btnSignIn.setText("loading");
                // todo 12 - c 2
                reference = FirebaseDatabase.getInstance().getReference().child("Users")
                        .child(username);
                
                reference.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()){

                            // ambil data password dari firebase
                            String passwordFromFirebase = dataSnapshot.child("password").getValue().toString();

                            // validasi password dengan password firebase
                            if (password.equals(passwordFromFirebase)){

                                // simpan username (key) di local
                                SharedPreferences sharedPreferences = getSharedPreferences(USERNAME_KEY, MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                editor.putString(username_key, edtUsername.getText().toString());
                                editor.apply();

                                Intent gotoHome = new Intent(SignInActivity.this, HomeActivity.class);
                                startActivity(gotoHome);
                            } else{
                                Toast.makeText(SignInActivity.this, "password salah", Toast.LENGTH_SHORT).show();
                            }

                        } else {
                            Toast.makeText(SignInActivity.this, "username tidak ada", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });



            }
        });
    }


}
