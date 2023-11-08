package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;
import com.google.android.material.card.MaterialCardView;

public class TentangKamiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tentang_kami);
        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        MaterialCardView cardyoutube = findViewById(R.id.cardyoutube);
        cardyoutube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://youtube.com/@dinasporabudparbangkit?si=-eD5ySvcdr-Th2I9";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkweb));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        MaterialCardView cardfacebook = findViewById(R.id.cardfacebook);
        cardfacebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://m.facebook.com/PesonaWisataNganjuk/";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkweb));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        MaterialCardView cardinstagram = findViewById(R.id.cardinstagram);
        cardinstagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkweb = "https://instagram.com/dinasporabudpar_nganjuk?igshid=NHR2dHVlYmo2eTJr";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkweb));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
 MaterialCardView cardinstagramdev = findViewById(R.id.cardinstagramdev);
        cardinstagramdev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkigdev = "https://www.instagram.com/hufflepuff.ie/?utm_source=ig_web_button_share_sheet&igshid=OGQ5ZDc2ODk2ZA==";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkigdev));
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
 MaterialCardView cardiemaildev = findViewById(R.id.cardemaildev);
        cardiemaildev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailAddress = "hufflepuffgol.a@gmail.com";
                String emailLink = "mailto:" + emailAddress;

                Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse(emailLink));
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