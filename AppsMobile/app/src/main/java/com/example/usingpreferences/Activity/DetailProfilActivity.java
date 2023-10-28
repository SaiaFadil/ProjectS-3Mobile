package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;

import org.w3c.dom.Text;

public class DetailProfilActivity extends AppCompatActivity {
    private TextView  id_user,nama_lengkap, no_telpon, tempat_lahir, email,jeniskelamin,tanggallahir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);
//
        id_user = findViewById(R.id.iduserdetail);
        nama_lengkap = findViewById(R.id.namalengkapdetail);
        no_telpon = findViewById(R.id.telepondetail);
        jeniskelamin = findViewById(R.id.jeniskelamindetail);
        tempat_lahir = findViewById(R.id.tempatlahirdetail);
        email = findViewById(R.id.emaildetail);
        tanggallahir = findViewById(R.id.tanggaldetail);
        
        
        
        
        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
    }



    private void showData(){

        SharedPreferences sharedPreferences = DetailProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        String namaLengkapShared = sharedPreferences.getString("nama_lengkap", "");
        String noTelponShared = sharedPreferences.getString("no_telpon", "");
        String tempatLahirShared = sharedPreferences.getString("tempat_lahir", "");
        String tanggalLahirShared = sharedPreferences.getString("tanggal_lahir", "");
        String emailShared = sharedPreferences.getString("email", "");
        String jenisKelaminShared = sharedPreferences.getString("jenis_kelamin", "");

        id_user.setText(idUserShared);
        nama_lengkap.setText(namaLengkapShared);
        no_telpon.setText(noTelponShared);
        tempat_lahir.setText(tempatLahirShared);
        tanggallahir.setText(tanggalLahirShared);
        email.setText(emailShared);
        jeniskelamin.setText(jenisKelaminShared);
    }
    public void onResume() {
        super.onResume();
        showData();
    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}