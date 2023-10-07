package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

public class FormulirUploadEvent extends AppCompatActivity {
    private DatePickerDialog picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_upload_event);


        MaterialButton button_kirimevent = findViewById(R.id.button_kirimevent);

        button_kirimevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FormulirUploadEvent.this, PengajuanBerhasilTerkirim.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        EditText et_tanggalawalevent = findViewById(R.id.et_tanggalawalevent);
        et_tanggalawalevent.setInputType(InputType.TYPE_NULL);
        EditText et_tanggalakhirevent = findViewById(R.id.et_tanggalakhirevent);
        et_tanggalakhirevent.setInputType(InputType.TYPE_NULL);

        et_tanggalawalevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
//                picker.setContextCompat.getColor(this, R.color.greendark));

                picker = new DatePickerDialog(FormulirUploadEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_tanggalawalevent.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },tahun,bulan,tgl);
                picker.show();
            }
        });
        et_tanggalakhirevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
//                picker.setContextCompat.getColor(this, R.color.greendark));

                picker = new DatePickerDialog(FormulirUploadEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        et_tanggalakhirevent.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },tahun,bulan,tgl);
                picker.show();
            }
        });
        ImageButton btnback = (ImageButton) findViewById(R.id.eventback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
    }
    public void onBackPressed(){

    }
}