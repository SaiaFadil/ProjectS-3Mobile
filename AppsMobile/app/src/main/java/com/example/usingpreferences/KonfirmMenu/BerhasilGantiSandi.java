package com.example.usingpreferences.KonfirmMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.Activity.LoginActivity;
import com.example.usingpreferences.R;

public class BerhasilGantiSandi extends AppCompatActivity {
private Button kembalilogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_ganti_sandi);


        kembalilogin = findViewById(R.id.button_lanjut);
        kembalilogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(BerhasilGantiSandi.this, LoginActivity.class);
                startActivity(pindah);
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
    }
    public void onBackPressed(){

    }
}