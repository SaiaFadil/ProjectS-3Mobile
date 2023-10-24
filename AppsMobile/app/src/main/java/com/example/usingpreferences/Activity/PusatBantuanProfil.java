package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.usingpreferences.R;
import com.google.android.material.card.MaterialCardView;

public class PusatBantuanProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusat_bantuan_profil);
        MaterialCardView cardwebsite = findViewById(R.id.cardwebsite);
        cardwebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://disporabudpar.nganjukkab.go.id/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkweb));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

    }   public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}