package com.example.usingpreferences.KonfirmMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.usingpreferences.Activity.FormulirUploadEvent;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

public class KonfirmasiAwalEvent extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_awal_event);
        MaterialButton button_lanjutevent = findViewById(R.id.button_lanjutevent);
        button_lanjutevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KonfirmasiAwalEvent.this, FormulirUploadEvent.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        ImageButton btnback = (ImageButton) findViewById(R.id.eventback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}