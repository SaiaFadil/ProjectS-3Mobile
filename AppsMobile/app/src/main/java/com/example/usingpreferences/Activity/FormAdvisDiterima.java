package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailAdvisDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiterima;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class FormAdvisDiterima extends AppCompatActivity {
    private TextView et_namalengkapadvis, et_tanggalpentasadvis, et_alamatadvis, et_namapentasadvis, et_lokasiadvis;
    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Button lihatkodesurat;
    private Animation fadeIn;
    private String id_advis = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_advis_diterima);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        lihatkodesurat = findViewById(R.id.lihatkodesurat);
        et_namalengkapadvis = findViewById(R.id.et_namalengkapadvis);
        et_tanggalpentasadvis = findViewById(R.id.et_tanggalpentasadvis);
        et_alamatadvis = findViewById(R.id.et_alamatadvis);
        et_namapentasadvis = findViewById(R.id.et_namapentasadvis);
        et_lokasiadvis = findViewById(R.id.et_lokasiadvis);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        lihatkodesurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ModelDetailAdvisDiterima item = new ModelDetailAdvisDiterima();
//                String idAdvis = item.getId_advis();

                // Kirim data ke aktivitas selanjutnya
                Intent intent = new Intent(getApplicationContext(), ActivityLihatKodeSurat.class);

                intent.putExtra("id_advis", id_advis);
                startActivity(intent);

                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        showData();
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }});
    }
    private void showData() {
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailAdvisDiterima> getDetail = ardData
                .getDetailAdvisDiterima(getIntent().getStringExtra("id_advis"));
        getDetail.enqueue(new Callback<ResponseDetailAdvisDiterima>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDiterima> call, Response<ResponseDetailAdvisDiterima> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailAdvisDiterima ambildata = response.body().getData();
                    if (ambildata.getId_advis().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.setVisibility(View.VISIBLE);
                        mDataSemua.startAnimation(fadeIn);
                    }
                    id_advis = (ambildata.getId_advis());
                    et_namalengkapadvis.setText(ambildata.getNama_advis());
                    et_tanggalpentasadvis.setText(ambildata.getTgl_advis());
                    et_alamatadvis.setText(ambildata.getAlamat_advis());
                    et_namapentasadvis.setText(ambildata.getDeskripsi_advis());
                    et_lokasiadvis.setText(ambildata.getTempat_advis());
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormAdvisDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormAdvisDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseDetailAdvisDiterima> call, Throwable t) {
                t.printStackTrace();
                showAlertDialog();
            }});}

    @Override
    public void onResume() {
        super.onResume();
        showData();
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormAdvisDiterima.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }});
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}