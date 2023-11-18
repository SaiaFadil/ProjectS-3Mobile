package com.example.usingpreferences.Activity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailAdvisDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiterima;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActivityLihatKodeSurat extends AppCompatActivity {
    private TextView tv_KodeSurat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kode_surat);
        tv_KodeSurat = findViewById(R.id.tv_KodeSurat);
        showData();
    }




    private void showData(){
        APIRequestData ard = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailAdvisDiterima> getDetail = ard.getKodeSurat(getIntent().getStringExtra("id_advis"));
        getDetail.enqueue(new Callback<ResponseDetailAdvisDiterima>() {
            @Override
            public void onResponse(Call<ResponseDetailAdvisDiterima> call, Response<ResponseDetailAdvisDiterima> response) {
                if (response.body().getKode() == 1){
                    ModelDetailAdvisDiterima getData = new ModelDetailAdvisDiterima();
//                    tv_KodeSurat.setText(getData.getKodeSurat());
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