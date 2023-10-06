package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

public class KodeOtp extends AppCompatActivity {
private Button konfir;
private TextInputEditText inputotp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);
        konfir = findViewById(R.id.button_konfirmasiotpp);
        inputotp = findViewById(R.id.otp);

        konfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(KodeOtp.this, AturUlangKataSandi.class);
                startActivity(pindah);
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        }

public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}