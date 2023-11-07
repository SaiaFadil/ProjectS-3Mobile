package com.example.usingpreferences.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailAdvisDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDitolak;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAdvisDitolak extends AppCompatActivity {
    private TextView et_namalengkapadvis,et_tanggalpentasadvis,et_alamatadvis,et_namapentasadvis,et_lokasiadvis,catatanDitolakAdvis;

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
        showData();
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        Button kirimulang = findViewById(R.id.ajukanulang);
        kirimulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               

            }
        });
    }
    private void showData(){
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
    public void onBackPressed(){
        
    }
}