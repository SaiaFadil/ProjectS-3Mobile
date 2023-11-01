package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

public class NoInduk6 extends AppCompatActivity {

    private EditText nik,nama,nislama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk6);

        nik = findViewById(R.id.editTextNIK);
        nama = findViewById(R.id.editTextNamaLengkap);
        nislama = findViewById(R.id.editTextNomorIndukLama);



        ImageButton btnback = findViewById(R.id.indukback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        Button btnnext = (Button) findViewById(R.id.button_lanjutinduk1);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nikteks = nik.getText().toString();
                String namateks = nama.getText().toString();
                String nislamateks = nislama.getText().toString();

                if (TextUtils.isEmpty(nikteks)){
                    nik.setError("NIK Harus Terisi!!");
                    nik.requestFocus();
                } else if (TextUtils.isEmpty(namateks)){
                    nama.setError("Nama Lengkap Harus Terisi!!");
                    nama.requestFocus();
                } else if (TextUtils.isEmpty(nislamateks)){
                    nislama.setError("NIS Lama Harus Terisi!!");
                    nislama.requestFocus();
                } else {

                    Intent intent = new Intent(NoInduk6.this, PengajuanBerhasilTerkirim.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                }
            }
        });

    } public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}