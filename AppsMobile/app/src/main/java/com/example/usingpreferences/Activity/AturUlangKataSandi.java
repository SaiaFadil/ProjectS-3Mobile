package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.KonfirmMenu.BerhasilGantiSandi;
import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AturUlangKataSandi extends AppCompatActivity {
    private Button aturulang;
    private String email, passwordstring, konfpas;
    private CheckBox tampilkansandi;

    private TextInputEditText password, konfirmasipassword;

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atur_ulang_kata_sandi);
        progressDialog = new ProgressDialog(AturUlangKataSandi.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        tampilkansandi = findViewById(R.id.checkboxpw);
        tampilkansandi.setChecked(false);
        password = findViewById(R.id.et_passwordLupakatasandi);
        konfirmasipassword = findViewById(R.id.et_konfirmasipasswordLupakatasandi);
        email = getIntent().getStringExtra("emaillupa");
        password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        konfirmasipassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        tampilkansandi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    konfirmasipassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                } else {
                    password.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    konfirmasipassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                }

                // Set kursor ke posisi akhir teks
                password.setSelection(password.getText().length());
            }
        });
        aturulang = findViewById(R.id.aturulangkatasandi);
        aturulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passwordstring = password.getText().toString().trim();
                konfpas = konfirmasipassword.getText().toString().trim();
                if (email.isEmpty() || passwordstring.isEmpty() || konfpas.isEmpty()) {
                    Toast.makeText(AturUlangKataSandi.this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
                } else if (passwordstring.length() < 8) {
                    password.setError("Kata Sandi minimal 8 karakter");
                    password.requestFocus();
                } else if (!passwordstring.matches(".*[a-zA-Z].*") || !passwordstring.matches(".*\\d.*")) {
                    password.setError("Kata Sandi harus terdiri dari huruf dan angka");
                    password.requestFocus();
                } else if (!konfpas.matches(".*[a-zA-Z].*") || !konfpas.matches(".*\\d.*")) {
                    konfirmasipassword.setError("Kata Sandi harus terdiri dari huruf dan angka");
                    konfirmasipassword.requestFocus();
                } else if (!TextUtils.equals(passwordstring, konfpas)) {
                    konfirmasipassword.setError("Kata Sandi tidak cocok");
                    konfirmasipassword.requestFocus();
                    tampilkansandi.setChecked(false);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(AturUlangKataSandi.this);
                    builder.setMessage("Apakah Anda Yakin Mengubah Kata Sandi Anda?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {


                            progressDialog.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                    Call<ModelUpdateProfil> getRegisterResponse = ardData.updatePasswordLupa(email, passwordstring);
                                    getRegisterResponse.enqueue(new Callback<ModelUpdateProfil>() {
                                        @Override
                                        public void onResponse(Call<ModelUpdateProfil> call, Response<ModelUpdateProfil> response) {
                                            progressDialog.dismiss();
                                            if (response.body().getKode() == 1) {
                                                Intent pindah = new Intent(AturUlangKataSandi.this, BerhasilGantiSandi.class);
                                                startActivity(pindah);
                                                finish();
                                                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                            } else if (response.body().getKode() == 0) {
                                                Toast.makeText(AturUlangKataSandi.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                            } else if (response.body().getKode() == 2) {
                                                Toast.makeText(AturUlangKataSandi.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ModelUpdateProfil> call, Throwable t) {
                                            progressDialog.dismiss();
                                            Toast.makeText(AturUlangKataSandi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }
                            }, 1000);


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

    public void onBackPressed() {

    }
}