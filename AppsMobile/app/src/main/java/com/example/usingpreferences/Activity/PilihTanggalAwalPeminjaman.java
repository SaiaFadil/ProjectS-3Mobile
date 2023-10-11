package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

public class PilihTanggalAwalPeminjaman extends AppCompatActivity {
public static String namatempat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_tanggal_awal_peminjaman);
        DatePicker datePicker = findViewById(R.id.datePicker);
        TextView NamaTempatDipinjam = findViewById(R.id.NamaTempatDipinjam);
        namatempat = getIntent().getStringExtra("nama_tempat");
        NamaTempatDipinjam.setText(namatempat);
        MaterialButton button_pinjamtanggalawal = findViewById(R.id.button_pinjamtanggalawal);
        button_pinjamtanggalawal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil tanggal yang dipilih dari DatePicker
                int day = datePicker.getDayOfMonth();
                int month = datePicker.getMonth() + 1; // Penambahan 1 karena bulan dimulai dari 0
                int year = datePicker.getYear();
                // Buat Intent untuk berpindah ke halaman selanjutnya
                Intent intent = new Intent(PilihTanggalAwalPeminjaman.this, FormulirPeminjamanTempat.class);
                // Kirim tanggal yang dipilih sebagai data ekstra
                intent.putExtra("nama_tempat",namatempat);
                intent.putExtra("tanggal_awal", day + "/" + month + "/" + year);
                // Mulai halaman selanjutnya
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        ImageButton pinjamback =  findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
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