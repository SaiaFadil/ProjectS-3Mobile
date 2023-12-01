package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GantiPasswordProfilActivity extends AppCompatActivity {
    private EditText pwlamaganti,pwbaruganti,konfirpwbaruganti;
    private TextView id_user;
    private ProgressDialog progressDialog;
    private CheckBox tampilkansandi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ganti_password_profil);
        tampilkansandi = findViewById(R.id.checkboxpw);
        tampilkansandi.setChecked(false);
        SharedPreferences sharedPreferencesawal = GantiPasswordProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUser = sharedPreferencesawal.getString("id_user", "");
        pwlamaganti = findViewById(R.id.pwlamaganti);
        pwbaruganti = findViewById(R.id.pwbaruganti);
        konfirpwbaruganti = findViewById(R.id.konfirpwbaruganti);
        id_user = findViewById(R.id.id_user);
        id_user.setText(idUser);

        pwlamaganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        pwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        konfirpwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        progressDialog = new ProgressDialog(GantiPasswordProfilActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Harap Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        tampilkansandi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    pwlamaganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    pwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    konfirpwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    pwlamaganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    pwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    konfirpwbaruganti.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                // Set kursor ke posisi akhir teks
                pwlamaganti.setSelection(pwlamaganti.getText().length());
            }
        });

        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });


        MaterialButton btn_gantipassword = findViewById(R.id.btn_gantipassword);
        btn_gantipassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Ambil data dari input pengguna
                String idUser = id_user.getText().toString().trim();
                String passwordlama = pwlamaganti.getText().toString().trim();
                String passwordbaru = pwbaruganti.getText().toString().trim();
                String konfirmasipassword = konfirpwbaruganti.getText().toString().trim();

                // Ada perubahan, lanjutkan dengan pembaruan data
                if (idUser.isEmpty() || passwordlama.isEmpty() || passwordbaru.isEmpty() || konfirmasipassword.isEmpty()) {
                    Toast.makeText(GantiPasswordProfilActivity.this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();

                } else if (passwordbaru.length() < 8) {
                    pwbaruganti.setError("Kata Sandi minimal 8 karakter");
                    pwbaruganti.requestFocus();
                } else if (!passwordbaru.matches(".*[a-zA-Z].*") || !passwordbaru.matches(".*\\d.*")) {
                    pwbaruganti.setError("Kata Sandi harus terdiri dari huruf dan angka");
                    pwbaruganti.requestFocus();
                }  else if (!konfirmasipassword.matches(".*[a-zA-Z].*") || !konfirmasipassword.matches(".*\\d.*")) {
                    konfirpwbaruganti.setError("Kata Sandi harus terdiri dari huruf dan angka");
                    konfirpwbaruganti.requestFocus();
                } else if (!TextUtils.equals(passwordbaru, konfirmasipassword)) {
                    konfirpwbaruganti.setError("Kata Sandi tidak cocok");
                    konfirpwbaruganti.requestFocus();
                }else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(GantiPasswordProfilActivity.this);
                    builder.setMessage("Apakah Anda Yakin ingin Mengubah password Anda?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                            SharedPreferences sharedPreferences = GantiPasswordProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                            String password_lama = sharedPreferences.getString("password", "");
                            Call<ModelUpdateProfil> call = ardData.updatePasswordProfil(idUser, passwordlama,passwordbaru);
                            call.enqueue(new Callback<ModelUpdateProfil>() {
                                @Override
                                public void onResponse(Call<ModelUpdateProfil> call, Response<ModelUpdateProfil> response) {
                                    if (response.isSuccessful()) {
                                        ModelUpdateProfil result = response.body();
                                        if (result != null && result.getKode() == 1) {
                                            progressDialog.show();
                                            SharedPreferences sharedPreferencesedit = GantiPasswordProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferencesedit.edit();

                                            editor.putString("id_user", idUser);
                                            editor.putString("password", passwordbaru);
                                            editor.apply();
                                            Bersihkan();
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    AlertDialog.Builder builder = new AlertDialog.Builder(GantiPasswordProfilActivity.this);
                                                    builder.setMessage("Password Berhasil Diubah")
                                                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                public void onClick(DialogInterface dialog, int id) {

                                                                    dialog.dismiss();
                                                                }

                                                            });

                                                    AlertDialog dialog = builder.create();
                                                    dialog.show();

                                                    // Tutup ProgressDialog
                                                    progressDialog.dismiss();

                                                }
                                            }, 2000);

                                            dialog.dismiss();

                                        } else if (result != null && result.getKode() == 3){
                                            dialog.dismiss();
                                            pwlamaganti.setError("Password Lama Salah!");
                                            pwlamaganti.requestFocus();
                                        }
                                    } else {
                                        dialog.dismiss();
                                        Toast.makeText(GantiPasswordProfilActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<ModelUpdateProfil> call, Throwable t) {
                                    // Kesalahan jaringan atau kesalahan lainnya
                                    t.printStackTrace();
                                    Toast.makeText(GantiPasswordProfilActivity.this, "Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            });
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


            }
        });




    }
    private void Bersihkan(){
        pwlamaganti.setText(null);
        pwbaruganti.setText(null);
        konfirpwbaruganti.setText(null);
        pwlamaganti.setError(null);
        pwbaruganti.setError(null);
        konfirpwbaruganti.setError(null);
    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}