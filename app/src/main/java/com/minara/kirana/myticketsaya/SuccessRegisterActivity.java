package com.minara.kirana.myticketsaya;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SuccessRegisterActivity extends AppCompatActivity {

    Button btn_exploreNow_successRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_register);

        btn_exploreNow_successRegister = findViewById(R.id.btn_exploreNow_successRegister);
        btn_exploreNow_successRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent gotoHome = new Intent(SuccessRegisterActivity.this, HomeActivity.class);
                startActivity(gotoHome);
            }
        });
    }
}
