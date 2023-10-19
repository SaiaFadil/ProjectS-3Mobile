package com.example.usingpreferences.KonfirmMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.Activity.LoginActivity;
import com.example.usingpreferences.R;

public class BerhasilDaftar extends AppCompatActivity {
Button lanjut;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_berhasil_daftar);


    lanjut = findViewById(R.id.button_lanjut);
    lanjut.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {


                startActivity(new Intent(getBaseContext(), LoginActivity.class));
            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);


        }
    });




    } @Override
    public void onBackPressed() {
        // Tidak melakukan apa-apa ketika tombol kembali ditekan
    }
}