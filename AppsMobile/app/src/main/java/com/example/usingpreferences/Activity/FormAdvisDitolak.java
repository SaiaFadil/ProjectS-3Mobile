package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailAdvisDitolak;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDitolak;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdvisDitolak extends AppCompatActivity {
    private TextView et_namalengkapadvis, et_tanggalpentasadvis, catatanDitolakAdvis, et_alamatadvis;
    private EditText et_namapentasadvis, et_lokasiadvis;
    private Button ajukanulang;
    private DatePickerDialog picker;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_advis_ditolak);
        et_namalengkapadvis = findViewById(R.id.et_namalengkapadvis);
        catatanDitolakAdvis = findViewById(R.id.catatanDitolakAdvis);
        et_tanggalpentasadvis = findViewById(R.id.et_tanggalpentasadvis);
        et_alamatadvis = findViewById(R.id.et_alamatadvis);
        et_namapentasadvis = findViewById(R.id.et_namapentasadvis);
        et_lokasiadvis = findViewById(R.id.et_lokasiadvis);
        et_tanggalpentasadvis.setOnClickListener(new View.OnClickListener() {
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

                picker = new DatePickerDialog(FormAdvisDitolak.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            et_tanggalpentasadvis.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });
        ajukanulang = findViewById(R.id.ajukanulang);
        ajukanulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String namateks = et_namalengkapadvis.getText().toString().trim();
                String alamatteks = et_alamatadvis.getText().toString().trim();
                String untukpentasteks = et_namapentasadvis.getText().toString().trim();
                String tanggalteks = et_tanggalpentasadvis.getText().toString().trim();
                String tempatteks = et_lokasiadvis.getText().toString().trim();


                if (TextUtils.isEmpty(untukpentasteks)) {
                    et_namapentasadvis.setError("Untuk Pentas Harus Diisi!");
                    et_namapentasadvis.requestFocus();
                }else if (!untukpentasteks.matches("^[a-zA-Z' ]+$")) {
                    et_namapentasadvis.setError("Untuk Pentas Tidak Valid!");
                    et_namapentasadvis.requestFocus();

                }else if (!tempatteks.matches("^[a-zA-Z' ]+$")) {
                    et_lokasiadvis.setError("Nama Tempat Tidak Valid!");
                    et_lokasiadvis.requestFocus();

                } else if (TextUtils.isEmpty(tanggalteks)) {
                    et_tanggalpentasadvis.setError("Tanggal Pentas Harus Diisi!");
                    et_tanggalpentasadvis.requestFocus();
                } else if (TextUtils.isEmpty(tempatteks)) {
                    et_lokasiadvis.setError("Tempat Advis Harus Diisi!");
                    et_lokasiadvis.requestFocus();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(FormAdvisDitolak.this);
                    builder.setMessage("Apakah Anda Yakin Dengan Data yang anda masukkan?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            // Tampilkan ProgressDialog sebelum penghapusan

                            progressDialog.show();
                            String id_advis = getIntent().getStringExtra("id_advis");
                            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                            Call<ModelResponseAll> getResponse = ardData.ajukanulangAdvisDiajukan(id_advis, untukpentasteks, tanggalteks, tempatteks);
                            getResponse.enqueue(new Callback<ModelResponseAll>() {
                                @Override
                                public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {

                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            startActivity(new Intent(FormAdvisDitolak.this, PengajuanBerhasilTerkirim.class));
                                            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                        }
                                    }, 3000);
                                }

                                @Override
                                public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(FormAdvisDitolak.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

        progressDialog = new ProgressDialog(FormAdvisDitolak.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);


        showData();
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
    }
    private void showData() {
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailAdvisDitolak> getDetail = ardData.getDetailAdvisDitolak(getIntent().getStringExtra("id_advis"));
        getDetail.enqueue(new Callback<ResponseDetailAdvisDitolak>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDitolak> call, Response<ResponseDetailAdvisDitolak> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailAdvisDitolak ambildata = response.body().getData();
                    et_namalengkapadvis.setText(ambildata.getNama_advis());
                    et_tanggalpentasadvis.setText(ambildata.getTgl_advis());
                    et_alamatadvis.setText(ambildata.getAlamat_advis());
                    et_namapentasadvis.setText(ambildata.getDeskripsi_advis());
                    et_lokasiadvis.setText(ambildata.getTempat_advis());
                    catatanDitolakAdvis.setText(ambildata.getCatatan());
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormAdvisDitolak.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormAdvisDitolak.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseDetailAdvisDitolak> call, Throwable t) {
                Toast.makeText(FormAdvisDitolak.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onBackPressed() {

    }
}