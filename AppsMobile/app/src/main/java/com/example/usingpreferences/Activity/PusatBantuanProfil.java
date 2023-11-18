package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;
import com.google.android.material.card.MaterialCardView;

public class PusatBantuanProfil extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pusat_bantuan_profil);

        MaterialCardView cardwhatsapp = findViewById(R.id.cardwhatsapp);
        cardwhatsapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://wa.me/628113319289";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkweb));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        MaterialCardView email = findViewById(R.id.cardemail);
        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = "disporabudpar@nganjukkab.go.id";
                String emailLink = "mailto:" + emailAddress;

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailLink));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });

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
        MaterialCardView cardalamat = findViewById(R.id.cardalamat);
        cardalamat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://goo.gl/maps/s7oAxZZqgfWFrnES8";
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