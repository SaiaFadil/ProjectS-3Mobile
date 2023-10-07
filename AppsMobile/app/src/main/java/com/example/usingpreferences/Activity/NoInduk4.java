package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

public class NoInduk4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk4);

        ImageButton btnback = (ImageButton) findViewById(R.id.indukback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        Button btnnext = (Button) findViewById(R.id.button_kiriminduk);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk4.this, PengajuanBerhasilTerkirim.class);
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