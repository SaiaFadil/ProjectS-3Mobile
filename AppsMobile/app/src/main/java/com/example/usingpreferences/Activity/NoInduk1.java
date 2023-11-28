package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiajukan;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoInduk1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk1);
        ImageButton btnback = (ImageButton) findViewById(R.id.indukback);
        CardView btnRegisInduk = (CardView) findViewById(R.id.btnRegisInduk);
        CardView btnPerpanjangInduk = (CardView) findViewById(R.id.btnPerpanjangInduk);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        btnRegisInduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk1.this, NoInduk2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });


        btnPerpanjangInduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk1.this, NoInduk6.class);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}