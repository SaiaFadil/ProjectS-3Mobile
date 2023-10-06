package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.usingpreferences.R;

public class NoInduk1 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk1);
        ImageView btnback = (ImageView) findViewById(R.id.indukback);
        CardView btnRegisInduk = (CardView) findViewById(R.id.btnRegisInduk);
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
    }
}