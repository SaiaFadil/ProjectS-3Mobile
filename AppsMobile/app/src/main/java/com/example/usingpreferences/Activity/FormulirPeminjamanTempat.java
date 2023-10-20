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
private EditText etTanggalAwal, etTanggalAkhir;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_peminjaman_tempat);
        String namaTempat = getIntent().getStringExtra("nama_tempat");
        EditText namatempat = findViewById(R.id.et_tempatpinjam);
        namatempat.setText(namaTempat);
        // Ambil data tanggal dari intent
        String tanggal = getIntent().getStringExtra("tanggal_awal");
        EditText tanggalpinjam = findViewById(R.id.et_tanggalawalpinjam);
        tanggalpinjam.setText(tanggal);
        // Cari EditText dengan ID et_tanggalawal
        etTanggalAwal = findViewById(R.id.et_tanggalawalpinjam); // Inisialisasi EditText jam awal
        etTanggalAkhir = findViewById(R.id.et_tanggalakhirpinjam); // Inisialisasi EditText jam akhir

        etTanggalAwal.setInputType(InputType.TYPE_NULL);
        etTanggalAkhir.setInputType(InputType.TYPE_NULL);

        etTanggalAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan tanggal saat ini
                final Calendar calendar = Calendar.getInstance();
                int tahunSaatIni = calendar.get(Calendar.YEAR);
                int bulanSaatIni = calendar.get(Calendar.MONTH);
                int hariSaatIni = calendar.get(Calendar.DAY_OF_MONTH);

                // Buat DatePickerDialog untuk memilih tanggal akhir
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                        // Format tanggal akhir sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
                        String tanggalAkhirFormatted = String.format(Locale.getDefault(), "%02d/%02d/%04d", hari, bulan + 1, tahun);
                        // Set teks pada EditText tanggal akhir
                        etTanggalAkhir.setText(tanggalAkhirFormatted);
                    }
                }, tahunSaatIni, bulanSaatIni, hariSaatIni);

                // Tampilkan dialog pemilihan tanggal akhir
                datePickerDialog.show();
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