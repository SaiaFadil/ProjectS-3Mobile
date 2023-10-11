package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.usingpreferences.R;

public class PinjamTempatList extends AppCompatActivity {
private CardView cardanjukladang,cardbalaibudaya,cardsoetomo,cardsedudo,cardgoamargotresno,cardrorokuning;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_tempat_list);
        cardanjukladang = findViewById(R.id.cardanjukladang);
        cardbalaibudaya = findViewById(R.id.cardbalaibudaya);
        cardsoetomo = findViewById(R.id.cardsoetomo);
        cardsedudo = findViewById(R.id.cardsedudo);
        cardgoamargotresno = findViewById(R.id.cardgoamargotresno);
        cardrorokuning = findViewById(R.id.cardrorokuning);

        cardanjukladang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Museum Anjuk Ladang");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardbalaibudaya.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Balai Budaya");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardsoetomo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Museum Dr.Soetomo");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardsedudo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Air Terjun Sedudo");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardgoamargotresno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Goa Margo Tresno");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardrorokuning.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
                pindah.putExtra("nama_tempat","Air Terjun Roro Kuning");
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });




        ImageButton pinjamback =  findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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