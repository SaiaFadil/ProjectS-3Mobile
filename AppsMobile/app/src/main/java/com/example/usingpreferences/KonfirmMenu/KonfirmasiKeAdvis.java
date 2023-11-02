package com.example.usingpreferences.KonfirmMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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



                SharedPreferences sharedPreferencesseniman = getSharedPreferences("prefDataSeniman", MODE_PRIVATE);
                String idsenimanshared = sharedPreferencesseniman.getString("id_seniman", "");
                if (TextUtils.isEmpty(idsenimanshared)){

                    AlertDialog.Builder builder = new AlertDialog.Builder(KonfirmasiKeAdvis.this);
                    builder.setMessage("Selain Seniman Tidak Dapat Mengajukan Surat Advis!")
                            .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {

                                }
                            });
                    AlertDialog dialog1 = builder.create();
                    dialog1.show();
                } else {

                    startActivity(new Intent(KonfirmasiKeAdvis.this, FormulirSuratAdvisActivity.class));
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

                }





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