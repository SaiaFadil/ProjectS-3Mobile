package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDitolak;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormTempatDiajukan2 extends AppCompatActivity {

    private MaterialButton batalkandiajukan;
    private TextView tempat;
    private MaterialButton kembalikedashboard;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_tempat_diajukan2);
        tempat = findViewById(R.id.tempatdipinjam);
        kembalikedashboard = findViewById(R.id.kembalikedashboard);
        batalkandiajukan = findViewById(R.id.batalkandiajukan);

        progressDialog = new ProgressDialog(FormTempatDiajukan2.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        showData();
        kembalikedashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormTempatDiajukan2.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                startActivity(intent);
            }
        });
        batalkandiajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupDialog.getInstance(FormTempatDiajukan2.this)
                        .setStyle(Styles.IOS)
                        .setHeading("Batalkan Pengajuan?")
                        .setDescription("Apakah Anda Ingin Membatalkan Pengajuan Yang Telah Terkirim??")
                        .setCancelable(false)
                        .setPositiveButtonText("Ya")
                        .setNegativeButtonText("Tidak")
                        .setPositiveButtonTextColor(R.color.greendark)
                        .setNegativeButtonTextColor(R.color.greendark)
                        .showDialog(new OnDialogButtonClickListener() {
                            @Override
                            public void onPositiveClicked(Dialog dialog) {
                                super.onPositiveClicked(dialog);
                                progressDialog.show();
                                String id_sewa = getIntent().getStringExtra("id_sewa");
                                Toast.makeText(FormTempatDiajukan2.this, "halo :" +id_sewa, Toast.LENGTH_SHORT).show();
                                APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                Call<ResponseDetailPinjamDiajukan> getResponse = ardData.hapusPinjamDiajukan(id_sewa);
                                getResponse.enqueue(new Callback<ResponseDetailPinjamDiajukan>() {
                                    @Override
                                    public void onResponse(Call<ResponseDetailPinjamDiajukan> call, Response<ResponseDetailPinjamDiajukan> response) {
                                        if (response.body().getKode() == 1) {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(FormTempatDiajukan2.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                                }
                                            }, 3000);
                                        } else {
                                            System.out.println(response.body().getPesan());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseDetailPinjamDiajukan> call, Throwable t) {
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
        });
    }
    private void showData() {

        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailPinjamDitolak> getDetail = ardData.getDetailPinjamDitolak(getIntent().getStringExtra("id_sewa"));
        getDetail.enqueue(new Callback<ResponseDetailPinjamDitolak>() {
            @Override
            public void onResponse(Call<ResponseDetailPinjamDitolak> call, Response<ResponseDetailPinjamDitolak> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailPinjamDitolak ambildata = response.body().getData();
                    if (ambildata.getId_sewa().isEmpty()) {

                    } else {
                    }
                    tempat.setText(ambildata.getNama_tempat());
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormTempatDiajukan2.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormTempatDiajukan2.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseDetailPinjamDitolak> call, Throwable t) {
                t.printStackTrace();
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormTempatDiajukan2.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false).setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();
    }
}