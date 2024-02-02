package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.usingpreferences.R;

public class DetailEventDashboard extends AppCompatActivity {

    String nama = "", tanggal = "", deskripsi = "", link = "", gbr = "", idEvent = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event_dashboard);

        nama = getIntent().getStringExtra("nama");
        tanggal = getIntent().getStringExtra("tgl");
        deskripsi = getIntent().getStringExtra("deskripsi");
        gbr = getIntent().getStringExtra("gbr");

        ImageButton kembali = findViewById(R.id.eventback);

        TextView judul = findViewById(R.id.txtjudul);
        TextView txtTanggal = findViewById(R.id.txt_tanggal);
        TextView txtDes = findViewById(R.id.txt_des);



        judul.setText(nama);
        txtTanggal.setText(tanggal);
        txtDes.setText(deskripsi);

        ImageView imageView = findViewById(R.id.txtimage);
        Glide.with(this)
                .load("https://elok.tifnganjuk.com/DatabaseMobile/uploads/events" + gbr)
                .centerCrop()
                .into(imageView);


        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        TextView linkDes = findViewById(R.id.linkevent);
        linkDes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(DetailEventDashboard.this, "Link Terbuka", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link.toString()));
                startActivity(intent);
            }
        });
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}