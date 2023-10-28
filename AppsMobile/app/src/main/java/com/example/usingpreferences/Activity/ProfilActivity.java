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
import com.google.android.material.card.MaterialCardView;

public class ProfilActivity extends AppCompatActivity {
    private ImageButton kembali;

    private TextView tvNama,tvEmail,tvNotelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        MaterialButton TentangKami = findViewById(R.id.TentangKami);
        TentangKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,TentangKamiActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
         MaterialCardView ProfilLengkap = findViewById(R.id.ProfilLengkap);
        ProfilLengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,DetailProfilActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        MaterialButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,LoginActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        tvEmail = findViewById(R.id.tv_Emaillengkap);
        tvNama = findViewById(R.id.tv_namalengkap);
        tvNotelp = findViewById(R.id.tv_telponlengkap);
//        ShowData();
        kembali = findViewById(R.id.kembaliprofil);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);


            }
        });
        MaterialCardView EditProfil = findViewById(R.id.EditProfil);
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
    @Override
    protected void onResume() {
        super.onResume();

        ShowData();
    }

    private void ShowData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        String email = sharedPreferences.getString("email", "");
        String notelpon = sharedPreferences.getString("no_telpon", "");

        tvEmail.setText(email);
        tvNama.setText(namaLengkap);
        tvNotelp.setText(notelpon);
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}