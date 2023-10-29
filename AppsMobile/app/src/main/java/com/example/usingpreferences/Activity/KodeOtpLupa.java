package com.example.usingpreferences.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.VerifyResponse;
import com.example.usingpreferences.DataModel.VerifyUtil;
import com.example.usingpreferences.R;

import in.aabhasjindal.otptextview.OTPListener;
import in.aabhasjindal.otptextview.OtpTextView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class KodeOtpLupa extends AppCompatActivity {
    private Button konfir;
    private OtpTextView inputotp;

    private String email, otp;
    private ProgressDialog progressDialog;
    private TextView tulisansalah;


    private int totalSeconds;
    private Animation fadeIn;

    private VerifyUtil util;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kode_otp_lupa);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in);
        progressDialog = new ProgressDialog(KodeOtpLupa.this);
        progressDialog.setTitle("Sedang Mengirim Ulang OTP...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        konfir = findViewById(R.id.button_konfirmasiotpp);
        inputotp = findViewById(R.id.kode_otp);
        tulisansalah = findViewById(R.id.tulisansalah);
        email = getIntent().getStringExtra("emailupa");
        otp = getIntent().getStringExtra("otp");
        Log.e("OTP ", otp.toString());
        konfir.setEnabled(false);
        util = new VerifyUtil(this);
        util.setEmail(email);
        totalSeconds = 10;
        Log.e("OTP", "" + totalSeconds);
        updateSecond();

        konfir.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                tulisansalah.setVisibility(View.INVISIBLE);
                inputotp.setOTP(null);
                progressDialog.show();
                totalSeconds = 0;
                RetroServer.getInstance().sendEmail(
                        util.getEmail(), "SignUp", "update", "1"
                ).enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                progressDialog.dismiss();
                                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                    Toast.makeText(KodeOtpLupa.this, "OTP Terkirim", Toast.LENGTH_SHORT).show();

                                    konfir.setEnabled(false);
                                    totalSeconds = totalSeconds + 60;
                                    util = new VerifyUtil(KodeOtpLupa.this,response.body().getData());
                                    otp = util.getOtp();
                                    updateSecond();


                                } else {
                                    Toast.makeText(KodeOtpLupa.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                            }
                        }, 2000);
                    }
                    @Override
                    public void onFailure(Call<VerifyResponse> call, Throwable t) {
                        progressDialog.dismiss();
                        Toast.makeText(KodeOtpLupa.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
                if (KodeOtpLupa.this.otp.equals(inputotp.getOTP())) {
                    String emailambil = email;
                    util.setOtp(null);
                    Intent pindah = new Intent(KodeOtpLupa.this, AturUlangKataSandi.class);
                    pindah.putExtra("emaillupa", emailambil);
                    startActivity(pindah);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

                } else {

                    tulisansalah.setVisibility(View.VISIBLE);
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
                if (KodeOtpLupa.this.totalSeconds > 0) {
                    updateButtonName();
                    KodeOtpLupa.this.totalSeconds--;
                    handler.postDelayed(this, 1100);
                } else if (KodeOtpLupa.this.totalSeconds == 0){
                    konfir.setText(("Kirim Ulang"));
                    konfir.setEnabled(true);
                }
            }
        };
        handler.post(runnable);
    }
}