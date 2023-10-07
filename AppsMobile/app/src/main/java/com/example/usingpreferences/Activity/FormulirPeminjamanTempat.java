package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;

import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;
import java.util.Locale;

public class FormulirPeminjamanTempat extends AppCompatActivity {
    private DatePickerDialog picker;
private EditText etJamAwal,etJamAkhir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_peminjaman_tempat);
        String namaTempat = getIntent().getStringExtra("nama_tempat");
        EditText namatempat = findViewById(R.id.et_tempatpinjam);
        namatempat.setText(namaTempat);
        // Ambil data tanggal dari intent
        String tanggal = getIntent().getStringExtra("tanggal_awal");
        EditText tanggalpinjam = findViewById(R.id.et_tanggalpinjam);
        tanggalpinjam.setText(tanggal);
        // Cari EditText dengan ID et_tanggalawal
        etJamAwal = findViewById(R.id.et_jamawalpinjam); // Inisialisasi EditText jam awal
        etJamAkhir = findViewById(R.id.et_jamakhirpinjam); // Inisialisasi EditText jam akhir

        etJamAwal.setInputType(InputType.TYPE_NULL);
        etJamAkhir.setInputType(InputType.TYPE_NULL);

        etJamAwal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan waktu saat ini
                final Calendar calendar = Calendar.getInstance();
                int jamSaatIni = calendar.get(Calendar.HOUR_OF_DAY);
                int menitSaatIni = calendar.get(Calendar.MINUTE);

                // Buat TimePickerDialog untuk memilih jam awal
                TimePickerDialog timePickerDialog = new TimePickerDialog(FormulirPeminjamanTempat.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int jam, int menit) {
                        // Format jam awal sesuai kebutuhan Anda (misalnya, "HH:mm")
                        String jamAwalFormatted = String.format(Locale.getDefault(), "%02d:%02d", jam, menit);
                        // Set teks pada EditText jam awal
                        etJamAwal.setText(jamAwalFormatted);
                    }
                }, jamSaatIni, menitSaatIni, true); // true untuk menampilkan format 24 jam

                // Tampilkan dialog pemilihan jam awal
                timePickerDialog.show();
            }
        });
        etJamAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan waktu saat ini
                final Calendar calendar = Calendar.getInstance();
                int jamSaatIni = calendar.get(Calendar.HOUR_OF_DAY);
                int menitSaatIni = calendar.get(Calendar.MINUTE);

                // Buat TimePickerDialog untuk memilih jam akhir
                TimePickerDialog timePickerDialog = new TimePickerDialog(FormulirPeminjamanTempat.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int jam, int menit) {
                        // Format jam akhir sesuai kebutuhan Anda (misalnya, "HH:mm")
                        String jamAkhirFormatted = String.format(Locale.getDefault(), "%02d:%02d", jam, menit);
                        // Set teks pada EditText jam akhir
                        etJamAkhir.setText(jamAkhirFormatted);
                    }
                }, jamSaatIni, menitSaatIni, true); // true untuk menampilkan format 24 jam

                // Tampilkan dialog pemilihan jam akhir
                timePickerDialog.show();
            }
        });
        MaterialButton btnkirimpinjam = findViewById(R.id.button_kirimpinjam);

        btnkirimpinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormulirPeminjamanTempat.this, PengajuanBerhasilTerkirim.class));
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

    }
}