package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailAdvisDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiproses;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdvisDiproses extends AppCompatActivity {
    private TextView et_namalengkapadvis,et_tanggalpentasadvis,et_alamatadvis,et_namapentasadvis,et_lokasiadvis;

    private  ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_advis_diproses);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        et_namalengkapadvis = findViewById(R.id.et_namalengkapadvis);
        et_tanggalpentasadvis = findViewById(R.id.et_tanggalpentasadvis);
        et_alamatadvis = findViewById(R.id.et_alamatadvis);
        et_namapentasadvis = findViewById(R.id.et_namapentasadvis);
        et_lokasiadvis = findViewById(R.id.et_lokasiadvis);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);

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
    private void showData(){
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailAdvisDiproses> getDetail = ardData.getDetailAdvisDiproses(getIntent().getStringExtra("id_advis"));
        getDetail.enqueue(new Callback<ResponseDetailAdvisDiproses>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDiproses> call, Response<ResponseDetailAdvisDiproses> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailAdvisDiproses ambildata = response.body().getData();

                    if (ambildata.getId_advis().isEmpty()){
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    }else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.setVisibility(View.VISIBLE);
                        mDataSemua.startAnimation(fadeIn);
                    }
                    et_namalengkapadvis.setText(ambildata.getNama_advis());
                    et_tanggalpentasadvis.setText(ambildata.getTgl_advis());
                    et_alamatadvis.setText(ambildata.getAlamat_advis());
                    et_namapentasadvis.setText(ambildata.getDeskripsi_advis());
                    et_lokasiadvis.setText(ambildata.getTempat_advis());
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormAdvisDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormAdvisDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseDetailAdvisDiproses> call, Throwable t) {
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormAdvisDiproses.this);
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
    @Override
    public void onResume() {
        super.onResume();
        showData();

    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}