package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.KonfirmMenu.BerhasilGantiSandi;
import com.example.usingpreferences.R;

public class AturUlangKataSandi extends AppCompatActivity {
        private Button aturulang;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_ulang_kata_sandi);

    aturulang = findViewById(R.id.aturulangkatasandi);
    aturulang.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent pindah = new Intent(AturUlangKataSandi.this, BerhasilGantiSandi.class);
            startActivity(pindah);
            finish();
        }
    });

    }
    public void onBackPressed(){

    }
}