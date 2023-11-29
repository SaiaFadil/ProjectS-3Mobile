package com.example.usingpreferences.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

public class ActivityLihatKodePerpanjangan extends AppCompatActivity {
    private TextView tv_KodeSurat1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lihat_kode_perpanjangan);
        MaterialButton btn_okelihatsurat = findViewById(R.id.btn_okelihatseniman);

        btn_okelihatsurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        tv_KodeSurat1 = findViewById(R.id.tv_KodeSeniman);
        showData();
    }

    @Override
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }

    private void showData() {
        // Get the String extras directly from the intent
        String noInduk = getIntent().getStringExtra("no_induk");

        // Display the value in the TextView
        tv_KodeSurat1.setText(noInduk);
    }

}