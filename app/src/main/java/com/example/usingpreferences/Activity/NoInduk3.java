package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.usingpreferences.R;

public class NoInduk3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk3);

        ImageView btnback = (ImageView) findViewById(R.id.indukback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk3.this, NoInduk2.class);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        Button btnnext = (Button) findViewById(R.id.button_lanjutinduk);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk3.this, NoInduk4.class);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
    }
}