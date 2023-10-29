package com.example.usingpreferences.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.CekEmailModel;
import com.example.usingpreferences.DataModel.VerifyResponse;
import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LupaKatasandi extends AppCompatActivity {
    private Button lanjutkeotp;
    private TextInputEditText emailkode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_katasandi);
        emailkode = findViewById(R.id.et_emailLupakatasandi);
        lanjutkeotp = findViewById(R.id.button_lupakatasandi);
        progressDialog = new ProgressDialog(LupaKatasandi.this);
        progressDialog.setTitle("Sedang Proses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.dismiss();

        lanjutkeotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailkode.getText().toString();

                if (email.isEmpty()) {
                    emailkode.setError("Email Harus Terisi");
                } else if (!email.endsWith("@gmail.com")) {
                    emailkode.setError("Email Tidak Valid!");
                } else {
                    progressDialog.show();

                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                    Call<CekEmailModel> getLoginResponse = ardData.cekemail(emailkode.getText().toString());
                    getLoginResponse.enqueue(new Callback<CekEmailModel>() {
                        @Override
                        public void onResponse(Call<CekEmailModel> call, Response<CekEmailModel> response) {

                            if (response.body().getKode() == 1) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Tutup ProgressDialog
                                        sendOtp();

                                    }
                                }, 1000);


                            } else if (response.body().getKode() == 0) {
                                progressDialog.dismiss();
                                emailkode.requestFocus();
                                Toast.makeText(LupaKatasandi.this, "Email Belum Terdaftar", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<CekEmailModel> call, Throwable t) {
                            t.printStackTrace();
                            progressDialog.dismiss();
                        }
                    });

                }
            }
        });
    }

    private ProgressDialog progressDialog;

    private void sendOtp() {

        RetroServer.getInstance().sendEmail(emailkode.getText().toString(), "SignUp", "new", "1")
                .enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(LupaKatasandi.this, "OTP Terkirim", Toast.LENGTH_SHORT).show();

                            String email = emailkode.getText().toString();
                            Intent pindah = new Intent(LupaKatasandi.this, KodeOtpLupa.class);
                            pindah.putExtra("emailupa", email);
                            pindah.putExtra("otp", response.body().getData().getOtp());
                            startActivity(pindah);
                            finish();
                            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

                            progressDialog.dismiss();
                        } else {
                            Toast.makeText(LupaKatasandi.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<VerifyResponse> call, Throwable t) {
                        Toast.makeText(LupaKatasandi.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        t.printStackTrace();
                        progressDialog.dismiss();

                    }
                });

    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}