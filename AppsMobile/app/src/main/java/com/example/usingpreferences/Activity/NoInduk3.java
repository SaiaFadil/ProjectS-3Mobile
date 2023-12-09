package com.example.usingpreferences.Activity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
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
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.DataModel.getSingkatanModel;
import com.example.usingpreferences.DataModel.getSingkatanResponse;
import com.example.usingpreferences.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class    NoInduk3 extends AppCompatActivity {

    private DatePickerDialog picker;
    private TextView tanggalinduk;
    private EditText editTextNIK,editTextNamaLengkap,editTextTL,editTextAlamat,editTextNOHP,editTextNamaOrganisasi,editTextJmlAnggota;
    private Spinner gender_spinner,kecamatan_spinner,kategoriSenimanSpinner,tipeSeniman_spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk3);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        gender_spinner = findViewById(R.id.gender_spinner);
        editTextTL = findViewById(R.id.editTextTL);
        kecamatan_spinner = findViewById(R.id.kecamatan_spinner);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNOHP = findViewById(R.id.editTextNOHP);
        tipeSeniman_spinner = findViewById(R.id.tipeSeniman_spinner);
        editTextNamaOrganisasi = findViewById(R.id.editTextNamaOrganisasi);
        editTextJmlAnggota = findViewById(R.id.editTextJmlAnggota);
        kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner);
        EditText[] editTexts = {editTextNIK, editTextNamaLengkap, editTextTL, editTextAlamat, editTextNOHP, editTextNamaOrganisasi, editTextJmlAnggota};

        kategoriSenimanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedKategoriNama = (String) parentView.getItemAtPosition(position);
                // Lakukan sesuatu dengan nama kategori yang dipilih
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle jika tidak ada yang dipilih
            }
        });

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[a-zA-Z0-9'. -]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        editTextNamaLengkap.setFilters(new InputFilter[]{filter});
        editTextNamaOrganisasi.setFilters(new InputFilter[]{filter});
        InputFilter filters = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[a-zA-Z0-9'. -]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        editTextTL.setFilters(new InputFilter[]{filters});
        InputFilter filterNoHp = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[0-9+]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        editTextNOHP.setFilters(new InputFilter[]{filterNoHp});

        InputFilter filterNIK = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[0-9]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        editTextNIK.setFilters(new InputFilter[]{filterNIK});
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
                        tanggalinduk.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
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
        //Set the minimum length pada editTextNIK
        EditText editText1 = findViewById(R.id.editTextNOHP);

        int minLength1 = 12;  // Set your desired minimum character limit
        int maxLength1 = 14; // Set your desired maximum character limit

        editText1.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // This method is called before text changes
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // This method is called when text is being changed
                String input = s.toString();

                // Check for the minimum length
                if (input.length() < minLength1) {
                    editText1.setError("Format Nomor Telepon tidak Sesuai Ketentuan");
                } else {
                    editText1.setError(null); // Clear the error if the minimum length is met
                }

                // Check for the maximum length
                if (input.length() > maxLength1) {
                    editText1.setText(input.substring(0, maxLength1)); // Trim the text to the maximum length
                    editText1.setSelection(maxLength1); // Move the cursor to the end
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
                    kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner);

                    // Buat adapter untuk spinner
                    List<String> kategoriNamaList = new ArrayList<>();
                    for (KategoriSenimanModel kategori : kategoriSenimanList) {
                        kategoriNamaList.add(kategori.getNama_kategori());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(NoInduk3.this, android.R.layout.simple_spinner_item, kategoriNamaList);

                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                    // Set adapter ke spinner
                    kategoriSenimanSpinner.setAdapter(adapter);
                    kategoriSenimanSpinner.setSelection(0);

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
                    cardViewOrganisasi.setVisibility(View.VISIBLE);
                } else if (selectedValue.equals("Perseorangan")) {
                    editTextNamaOrganisasi.setText("-");
                    editTextJmlAnggota.setText("1");
                    cardViewOrganisasi.setVisibility(View.GONE);
                } else if (selectedValue.equals("Pilih Tipe Seniman")) {
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

        Button btnnext = findViewById(R.id.button_lanjutinduk);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean anyFieldEmpty = false;

                anyFieldEmpty |= validateField(editTextNIK, "Input Harus Diisi");
                anyFieldEmpty |= validateField(editTextNamaLengkap, "Input Harus Diisi");
                anyFieldEmpty |= validateSpinner(gender_spinner, "Pilih jenis kelamin");
                anyFieldEmpty |= validateField(editTextTL, "Input Harus Diisi");
                anyFieldEmpty |= validateField(tanggalinduk, "Input Harus Diisi");
                anyFieldEmpty |= validateSpinner(kecamatan_spinner, "Pilih kecamatan");
                anyFieldEmpty |= validateField(editTextAlamat, "Input Harus Diisi");
                anyFieldEmpty |= validateField(editTextNOHP, "Input Harus Diisi");
                anyFieldEmpty |= validateSpinner(tipeSeniman_spinner, "Pilih tipe seniman");
                getSingkatan();

                String nomorTelepon = editTextNOHP.getText().toString();

                // Check if the phone number starts with "+62" or "62"
                if (nomorTelepon.startsWith("+62")) {
                    // Remove the "+", then add "0" at the beginning
                    nomorTelepon = "0" + nomorTelepon.substring(3);
                } else if (nomorTelepon.startsWith("62")) {
                    // Add "0" at the beginning
                    nomorTelepon = "0" + nomorTelepon.substring(2);
                }

                String selectedTipeSeniman = tipeSenimanSpinner.getSelectedItem().toString();
                if (selectedTipeSeniman.equals("Organisasi")) {
                    // Jika tipe seniman adalah "Organisasi", tambahkan validasi jumlah anggota di sini
                    int minNumber = 4; // Set your desired minimum numeric value for jumlah anggota

                    if (!editTextJmlAnggota.getText().toString().isEmpty()) {
                        int number = Integer.parseInt(editTextJmlAnggota.getText().toString());
                        if (number < minNumber) {
                            editTextJmlAnggota.setError("Minimal Jumlah Anggota Adalah " + minNumber);
                            editTextJmlAnggota.requestFocus();
                            anyFieldEmpty = true;
                        } else {
                            editTextJmlAnggota.setError(null); // Clear the error if the minimum value is met
                        }
                    } else {
                        editTextNamaOrganisasi.setError("Input Harus Diisi");
                        editTextNamaOrganisasi.requestFocus();
                        editTextJmlAnggota.setError("Input Harus Diisi");
                        editTextJmlAnggota.requestFocus();
                        anyFieldEmpty = true;
                    }
                }

                if (!anyFieldEmpty && !simpanSingkatannya.isEmpty()) {
                    Intent intent = new Intent(NoInduk3.this, NoInduk4.class);
                    intent.putExtra("nik", editTextNIK.getText().toString());
                    intent.putExtra("nama_seniman", editTextNamaLengkap.getText().toString());
                    intent.putExtra("jenis_kelamin", gender_spinner.getSelectedItem().toString());
                    intent.putExtra("kecamatan", kecamatan_spinner.getSelectedItem().toString());
                    intent.putExtra("nama_kategori_seniman", simpanSingkatannya);
                    intent.putExtra("tempat_lahir", editTextTL.getText().toString());
                    intent.putExtra("tanggal_lahir", tanggalinduk.getText().toString());
                    intent.putExtra("alamat_seniman", editTextAlamat.getText().toString());
                    intent.putExtra("no_telpon", nomorTelepon);
                    intent.putExtra("nama_organisasi", editTextNamaOrganisasi.getText().toString());
                    intent.putExtra("jumlah_anggota", editTextJmlAnggota.getText().toString());
                    startActivity(intent);
                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                }
            }
        });
    }

    private boolean validateField(View view, String errorMessage) {
        if (view instanceof EditText) {
            EditText editText = (EditText) view;
            if (editText.getText().toString().trim().isEmpty()) {
                editText.setError(errorMessage);
                editText.requestFocus();
                return true;
            } else {
                editText.setError(null);
                return false;
            }
        } else if (view instanceof TextView) {
            TextView textView = (TextView) view;
            if (textView.getText().toString().trim().isEmpty()) {
                textView.setError(errorMessage);
                textView.requestFocus();
                return true;
            } else {
                textView.setError(null);
                return false;
            }
        }
        return false;
    }

    private boolean validateSpinner(Spinner spinner, String errorMessage) {
        if (spinner.getSelectedItemPosition() == 0) {
            ((TextView) spinner.getSelectedView()).setError(errorMessage);
            return true;
        } else {
            ((TextView) spinner.getSelectedView()).setError(null);
            return false;
        }
    }
    private String simpanSingkatannya = "";
    private void getSingkatan(){
        String teksNamaKategori = kategoriSenimanSpinner.getSelectedItem().toString();
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<getSingkatanResponse> getRespon = ardData.getSingkatan(teksNamaKategori);

        getRespon.enqueue(
                new Callback<getSingkatanResponse>() {
                    @Override
                    public void onResponse(Call<getSingkatanResponse> call, Response<getSingkatanResponse> response) {
                        if (response.body().getKode() == 1){
                            simpanSingkatannya = response.body().getdata();
                        }
                    }

                    @Override
                    public void onFailure(Call<getSingkatanResponse> call, Throwable t) {

                    }
                }
        );


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