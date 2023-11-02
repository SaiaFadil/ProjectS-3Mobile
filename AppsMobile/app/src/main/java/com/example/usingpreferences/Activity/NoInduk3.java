package com.example.usingpreferences.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoInduk3 extends AppCompatActivity {

    private DatePickerDialog picker;
    private EditText tanggalinduk,editTextNIK,editTextNamaLengkap,editTextTL,editTextTG,editTextAlamat,editTextNOHP,editTextNamaOrganisasi,editTextJmlAnggota;
    private Spinner gender_spinner,kecamatan_spinner,tipeSeniman_spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk3);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        gender_spinner = findViewById(R.id.gender_spinner);
        editTextTL = findViewById(R.id.editTextTL);
        editTextTG = findViewById(R.id.editTextTG);
        kecamatan_spinner = findViewById(R.id.kecamatan_spinner);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNOHP = findViewById(R.id.editTextNOHP);
        tipeSeniman_spinner = findViewById(R.id.tipeSeniman_spinner);
        editTextNamaOrganisasi = findViewById(R.id.editTextNamaOrganisasi);
        editTextJmlAnggota = findViewById(R.id.editTextJmlAnggota);
//        Spinner kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner);
        EditText[] editTexts = {editTextNIK, editTextNamaLengkap, editTextTL, editTextTG, editTextAlamat, editTextNOHP, editTextNamaOrganisasi, editTextJmlAnggota};

//        kategoriSenimanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
//                String selectedKategoriNama = (String) parentView.getItemAtPosition(position);
//                // Lakukan sesuatu dengan nama kategori yang dipilih
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parentView) {
//                // Handle jika tidak ada yang dipilih
//            }
//        });


// Inisialisasi tanggalinduk
        tanggalinduk = findViewById(R.id.editTextTG);
        tanggalinduk.setInputType(InputType.TYPE_NULL);

// Mengatur OnClickListener untuk tanggalinduk
        tanggalinduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(NoInduk3.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggalinduk.setText(dayOfMonth + "/" + (month + 1) + "/" + year);
                        tanggalinduk.setError(null);
                    }
                }, tahun, bulan, tgl);

                // Tampilkan dialog pemilih tanggal
                picker.show();
            }
        });

        //Set the minimum length pada editTextNIK
        EditText editText = findViewById(R.id.editTextNIK);

        int minLength = 16;  // Set your desired minimum character limit
        int maxLength = 16; // Set your desired maximum character limit

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when text is being changed
                String input = s.toString();

                // Check for the minimum length
                if (input.length() < minLength) {
                    editText.setError("Format NIK tidak Sesuai Ketentuan");
                } else {
                    editText.setError(null); // Clear the error if the minimum length is met
                }

                // Check for the maximum length
                if (input.length() > maxLength) {
                    editText.setText(input.substring(0, maxLength)); // Trim the text to the maximum length
                    editText.setSelection(maxLength); // Move the cursor to the end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after text changes
            }
        });

        //Set the text minimum pada editTextJmlAnggota:
        EditText textInputEditText = findViewById(R.id.editTextJmlAnggota);

        final int minNumber = 4; // Set your desired minimum numeric value

        editTextJmlAnggota.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when text is being changed
                if (!s.toString().isEmpty()) {
                    int number = Integer.parseInt(s.toString());
                    if (number < minNumber) {
                        editTextJmlAnggota.setError("Minimal Jumlah Anggota Adalah" + minNumber);
                    } else {
                        editTextJmlAnggota.setError(null); // Clear the error if the minimum value is met
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after text changes
            }
        });


        //Pemilihan Jenis Kelamin
        Spinner genderSpinner = findViewById(R.id.gender_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        //Pemilihan kecamatan
        Spinner kecamatanSpinner = findViewById(R.id.kecamatan_spinner);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.kecamatan_array, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        kecamatanSpinner.setAdapter(adapter2);

        //Pemilihan Kategori Seniman
        APIRequestData apiService = RetroServer.getInstance();

        Call<List<KategoriSenimanModel>> call = apiService.getKategoriSeniman();

        call.enqueue(new Callback<List<KategoriSenimanModel>>() {
            @Override
            public void onResponse(Call<List<KategoriSenimanModel>> call, Response<List<KategoriSenimanModel>> response) {
                if (response.isSuccessful()) {
                    List<KategoriSenimanModel> kategoriSenimanList = response.body();

                    // Ambil referensi ke spinner
//                    Spinner kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner);

                    // Buat adapter untuk spinner
                    List<String> kategoriNamaList = new ArrayList<>();
                    for (KategoriSenimanModel kategori : kategoriSenimanList) {
                        kategoriNamaList.add(kategori.getNama_kategori());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(NoInduk3.this, android.R.layout.simple_spinner_item, kategoriNamaList);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Set adapter ke spinner
//                    kategoriSenimanSpinner.setAdapter(adapter);
//                    kategoriSenimanSpinner.setSelection(0);

                    // Gunakan kategoriSenimanList untuk mengisi dropdown atau tampilan lainnya di aplikasi Anda.
                } else {
                    // Handle kesalahan
                }
            }

            @Override
            public void onFailure(Call<List<KategoriSenimanModel>> call, Throwable t) {
                // Handle kesalahan
            }
        });





        //Pemilihan tipe seniman
        Spinner tipeSenimanSpinner = findViewById(R.id.tipeSeniman_spinner);
        final CardView cardViewOrganisasi = findViewById(R.id.cardview_organisasi);

        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.tipe_seniman_array, android.R.layout.simple_spinner_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tipeSenimanSpinner.setAdapter(adapter1);

        tipeSenimanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedValue = parentView.getItemAtPosition(position).toString();
                if (selectedValue.equals("Organisasi")) {
                    bersihkanEditText();
                    cardViewOrganisasi.setVisibility(View.VISIBLE);
                } else if (selectedValue.equals("Perseorangan")) {
                    editTextNamaOrganisasi.setText("-");
                    editTextJmlAnggota.setText("1");
                    cardViewOrganisasi.setVisibility(View.GONE);
                } else if (selectedValue.equals("Pilih Tipe Seniman")) {
                    bersihkanEditText();
                    cardViewOrganisasi.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });

        //Fungsi Button Kembali
        ImageButton btnback = (ImageButton) findViewById(R.id.indukback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });

        //Fungsi Button Lanjut
        Button btnnext = findViewById(R.id.button_lanjutinduk);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean anyFieldEmpty = false;
                for (EditText editText : editTexts) {
                    if (editText.getText().toString().isEmpty()) {
                        editText.setError("Input Harus Diisi");
                        anyFieldEmpty = true;
                    }
                }

                if (gender_spinner.getSelectedItemPosition() == 0) {
                    ((TextView) gender_spinner.getSelectedView()).setError("Pilih jenis kelamin");
                    anyFieldEmpty = true;
                } else {
                    ((TextView) gender_spinner.getSelectedView()).setError(null);
                }

                if (kecamatanSpinner.getSelectedItemPosition() == 0) {
                    ((TextView) kecamatanSpinner.getSelectedView()).setError("Pilih kecamatan");
                    anyFieldEmpty = true;
                } else {
                    ((TextView) kecamatanSpinner.getSelectedView()).setError(null);
                }

                if (tipeSeniman_spinner.getSelectedItemPosition() == 0) {
                    ((TextView) tipeSeniman_spinner.getSelectedView()).setError("Pilih tipe seniman");
                    anyFieldEmpty = true;
                } else {
                    ((TextView) tipeSeniman_spinner.getSelectedView()).setError(null);
                }

                if (!anyFieldEmpty) {
                    Intent intent = new Intent(NoInduk3.this, NoInduk4.class);
                    startActivity(intent);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                }
            }
        });
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }

    public void bersihkanEditText() {
        editTextNamaOrganisasi.setText("");
        editTextJmlAnggota.setText("");
    }


}