package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirSuratAdvisActivity extends AppCompatActivity {
    private DatePickerDialog picker;
    private CheckBox menyetujui;
    private TextView tanggaladvis;
    private EditText nisAdvis, namaAdvis, alamatAdvis, untukpentasAdvis, tempatAdvis;
    private Button kirimdata;
    private ProgressDialog progressDialog;
    private String idUser, idSeniman, Alamat, nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_surat_advis);
        menyetujui = findViewById(R.id.checkboxsetuju);
        nisAdvis = findViewById(R.id.et_induksenimanadvis);
        namaAdvis = findViewById(R.id.et_namalengkapadvis);
        alamatAdvis = findViewById(R.id.et_alamatadvis);
        untukpentasAdvis = findViewById(R.id.et_namapentasadvis);
        tempatAdvis = findViewById(R.id.et_lokasiadvis);
        ImageButton kembali = findViewById(R.id.kembalikekonfirmasiadvis);
        kirimdata = findViewById(R.id.btn_kirimformuliradvis);
        progressDialog = new ProgressDialog(FormulirSuratAdvisActivity.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        tanggaladvis = findViewById(R.id.et_tanggalpentasadvis);
        SimpanDataSeniman();
        menyetujui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    kirimdata.setEnabled(true);
                } else {
                    kirimdata.setEnabled(false);
                }
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        tanggaladvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);

                // Batasi pemilihan tanggal hingga setelah 5 hari dari hari ini
                cldr.add(Calendar.DAY_OF_MONTH, 5);
                int minYear = cldr.get(Calendar.YEAR);
                int minMonth = cldr.get(Calendar.MONTH);
                int minDay = cldr.get(Calendar.DAY_OF_MONTH);

                picker = new DatePickerDialog(FormulirSuratAdvisActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            tanggaladvis.setText(year + "/" + (month + 1) + "/" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });


        kirimdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nisteks = nisAdvis.getText().toString().trim();
                String namateks = namaAdvis.getText().toString().trim();
                String alamatteks = alamatAdvis.getText().toString().trim();
                String untukpentasteks = untukpentasAdvis.getText().toString().trim();
                String tanggalteks = tanggaladvis.getText().toString().trim();
                String tempatteks = tempatAdvis.getText().toString().trim();


                if (TextUtils.isEmpty(untukpentasteks)) {
                    untukpentasAdvis.setError("Untuk Pentas Harus Diisi!");
                    untukpentasAdvis.requestFocus();
                } else if (TextUtils.isEmpty(tanggalteks)) {
                    tanggaladvis.setError("Tanggal Pentas Harus Diisi!");
                    tanggaladvis.requestFocus();

                } else if (TextUtils.isEmpty(tempatteks)) {
                    tempatAdvis.setError("Tempat Advis Harus Diisi!");
                    tempatAdvis.requestFocus();
                } else if(!menyetujui.isChecked()) {

                    kirimdata.setEnabled(false);
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormulirSuratAdvisActivity.this);
                    builder.setMessage("Apakah Anda Yakin Dengan Data yang anda masukkan?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Tampilkan ProgressDialog sebelum penghapusan

                            progressDialog.show();


                            SharedPreferences sharedPreferences = FormulirSuratAdvisActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                            String idUserShared = sharedPreferences.getString("id_user", "");
                            SharedPreferences sharedPreferencesseniman = getSharedPreferences("prefDataSeniman", MODE_PRIVATE);
                            String idSenimanShared = sharedPreferencesseniman.getString("id_seniman", "");
                            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                            Call<ModelResponseAll> getResponse = ardData.sendAdvisData(nisteks, namateks, alamatteks, untukpentasteks, tanggalteks, tempatteks, "diajukan", "", idUserShared, idSenimanShared);
                            getResponse.enqueue(new Callback<ModelResponseAll>() {
                                @Override
                                public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(new Intent(FormulirSuratAdvisActivity.this, PengajuanBerhasilTerkirim.class));
                                            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                        }
                                    }, 3000);
                                }

                                @Override
                                public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(FormulirSuratAdvisActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });


                        }

                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();


                }

            }
        });

    }


    private void SimpanDataSeniman() {
        SharedPreferences sharedPreferences = FormulirSuratAdvisActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        SharedPreferences sharedPreferencesseniman = getSharedPreferences("prefDataSeniman", MODE_PRIVATE);
        String idSenimanShared = sharedPreferencesseniman.getString("nomor_induk", "");
        String alamatShared = sharedPreferencesseniman.getString("alamat_seniman", "");
        String namaShared = sharedPreferencesseniman.getString("nama_seniman", "");

        idUser = idUserShared;
        idSeniman = idSenimanShared;
        Alamat = alamatShared;
        nama = namaShared;
        nisAdvis.setText(idSeniman);
        namaAdvis.setText(nama);
        alamatAdvis.setText(Alamat);
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}