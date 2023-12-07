package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
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
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDitolak;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormStatusPinjamDibatalkan extends AppCompatActivity {
    private MaterialButton selesai;
    private TextView catatan, tempat;
    private ProgressDialog progressDialog;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_status_pinjam_dibatalkan);
        selesai = findViewById(R.id.selesai);
        catatan = findViewById(R.id.catatanditolakPinjam);
        tempat = findViewById(R.id.tempatdipinjam);

        progressDialog = new ProgressDialog(FormStatusPinjamDibatalkan.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        showData();
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                PopupDialog.getInstance(FormStatusPinjamDibatalkan.this)
                        .setStyle(Styles.IOS)
                        .setHeading("Di Tolak !! ")
                        .setDescription("Pengajuan anda ditolak admin, ajukan kembali dengan klik Ya dan mengisi ulang!!")
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
                                Toast.makeText(FormStatusPinjamDibatalkan.this, "halo :" +id_sewa, Toast.LENGTH_SHORT).show();
                                APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                Call<ResponseDetailPinjamDitolak> getResponse = ardData.hapusPinjamDitolak(id_sewa);
                                getResponse.enqueue(new Callback<ResponseDetailPinjamDitolak>() {
                                    @Override
                                    public void onResponse(Call<ResponseDetailPinjamDitolak> call, Response<ResponseDetailPinjamDitolak> response) {
                                        if (response.body().getKode() == 1) {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(FormStatusPinjamDibatalkan.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                                }
                                            }, 3000);
                                        } else {
                                            System.out.println(response.body().getPesan());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseDetailPinjamDitolak> call, Throwable t) {
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
                    catatan.setText(ambildata.getCatatan());
                    tempat.setText(ambildata.getNama_tempat());
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormStatusPinjamDibatalkan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormStatusPinjamDibatalkan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(FormStatusPinjamDibatalkan.this);
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