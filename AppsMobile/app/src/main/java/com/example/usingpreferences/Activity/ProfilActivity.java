package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

public class ProfilActivity extends AppCompatActivity {
    private ImageButton kembali;

    private TextView tvNama,tvEmail,tvNotelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        MaterialButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        SharedPreferences sharedPreferences = ProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        String email = sharedPreferences.getString("email", "");
        String notelpon = sharedPreferences.getString("no_telpon","");

        tvEmail = findViewById(R.id.tv_Emaillengkap);
        tvNama = findViewById(R.id.tv_namalengkap);
        tvNotelp = findViewById(R.id.tv_telponlengkap);

        tvEmail.setText(email);
        tvNama.setText(namaLengkap);
        tvNotelp.setText(notelpon);
        kembali = findViewById(R.id.kembaliprofil);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);


            }
        });
        MaterialButton EditProfil = findViewById(R.id.EditProfil);
        EditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        MaterialButton GantiPassword = findViewById(R.id.GantiPassword);
        GantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, GantiPasswordProfilActivity.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        MaterialButton PusatBantuan = findViewById(R.id.pusatbantuan);
        PusatBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, PusatBantuanProfil.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });



    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}