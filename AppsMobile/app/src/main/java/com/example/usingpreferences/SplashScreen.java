package com.example.usingpreferences;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import com.example.usingpreferences.KonfirmMenu.SelamatDatang;

public class SplashScreen extends AppCompatActivity {
TextView textView1,textView2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        textView1 = findViewById(R.id.text1);
        textView2 = findViewById(R.id.text2);

        // Memuat animasi dari XML
        Animation fadeFromTopAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in);

        // Menerapkan animasi ke ImageView
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


//
////MULAI KODE EFEK
////        TextView yourTextView1 = findViewById(R.id.text1);
//        TextView yourTextView2 = findViewById(R.id.text2);
//
//        int centerX = getResources().getDisplayMetrics().widthPixels / 2;
//        int centerY = getResources().getDisplayMetrics().heightPixels / 2;
////
////        ScaleAnimation scaleAnimation1 = new ScaleAnimation(
////                0f, // Ukuran awal X
////                1f, // Ukuran akhir X
////                0f, // Ukuran awal Y
////                1f, // Ukuran akhir Y
////                centerX,
////                centerY
////        );
//        ScaleAnimation scaleAnimation2 = new ScaleAnimation(
//                0f, // Ukuran awal X
//                1f, // Ukuran akhir X
//                0f, // Ukuran awal Y
//                1f, // Ukuran akhir Y
//                centerX,
//                centerY
//        );
//
//        scaleAnimation2.setDuration(1000); // Durasi animasi dalam milidetik
//        scaleAnimation2.setFillAfter(true);// Menjaga elemen dalam ukuran akhir setelah animasi selesai
////        scaleAnimation1.setDuration(1000); // Durasi animasi dalam milidetik
////        scaleAnimation1.setFillAfter(true); // Menjaga elemen dalam ukuran akhir setelah animasi selesai
//
//        // Terapkan animasi ke TextView Anda
////        yourTextView1.startAnimation(scaleAnimation1);
//        yourTextView2.startAnimation(scaleAnimation2);
//// AKHIR KODE EFEK
    }
}