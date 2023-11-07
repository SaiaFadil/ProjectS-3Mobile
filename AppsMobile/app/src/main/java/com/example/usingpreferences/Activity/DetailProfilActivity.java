package com.example.usingpreferences.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;

public class DetailProfilActivity extends AppCompatActivity {
    private TextView id_user, nama_lengkap, no_telpon, tempat_lahir, email, jeniskelamin, tanggallahir, titik1, titik2, titik3, jeniskelaminteks, tempatlahirteks, tgllahirteks;
    private LinearLayout dataSeniman;
    private TextView nomorindukinput,namaorganisasiinput,jumlahanggotainput,titiks1,titiks2,namairganisasiteks,jumlahanggotateks;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profil);



        id_user = findViewById(R.id.iduserdetail);
        nama_lengkap = findViewById(R.id.namalengkapdetail);
        no_telpon = findViewById(R.id.telepondetail);
        jeniskelamin = findViewById(R.id.jeniskelamindetail);
        tempat_lahir = findViewById(R.id.tempatlahirdetail);
        email = findViewById(R.id.emaildetail);
        tanggallahir = findViewById(R.id.tanggaldetail);
        titik1 = findViewById(R.id.titil1);
        titik2 = findViewById(R.id.titil2);
        titik3 = findViewById(R.id.titil3);
        jeniskelaminteks = findViewById(R.id.jeniskelaminteks);
        tempatlahirteks = findViewById(R.id.tempatlahirteks);
        tgllahirteks = findViewById(R.id.tgllahirteks);
//

        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });


        dataSeniman = findViewById(R.id.bagianSeniman);
        nomorindukinput = findViewById(R.id.nisIndukinput);
//        kategorinput = findViewById(R.id.kategoriinput);
        namaorganisasiinput = findViewById(R.id.namaorganisasiIndukinput);
        jumlahanggotainput = findViewById(R.id.jumlahanggotainput);
        titiks1 = findViewById(R.id.titik1s);
        titiks2 = findViewById(R.id.titik2s);
        namairganisasiteks = findViewById(R.id.namaorganisasikiri);
        jumlahanggotateks = findViewById(R.id.jumlahanggotateks);

    }


    private void ShowDataSeniman(){
        SharedPreferences sharedPreferencesSeniman = DetailProfilActivity.this.getSharedPreferences("prefDataSeniman",Context.MODE_PRIVATE);
        String nomorindukShared = sharedPreferencesSeniman.getString("nomor_induk","");
        String singkatan_kategoriShared = sharedPreferencesSeniman.getString("singkatan_kategori","");
        String namaorganisasiShared = sharedPreferencesSeniman.getString("nama_organisasi","");
        String jumlahanggotaShared = sharedPreferencesSeniman.getString("jumlah_anggota","");
        nomorindukinput.setText(nomorindukShared);
//        kategorinput.setText(kategorisenimanShared);
        namaorganisasiinput.setText(namaorganisasiShared);
        jumlahanggotainput.setText(jumlahanggotaShared);


        if(TextUtils.isEmpty(nomorindukShared)){
            dataSeniman.setVisibility(View.GONE);
        } else if (TextUtils.isEmpty(namaorganisasiShared)) {
            namaorganisasiinput.setVisibility(View.GONE);
            titiks1.setVisibility(View.GONE);
            namairganisasiteks.setVisibility(View.GONE);
            titiks2.setVisibility(View.GONE);
            jumlahanggotateks.setVisibility(View.GONE);
            jumlahanggotainput.setVisibility(View.GONE);
        }else {
            dataSeniman.setVisibility(View.VISIBLE);
        }

    }
    private void showData() {
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

        if (!tempatLahirShared.equals("")) {
            tempat_lahir.setVisibility(View.VISIBLE);
            titik2.setVisibility(View.VISIBLE);
            tempatlahirteks.setVisibility(View.VISIBLE);
        } else {
            tempat_lahir.setVisibility(View.GONE);
            titik2.setVisibility(View.GONE);
            tempatlahirteks.setVisibility(View.GONE);

        }

        if (!tanggalLahirShared.equals("0000-00-00")) {
            tanggallahir.setVisibility(View.VISIBLE);
            titik3.setVisibility(View.VISIBLE);
            tgllahirteks.setVisibility(View.VISIBLE);
        } else {
            tanggallahir.setVisibility(View.GONE);
            titik3.setVisibility(View.GONE);
            tgllahirteks.setVisibility(View.GONE);
        }

    }

    public void onResume() {
        super.onResume();
        showData();
        ShowDataSeniman();

    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}