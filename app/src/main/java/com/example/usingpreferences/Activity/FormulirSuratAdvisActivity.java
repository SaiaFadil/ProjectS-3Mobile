package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.usingpreferences.KonfirmMenu.BerhasilTerkirimAdvis;
import com.example.usingpreferences.R;

import java.util.Calendar;

public class FormulirSuratAdvisActivity extends AppCompatActivity {
private DatePickerDialog picker;
private EditText tanggaladvis;
private Button kirimdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_surat_advis);

        tanggaladvis = findViewById(R.id.et_tanggalpentasadvis);
        ImageButton kembali = findViewById(R.id.kembalikekonfirmasiadvis);
        tanggaladvis.setInputType(InputType.TYPE_NULL);
        kirimdata = findViewById(R.id.btn_kirimformuliradvis);
        kirimdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormulirSuratAdvisActivity.this, BerhasilTerkirimAdvis.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        tanggaladvis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(FormulirSuratAdvisActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggaladvis.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },tahun,bulan,tgl);
                picker.show();
            }
        });



    }
    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}