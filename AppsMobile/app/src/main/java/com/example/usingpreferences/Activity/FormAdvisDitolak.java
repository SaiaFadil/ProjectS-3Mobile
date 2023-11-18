package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
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
import com.facebook.shimmer.ShimmerFrameLayout;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

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
    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Animation fadeIn;
    private String simpannamapentas = "",simpantanggalpentas = "",simpanlokasipentas = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_advis_ditolak);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        et_namalengkapadvis = findViewById(R.id.et_namalengkapadvis);
        catatanDitolakAdvis = findViewById(R.id.catatanDitolakAdvis);
        et_tanggalpentasadvis = findViewById(R.id.et_tanggalpentasadvis);
        et_alamatadvis = findViewById(R.id.et_alamatadvis);
        et_namapentasadvis = findViewById(R.id.et_namapentasadvis);
        et_lokasiadvis = findViewById(R.id.et_lokasiadvis);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        et_tanggalpentasadvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
                // Batasi pemilihan tanggal hingga setelah 5 hari dari hari ini
                cldr.add(Calendar.DAY_OF_MONTH, 5);
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
                String untukpentasteks = et_namapentasadvis.getText().toString().trim();
                String tanggalteks = et_tanggalpentasadvis.getText().toString().trim();
                String tempatteks = et_lokasiadvis.getText().toString().trim();
                if (TextUtils.isEmpty(untukpentasteks)) {
                    et_namapentasadvis.setError("Untuk Pentas Harus Diisi!");
                    et_namapentasadvis.requestFocus();
                } else if (!untukpentasteks.matches("^[a-zA-Z' ]+$")) {
                    et_namapentasadvis.setError("Untuk Pentas Tidak Valid!");
                    et_namapentasadvis.requestFocus();
                } else if (!tempatteks.matches("^[a-zA-Z' ]+$")) {
                    et_lokasiadvis.setError("Nama Tempat Tidak Valid!");
                    et_lokasiadvis.requestFocus();

                } else if (TextUtils.isEmpty(tanggalteks)) {
                    et_tanggalpentasadvis.setError("Tanggal Pentas Harus Diisi!");
                    et_tanggalpentasadvis.requestFocus();
                } else if (TextUtils.isEmpty(tempatteks)) {
                    et_lokasiadvis.setError("Tempat Advis Harus Diisi!");
                    et_lokasiadvis.requestFocus();
                } else {
                    PopupDialog.getInstance(FormAdvisDitolak.this)
                            .setStyle(Styles.IOS)
                            .setHeading("Ajukan Ulang?")
                            .setDescription("Apakah Anda Sudah Yakin Dengan Data Yang Akan Diajukan Ulang??")
                            .setCancelable(false)
                            .setPositiveButtonText("Yakin")
                            .setNegativeButtonText("Belum")
                            .setPositiveButtonTextColor(R.color.greendark)
                            .setNegativeButtonTextColor(R.color.greendark)
                            .showDialog(new OnDialogButtonClickListener() {
                                @Override
                                public void onPositiveClicked(Dialog dialog) {
                                    super.onPositiveClicked(dialog);
                                    progressDialog.show();
                                    String id_advis = getIntent().getStringExtra("id_advis");
                                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                    Call<ModelResponseAll> getResponse = ardData
                                            .ajukanulangAdvisDiajukan(id_advis, untukpentasteks, tanggalteks, tempatteks);
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
                                            showAlertDialog();
                                        }
                                    });
                                }


                                @Override
                                public void onNegativeClicked(Dialog dialog) {
                                    super.onNegativeClicked(dialog);
                                    dialog.dismiss();

                                }
                            });
                }
            }
        });

        progressDialog = new ProgressDialog(FormAdvisDitolak.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[a-zA-Z0-9'. ]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        et_namapentasadvis.setFilters(new InputFilter[]{filter});
        et_lokasiadvis.setFilters(new InputFilter[]{filter});
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
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailAdvisDitolak> getDetail = ardData
                .getDetailAdvisDitolak(getIntent().getStringExtra("id_advis"));
        getDetail.enqueue(new Callback<ResponseDetailAdvisDitolak>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDitolak> call, Response<ResponseDetailAdvisDitolak> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailAdvisDitolak ambildata = response.body().getData();
                    simpanlokasipentas = response.body().getData().getTempat_advis();
                    simpannamapentas = response.body().getData().getAlamat_advis();
                    simpantanggalpentas = response.body().getData().getTgl_advis();
                    if (ambildata.getId_advis().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.startAnimation(fadeIn);
                        mDataSemua.setVisibility(View.VISIBLE);
                    }
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
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormAdvisDitolak.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
    public void DataTidakBerubah(){
        AlertDialog.Builder builder = new AlertDialog.Builder(FormAdvisDitolak.this);
        builder.setMessage("Tidak ada Perubahan Pada Kolom!")
                .setCancelable(false)
                .setPositiveButton("Oke", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();

                    }});
        AlertDialog alert = builder.create();
        alert.show();
    }
    @Override
    public void onResume() {
        super.onResume();
        showData();

    }

    public void onBackPressed() {

    }
}