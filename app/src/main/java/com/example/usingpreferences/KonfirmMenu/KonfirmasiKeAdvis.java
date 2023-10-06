package com.example.usingpreferences.KonfirmMenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.Activity.FormulirSuratAdvisActivity;
import com.example.usingpreferences.R;

public class KonfirmasiKeAdvis extends AppCompatActivity {
private Button lanjutkeform;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_konfirmasi_ke_advis);
        lanjutkeform = findViewById(R.id.button_lanjutadvis);
        lanjutkeform.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(KonfirmasiKeAdvis.this, FormulirSuratAdvisActivity.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        TextView tulisanatas = findViewById(R.id.tulisanatas);

        // Gunakan Html.fromHtml untuk membuat "Nomor induk seniman" menjadi tebal
        String text = "Surat ini hanya dapat diajukan apabila anda memiliki<b><u> kartu nomor induk seniman</u></b>. Jika anda belum memilikinya, anda dapat mendaftar terlebih dahulu.";
        tulisanatas.setText(Html.fromHtml(text));
        ImageButton kembali = findViewById(R.id.kembalikelayanan);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();

                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}