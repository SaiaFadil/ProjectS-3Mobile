package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;

public class LupaKatasandi extends AppCompatActivity {
private Button lanjutkeotp;
private TextInputEditText emailkode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lupa_katasandi);
        emailkode = findViewById(R.id.et_emailLupakatasandi);
        lanjutkeotp = findViewById(R.id.button_lupakatasandi);


        lanjutkeotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailkode.getText().toString();

                if (email.isEmpty()){
                    emailkode.setError("Email Harus Terisi");
                }else if(!email.endsWith("@gmail.com")){
                    emailkode.setError("Email Tidak Valid!");
                }else{
                    APIRequestData ardData = RetroServer.konekRetrofit().create(APIRequestData.class);
//                    Call<ResponseModelUsers> getLoginResponse = ardData.
                    Intent pindah = new Intent(LupaKatasandi.   this, KodeOtp.class);
                    startActivity(pindah);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }}
        });}


    public void onBackPressed(){
    finish();
    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}