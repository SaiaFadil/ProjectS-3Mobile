package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.DataModel.VerifyResponse;
import com.example.usingpreferences.DataModel.VerifyUtil;
import com.example.usingpreferences.KonfirmMenu.BerhasilDaftar;
import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KodeOtp extends AppCompatActivity {
    private Button konfir;
    private OtpTextView inputotp;

    private String email, otp;

    private String nama_lengkap, no_telpon, password;

    private int totalSeconds;

    private VerifyUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp);
        konfir = findViewById(R.id.button_konfirmasiotpp);
        inputotp = findViewById(R.id.kode_otp);

        email = getIntent().getStringExtra("email");
        otp = getIntent().getStringExtra("otp");
        nama_lengkap = getIntent().getStringExtra("nama_lengkap");
        no_telpon = getIntent().getStringExtra("no_telpon");
        password = getIntent().getStringExtra("password");

        Log.e("OTP ", otp.toString());

        konfir.setEnabled(false);
        util = new VerifyUtil(this);

        totalSeconds = 10;
        Log.e("OTP", "" + totalSeconds);
        updateSecond();


        konfir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RetroServer.getInstance().sendEmail(
                        util.getEmail(), "SignUp", "update", "27"
                ).enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(KodeOtp.this, "OTP Terkirim", Toast.LENGTH_SHORT).show();
                            konfir.setEnabled(false);

                            util = new VerifyUtil(KodeOtp.this, response.body().getData());
                            Log.e("TES", "resend 1 : " + response.body().getData().getResend());
                            Log.e("TES", "resend : " + util.getResendSeconds());
                            totalSeconds = Integer.parseInt(response.body().getData().getResend()) * 60;
                            updateSecond();
                            otp = util.getOtp();

                        } else {
                            Toast.makeText(KodeOtp.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<VerifyResponse> call, Throwable t) {
                        Toast.makeText(KodeOtp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        inputotp.setOtpListener(new OTPListener() {
            @Override
            public void onInteractionListener() {

            }

            @Override
            public void onOTPComplete(String otp) {
                Log.e("OTP COM ", otp);
                if (KodeOtp.this.otp.equals(inputotp.getOTP())) {
                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                    Call<ResponseModelUsers> getRegisterResponse = ardData.register(nama_lengkap, no_telpon,email, password);
                    getRegisterResponse.enqueue(new Callback<ResponseModelUsers>() {
                        @Override
                        public void onResponse(Call<ResponseModelUsers> call, Response<ResponseModelUsers> response) {

                            if (response.body().kode == 1) {
                                Intent pindah = new Intent(KodeOtp.this, BerhasilDaftar.class);

                                // Kirim email ke LoginActivity
                                pindah.putExtra("email", email);
                                startActivity(pindah);
                                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                            } else if (response.body().kode == 0) {
                                Toast.makeText(KodeOtp.this, "Email Sudah Pernah Terdaftar!!", Toast.LENGTH_SHORT).show();
                            } else if (response.body().kode == 2) {
                                Toast.makeText(KodeOtp.this, "Registrasi Gagal", Toast.LENGTH_SHORT).show();
                            }

                        }

                        @Override
                        public void onFailure(Call<ResponseModelUsers> call, Throwable t) {
                            Toast.makeText(KodeOtp.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });

                } else {
                    Toast.makeText(KodeOtp.this, "OTP SALAH", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }

    private void updateButtonName() {
        int minutes = totalSeconds / 60;
        if (totalSeconds > 59) {
            konfir.setText("Kirim Ulang (" + minutes + " Menit)");
        } else {
            konfir.setText("Kirim Ulang (" + totalSeconds + " Detik)");
        }
    }

    private void updateSecond() {
        Handler handler = new Handler(Looper.getMainLooper());

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (KodeOtp.this.totalSeconds > 0) {
                    updateButtonName();
                    KodeOtp.this.totalSeconds--;
                    handler.postDelayed(this, 1000);
                } else {
                    konfir.setText(("Kirim Ulang"));
                    konfir.setEnabled(true);
                }
            }
        };
        handler.post(runnable);
    }

}