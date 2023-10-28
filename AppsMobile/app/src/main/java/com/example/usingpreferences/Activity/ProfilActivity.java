package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;

public class ProfilActivity extends AppCompatActivity {
    private ImageButton kembali;

    private TextView tvNama,tvEmail,tvNotelp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil);
        MaterialButton TentangKami = findViewById(R.id.TentangKami);
        TentangKami.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,TentangKamiActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
         MaterialCardView ProfilLengkap = findViewById(R.id.ProfilLengkap);
        ProfilLengkap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ProfilActivity.this,DetailProfilActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        MaterialButton logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ClearDataOnLogout();
            }
        });

        tvEmail = findViewById(R.id.tv_Emaillengkap);
        tvNama = findViewById(R.id.tv_namalengkap);
        tvNotelp = findViewById(R.id.tv_telponlengkap);
        kembali = findViewById(R.id.kembaliprofil);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);


            }
        });
        MaterialCardView EditProfil = findViewById(R.id.EditProfil);
        EditProfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, EditProfilActivity.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });



        MaterialButton GantiPassword = findViewById(R.id.GantiPassword);
        GantiPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, GantiPasswordProfilActivity.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });



        MaterialButton PusatBantuan = findViewById(R.id.pusatbantuan);
        PusatBantuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(ProfilActivity.this, PusatBantuanProfil.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });



    }
    @Override
    protected void onResume() {
        super.onResume();

        ShowData();
    }

    private void ShowData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        String email = sharedPreferences.getString("email", "");
        String notelpon = sharedPreferences.getString("no_telpon", "");
        tvEmail.setText(email);
        tvNama.setText(namaLengkap);
        tvNotelp.setText(notelpon);
    }
private void ClearDataOnLogout(){

    AlertDialog.Builder builder = new AlertDialog.Builder(ProfilActivity.this);
    builder.setMessage("Apakah Anda Yakin ingin Keluar Aplikasi Anda?");
    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            SharedPreferences sharedPreferencesedit = ProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferencesedit.edit();
            editor.putString("id_user", null);
            editor.putString("nama_lengkap", null);
            editor.putString("no_telpon", null);
            editor.putString("jenis_kelamin", null);
            editor.putString("tempat_lahir", null);
            editor.putString("tanggal_lahir", null);
            editor.putString("email", null);
            editor.putString("password", null);
            editor.apply();
            startActivity(new Intent(ProfilActivity.this, LoginActivity.class));
            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        }

    });
    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
        @Override
        public void onClick(DialogInterface dialog, int which) {
            dialog.dismiss();
        }
    });

    AlertDialog alertDialog = builder.create();
    alertDialog.show();

}
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}