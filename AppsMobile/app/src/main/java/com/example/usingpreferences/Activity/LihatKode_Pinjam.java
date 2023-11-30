package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.os.Bundle;
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

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LihatKode_Pinjam extends AppCompatActivity {

    private TextView tv_KodeSurat;
    private MaterialButton oke;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kode_pinjam);
        tv_KodeSurat = findViewById(R.id.tv_KodeSurat);
        oke = findViewById(R.id.btn_okelihatsuratpinjam);
        showData();
        oke.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LihatKode_Pinjam.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
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
                    tv_KodeSurat.setText(ambildata.getKode_pinjam());   
                } else if (response.body().getKode() == 0) {
                    Toast.makeText(LihatKode_Pinjam.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(LihatKode_Pinjam.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPinjamDitolak> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }


}
