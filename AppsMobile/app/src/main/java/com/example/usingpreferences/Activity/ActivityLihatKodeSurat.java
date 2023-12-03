package com.example.usingpreferences.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiterima;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
public class ActivityLihatKodeSurat extends AppCompatActivity {
    private TextView tv_KodeSurat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kode_surat);
        MaterialButton btn_okelihatsurat = findViewById(R.id.btn_okelihatsurat);
        btn_okelihatsurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        tv_KodeSurat = findViewById(R.id.tv_KodeSurat);
        showData();
    }
    private void showData(){
        APIRequestData ard = RetroServer.getConnection().create(APIRequestData.class);
        String kode = getIntent().getStringExtra("id_advis");
        Call<ResponseDetailAdvisDiterima> getDetail = ard.getKodeSurat(kode);
        getDetail.enqueue(new Callback<ResponseDetailAdvisDiterima>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDiterima> call, Response<ResponseDetailAdvisDiterima> response) {
                if (response.body().getKode() == 1){
                    String kode_surat = response.body().getData().getKode_verifikasi();
                    tv_KodeSurat.setText(kode_surat);
                }else {
                    Toast.makeText(ActivityLihatKodeSurat.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseDetailAdvisDiterima> call, Throwable t) {
                Toast.makeText(ActivityLihatKodeSurat.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}