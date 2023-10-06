package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.R;

public class NoInduk5 extends AppCompatActivity {

    private Button btnStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk5);

        btnStatus = findViewById(R.id.cekstatus);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(NoInduk5.this, MainActivity.class)
                        .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
    }
}