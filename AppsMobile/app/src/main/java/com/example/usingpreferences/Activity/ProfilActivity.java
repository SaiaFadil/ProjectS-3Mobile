package com.example.usingpreferences.Activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;
import com.google.android.material.card.MaterialCardView;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

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
    private void ClearDataOnLogout() {


        PopupDialog.getInstance(this)
                .setStyle(Styles.IOS)
                .setHeading("Logout")
                .setDescription("Apakah Anda Yakin Ingin Keluar??")
                .setCancelable(false)
                .setPositiveButtonText("Keluar")
                .setNegativeButtonText("Batal")
                .setPositiveButtonTextColor(R.color.greendark)
                .setNegativeButtonTextColor(R.color.greendark)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onPositiveClicked(Dialog dialog) {
                        super.onPositiveClicked(dialog);
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
                        SharedPreferences sharedPreferenceseditSeniman = ProfilActivity.this.getSharedPreferences("prefDataSeniman", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editorSeniman = sharedPreferenceseditSeniman.edit();
                        editorSeniman.putString("id_seniman", null);
                        editorSeniman.putString("nik", null);
                        editorSeniman.putString("nomor_induk", null);
                        editorSeniman.putString("nama_seniman", null);
                        editorSeniman.putString("jenis_kelamin", null);
                        editorSeniman.putString("kategori", null);
                        editorSeniman.putString("kecamatan", null);
                        editorSeniman.putString("tempat_lahir", null);
                        editorSeniman.putString("tanggal_lahir", null);
                        editorSeniman.putString("alamat_seniman", null);
                        editorSeniman.putString("no_telpon", null);
                        editorSeniman.putString("nama_organisasi", null);
                        editorSeniman.putString("jumlah_anggota", null);
                        editorSeniman.putString("ktp_seniman", null);
                        editorSeniman.putString("pass_foto", null);
                        editorSeniman.putString("surat_keterangan", null);
                        editorSeniman.putString("tgl_pembuatan", null);
                        editorSeniman.putString("tgl_berlaku", null);
                        editorSeniman.putString("status", null);
                        editorSeniman.putString("catatan", null);
                        editorSeniman.putString("id_user", null);
                        editorSeniman.apply();
                        startActivity(new Intent(ProfilActivity.this, LoginActivity.class));
                        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                        dialog.dismiss();
                    }
                });
    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}