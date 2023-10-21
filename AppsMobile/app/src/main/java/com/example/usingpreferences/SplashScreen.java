package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usingpreferences.KonfirmMenu.SelamatDatang;

public class SplashScreen extends AppCompatActivity {
TextView textView1,textView2;
private ImageView logodisporabudpar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);
        logodisporabudpar = findViewById(R.id.logodisporabudpar);
        // Memuat animasi dari XML
        Animation fadeFromTopAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Menerapkan animasi ke ImageView
        logodisporabudpar.startAnimation(fadeFromTopAnimation);
        textView1.startAnimation(fadeFromTopAnimation);
        textView2.startAnimation(fadeFromTopAnimation);
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