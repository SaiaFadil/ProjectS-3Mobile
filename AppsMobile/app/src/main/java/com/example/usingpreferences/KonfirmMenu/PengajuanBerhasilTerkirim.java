package com.example.usingpreferences.KonfirmMenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.Activity.MainActivity;
import com.example.usingpreferences.R;

public class PengajuanBerhasilTerkirim extends AppCompatActivity {
private Button btnStatus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pengajuan_berhasil_terkirim);


        btnStatus = findViewById(R.id.cekstatus);
        btnStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PengajuanBerhasilTerkirim.this, MainActivity.class)
                        .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
    }
    public void onBackPressed(){

    }
}