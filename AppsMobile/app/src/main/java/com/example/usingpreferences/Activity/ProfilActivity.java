package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;

public class ProfilActivity extends AppCompatActivity {
    private ImageButton kembali;
    private TextView tvNama,tvEmail,tvNotelp,tvTTL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);

        SharedPreferences sharedPreferences = ProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        String email = sharedPreferences.getString("email", "");
        String notelpon = sharedPreferences.getString("no_telpon","");
        String Tempat = sharedPreferences.getString("tempat_lahir","");
        String tanggal = sharedPreferences.getString("tanggal_lahir","");

        tvEmail = findViewById(R.id.tv_Emaillengkap);
        tvNama = findViewById(R.id.tv_namalengkap);
        tvNotelp = findViewById(R.id.tv_telponlengkap);
        tvTTL = findViewById(R.id.tv_ttllengkap);

        tvEmail.setText(email);
        tvNama.setText(namaLengkap);
        tvNotelp.setText(notelpon);
        tvTTL.setText(Tempat+","+tanggal);
        kembali = findViewById(R.id.kembaliprofil);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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