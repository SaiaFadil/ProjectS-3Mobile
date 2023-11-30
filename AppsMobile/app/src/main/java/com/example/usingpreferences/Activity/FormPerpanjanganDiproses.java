package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ModelDetailPerpanjanganDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiproses;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPerpanjanganDiproses extends AppCompatActivity {
    private TextView textViewButton1, textViewButton2, textViewButton3, editTextNoInduk, editTextNIK, editTextNamaLengkap;
    private ProgressDialog progressDialog;
    public static ShimmerFrameLayout mFrameLayout;
    public LinearLayout mDataSemua;
    public static Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perpanjangan_diproses);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        editTextNoInduk = findViewById(R.id.noinduk);
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        showData();
        progressDialog = new ProgressDialog(FormPerpanjanganDiproses.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);

    }

        private void showData () {
            mFrameLayout.startShimmer();
            mDataSemua.setVisibility(View.GONE);
            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
            Call<ResponseDetailPerpanjanganDiproses> getDetail = ardData.getDetailPerpanjanganDiproses(getIntent().getStringExtra("id_perpanjangan"));
            getDetail.enqueue(new Callback<ResponseDetailPerpanjanganDiproses>() {
                @Override
                public void onResponse(Call<ResponseDetailPerpanjanganDiproses> call, Response<ResponseDetailPerpanjanganDiproses> response) {
                    if (response.body().getKode() == 1) {
                        ModelDetailPerpanjanganDiproses ambildata = response.body().getData();
                        if (ambildata.getId_perpanjangan().isEmpty()) {
                            mFrameLayout.startShimmer();
                            mDataSemua.setVisibility(View.GONE);
                        } else {
                            mFrameLayout.setVisibility(View.GONE);
                            mFrameLayout.stopShimmer();
                            mDataSemua.setVisibility(View.VISIBLE);
                            mDataSemua.startAnimation(fadeIn);
                        }
                        editTextNIK.setText(ambildata.getNik());
                        editTextNamaLengkap.setText(ambildata.getNama_seniman());
                        editTextNoInduk.setText(ambildata.getNomor_induk());
                        textViewButton1.setText(removeUploadPath(ambildata.getSurat_keterangan()));
                        textViewButton2.setText(removeUploadPath(ambildata.getKtp_seniman()));
                        textViewButton3.setText(removeUploadPath(ambildata.getPass_foto()));
                    } else if (response.body().getKode() == 0) {
                        Toast.makeText(FormPerpanjanganDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getKode() == 2) {
                        Toast.makeText(FormPerpanjanganDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseDetailPerpanjanganDiproses> call, Throwable t) {
                    t.printStackTrace();
                    showAlertDialog();
                }
            });
        }


    private String removeUploadPath(String fullPath) {
        // Cek apakah fullPath tidak null dan mengandung garis miring
        if (fullPath != null && fullPath.contains("/")) {
            // Temukan indeks garis miring terakhir
            int lastSlashIndex = fullPath.lastIndexOf("/");

            // Ambil karakter setelah garis miring terakhir
            if (lastSlashIndex < fullPath.length() - 1) {
                return fullPath.substring(lastSlashIndex + 1);
            }
        }

        // Jika input tidak memenuhi kriteria, kembalikan nilai default atau null sesuai kebutuhan
        return "";
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormPerpanjanganDiproses.this);
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
