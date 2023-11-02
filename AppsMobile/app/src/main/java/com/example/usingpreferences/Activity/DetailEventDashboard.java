package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;

public class DetailEventDashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_event_dashboard);
        ImageButton kembali = findViewById(R.id.eventback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        TextView link = findViewById(R.id.linkevent);
        link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linktt = "https://www.instagram.com/saia_fadill/";
                Toast.makeText(DetailEventDashboard.this, "Link Terbuka", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linktt));
                startActivity(intent);
            }
        });
    }   public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}