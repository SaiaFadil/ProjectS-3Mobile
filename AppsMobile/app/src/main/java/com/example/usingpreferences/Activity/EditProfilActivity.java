package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;

import com.example.usingpreferences.R;

import java.util.Calendar;

public class EditProfilActivity extends AppCompatActivity {
    private DatePickerDialog picker;
    private EditText tanggallahiredit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);
        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        tanggallahiredit = findViewById(R.id.tanggaledit);
        tanggallahiredit.setInputType(InputType.TYPE_NULL);
        tanggallahiredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(EditProfilActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggallahiredit.setText(dayOfMonth+"/"+(month + 1)+"/"+year);
                    }
                },tahun,bulan,tgl);
                picker.show();
            }
        });
        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);
    }   public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}