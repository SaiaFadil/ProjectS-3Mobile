package com.example.usingpreferences.Activity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirSuratAdvisActivity extends AppCompatActivity {
    private DatePickerDialog picker;
    private TextView tanggaladvis;
    private EditText nisAdvis, namaAdvis, alamatAdvis, untukpentasAdvis, tempatAdvis;
    private Button kirimdata;
    private ProgressDialog progressDialog;
    private String idUser, idSeniman, Alamat, nama;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_surat_advis);
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
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        tanggaladvis = findViewById(R.id.et_tanggalpentasadvis);
        SimpanDataSeniman();

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

                picker = new DatePickerDialog(FormulirSuratAdvisActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggaladvis.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                    }
                }, tahun, bulan, tgl);
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
                    untukpentasAdvis.setError("Nomor Induk Seniman Harus Diisi!");
                    untukpentasAdvis.requestFocus();
                } else if (TextUtils.isEmpty(tanggalteks)) {
                    tanggaladvis.setError("Tanggal Pentas Harus Diisi!");
                    tanggaladvis.requestFocus();

                } else if (TextUtils.isEmpty(tempatteks)) {
                    tempatAdvis.setError("Tempat Advis Harus Diisi!");
                    tempatAdvis.requestFocus();

                } else {




                    SharedPreferences sharedPreferences = FormulirSuratAdvisActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    String idUserShared = sharedPreferences.getString("id_user", "");
                    SharedPreferences sharedPreferencesseniman = getSharedPreferences("prefDataSeniman", MODE_PRIVATE);
                    String idSenimanShared = sharedPreferencesseniman.getString("id_seniman", "");
                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                    Call<ModelResponseAll> getResponse = ardData.sendAdvisData(nisteks, namateks, alamatteks, untukpentasteks, tanggalteks, tempatteks, "diajukan", "", idUserShared, idSenimanShared);
                    getResponse.enqueue(new Callback<ModelResponseAll>() {
                        @Override
                        public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                            progressDialog.show();
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