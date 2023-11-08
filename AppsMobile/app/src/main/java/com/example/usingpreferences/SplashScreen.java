package com.example.usingpreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.Activity.MainActivity;
import com.example.usingpreferences.KonfirmMenu.SelamatDatang;

public class SplashScreen extends AppCompatActivity {
TextView textView1,textView2;
private TextView simpanteks;
private ImageView logodisporabudpar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        logodisporabudpar = findViewById(R.id.logodisporabudpar);
        simpanteks = findViewById(R.id.simpanteks);
        // Memuat animasi dari XML
        Animation fadeFromTopAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Menerapkan animasi ke ImageView
        logodisporabudpar.startAnimation(fadeFromTopAnimation);
        textView1.startAnimation(fadeFromTopAnimation);
        textView2.startAnimation(fadeFromTopAnimation);
        cekLogin();




    }

    private void cekLogin(){
        SharedPreferences sharedPreferences = this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_user = sharedPreferences.getString("id_user", "");
        String nama_lengkap = sharedPreferences.getString("nama_lengkap", "");
        String text = "<b><u>"+nama_lengkap+"</u></b>";
        simpanteks.setText(Html.fromHtml(text));

        if (!id_user.isEmpty()){
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Pindah ke activity berikutnya setelah tampilan splash selesai
                    Intent mainIntent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(mainIntent);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                    finish();
                    Toast.makeText(SplashScreen.this, "Anda Masuk Sebagai\n "+simpanteks.getText(), Toast.LENGTH_SHORT).show();

                }
            }, 3000);

        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    // Pindah ke activity berikutnya setelah tampilan splash selesai
                    Intent mainIntent = new Intent(SplashScreen.this, SelamatDatang.class);
                    startActivity(mainIntent);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                    finish();

                }
            }, 3000);
        }


    }



}