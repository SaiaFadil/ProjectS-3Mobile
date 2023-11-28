package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.DataModel.ModelDetailSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponsesetKategoriOnSpinner;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSenimanDiproses extends AppCompatActivity {
    private TextView tanggalinduk, textViewButton1, textViewButton2, textViewButton3;
    private TextView editTextNIK, editTextNamaLengkap, editTextTL, editTextAlamat, editTextNOHP, editTextNamaOrganisasi, editTextJmlAnggota;
    private Spinner gender_spinner, kecamatan_spinner, kategoriSenimanSpinner, tipeSenimanSpinner;
    private CardView cardViewOrganisasi;
    private List<String> tipeList = new ArrayList<>();
    List<String> kategoriNamaList;
    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_seniman_diproses);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        editTextTL = findViewById(R.id.editTextTL);
        editTextAlamat = findViewById(R.id.editTextAlamat);
        editTextNOHP = findViewById(R.id.editTextNOHP);
        editTextNamaOrganisasi = findViewById(R.id.editTextNamaOrganisasi);
        editTextJmlAnggota = findViewById(R.id.editTextJmlAnggota);
        tanggalinduk = findViewById(R.id.editTextTG);
        gender_spinner = findViewById(R.id.gender_spinner);
        gender_spinner.setEnabled(false);
        kecamatan_spinner = findViewById(R.id.kecamatan_spinner);
        kecamatan_spinner.setEnabled(false);
        kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner_diproses);
        kategoriSenimanSpinner.setEnabled(false);
        tipeSenimanSpinner = findViewById(R.id.tipeseniman_spinner);
        tipeSenimanSpinner.setEnabled(false);
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        cardViewOrganisasi = findViewById(R.id.cardview_organisasi);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        showData();

        //Pemilihan Kategori Seniman
        APIRequestData apiService = RetroServer.getInstance();
        Call<List<KategoriSenimanModel>> call = apiService.getKategoriSeniman();
        call.enqueue(new Callback<List<KategoriSenimanModel>>() {
            @Override
            public void onResponse(Call<List<KategoriSenimanModel>> call, Response<List<KategoriSenimanModel>> response) {
                if (response.isSuccessful()) {
                    List<KategoriSenimanModel> kategoriSenimanList = response.body();
                    // Buat adapter untuk spinner
                    kategoriNamaList = new ArrayList<>();
                    for (KategoriSenimanModel kategori : kategoriSenimanList) {
                        kategoriNamaList.add(kategori.getNama_kategori());
                    }

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormSenimanDiproses.this, android.R.layout.simple_spinner_item, kategoriNamaList);

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
        // Tambahkan listener untuk mengatur visibilitas CardView
        tipeSenimanSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedTipe = tipeSenimanSpinner.getSelectedItem().toString();

                // Check if the selected value is "Perorangan"
                if (selectedTipe.equals("Perseorangan")) {
                    editTextNamaOrganisasi.setError(null);
                    editTextJmlAnggota.setError(null);
                    editTextNamaOrganisasi.setText("-");
                    editTextJmlAnggota.setText("1");
                    // Jika Perorangan, sembunyikan CardView
                    cardViewOrganisasi.setVisibility(View.GONE);
                    // Check if the selected value is "Perorangan"
                } else if (selectedTipe.equals("Pilih Tipe Seniman")) {
                    ((TextView) tipeSenimanSpinner.getSelectedView()).setError("Pilih Tipe Seniman");
                    // Jika Perorangan, sembunyikan CardView
                    cardViewOrganisasi.setVisibility(View.GONE);
                } else {
                    // Jika bukan Perorangan, tampilkan CardView
                    cardViewOrganisasi.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing here
            }
        });

        gender_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle pemilihan berdasarkan indeks di sini
                if (position == 0) {
                    ((TextView) gender_spinner.getSelectedView()).setError("Pilih Jenis Kelamin");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle ketika tidak ada yang dipilih (opsional)
            }
        });
        kecamatan_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Handle pemilihan berdasarkan indeks di sini
                if (position == 0) {
                    ((TextView) kecamatan_spinner.getSelectedView()).setError("Pilih Kecamatan!");
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Handle ketika tidak ada yang dipilih (opsional)
            }
        });
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

    }

    private String removeUploadPath(String fullPath) {
        // Cek apakah fullPath tidak null dan mengandung garis miring
        if (fullPath != null && fullPath.contains("/")) {
            // Temukan indeks garis miring terakhir
            int lastSlashIndex = fullPath.lastIndexOf("/");

            // Ambil karakter setelah garis miring terakhir
            if (lastSlashIndex < fullPath.length() - 1) {
                return fullPath.substring(lastSlashIndex + 1);
            }
        }

        // Jika input tidak memenuhi kriteria, kembalikan nilai default atau null sesuai kebutuhan
        return "";
    }

    private int getGenderPosition(String genderValue) {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        for (int i = 0; i < adapter.getCount(); i++) {
            if (adapter.getItem(i).toString().equalsIgnoreCase(genderValue)) {
                return i;
            }
        }

        return 0; // Posisi default jika tidak ditemukan
    }

    private void setKategoriOnSpinner() {
        Intent intent = getIntent();
        String idseniman = intent.getStringExtra("id_seniman");

        APIRequestData apiRequestData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponsesetKategoriOnSpinner> call = apiRequestData.setKategoriOnSpinner(idseniman);
        call.enqueue(new Callback<ResponsesetKategoriOnSpinner>() {
            @Override
            public void onResponse(Call<ResponsesetKategoriOnSpinner> call, Response<ResponsesetKategoriOnSpinner> response) {
                if (response.body().getKode().equals("1")) {
                    String kategoriValue = response.body().getData();
                    // Tentukan posisi default untuk Spinner Kategori
                    int kategoriPosition = kategoriNamaList.indexOf(kategoriValue);
                    kategoriSenimanSpinner.setSelection(kategoriPosition);
                } else {
                    Toast.makeText(FormSenimanDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsesetKategoriOnSpinner> call, Throwable t) {

            }
        });
    }

    private void showData() {
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailSenimanDiproses> getDetail = ardData.getDetailSenimanDiproses(getIntent().getStringExtra("id_seniman"));
        getDetail.enqueue(new Callback<ResponseDetailSenimanDiproses>() {
            @Override
            public void onResponse(Call<ResponseDetailSenimanDiproses> call, Response<ResponseDetailSenimanDiproses> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailSenimanDiproses ambildata = response.body().getData();
                    if (ambildata.getId_seniman().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.setVisibility(View.VISIBLE);
                        mDataSemua.startAnimation(fadeIn);
                    }
                    editTextNIK.setText(ambildata.getNik());
                    editTextNamaLengkap.setText(ambildata.getNama_seniman());
                    editTextTL.setText(ambildata.getTempat_lahir());
                    editTextNOHP.setText(ambildata.getNo_telpon());
                    editTextAlamat.setText(ambildata.getAlamat_seniman());
                    editTextNamaOrganisasi.setText(ambildata.getNama_organisasi());
                    editTextJmlAnggota.setText(ambildata.getJumlah_anggota());
                    tanggalinduk.setText(ambildata.getTanggal_lahir());
                    textViewButton1.setText(removeUploadPath(ambildata.getSurat_keterangan()));
                    textViewButton2.setText(removeUploadPath(ambildata.getKtp_seniman()));
                    textViewButton3.setText(removeUploadPath(ambildata.getPass_foto()));

                    // Tentukan posisi default untuk Spinner Gender
                    String genderValue = ambildata.getJenis_kelamin();
                    int genderPosition = getGenderPosition(genderValue);
                    gender_spinner.setSelection(genderPosition);

                    // Tentukan posisi default untuk Spinner Kecamatan
                    String kecamatanValue = ambildata.getKecamatan();
                    List<String> kecamatanList = Arrays.asList(getResources().getStringArray(R.array.kecamatan_array));
                    int kecamatanPosition = kecamatanList.indexOf(kecamatanValue);
                    kecamatan_spinner.setSelection(kecamatanPosition);

                    //setKategoriOnSpinner
                    setKategoriOnSpinner();

                    // Tentukan posisi default untuk Spinner Tipe Seniman
                    String tipeSenimanValue = ambildata.getNama_organisasi();
                    int tipeSenimanPosition;
                    if (editTextNamaOrganisasi.getText().toString().equals("-")) {
                        // Jika editTextNamaOrganisasi sama dengan "-", set nilai tipeSenimanPosition ke 1 (Perseorangan)
                        tipeSenimanPosition = 1;
                    } else {
                        // Jika tidak, cari posisi di dalam tipeList
                        tipeSenimanPosition = tipeList.indexOf(tipeSenimanValue);
                        if (tipeSenimanPosition == -1) {
                            // Jika nilai tidak ditemukan, set nilai tipeSenimanPosition ke 0
                            tipeSenimanPosition = 2;
                        }
                    }

                    // Set adapter untuk spinner tipeSeniman
                    ArrayAdapter<String> tipeAdapter = new ArrayAdapter<>(FormSenimanDiproses.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipe_seniman_array));
                    tipeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    tipeSenimanSpinner.setAdapter(tipeAdapter);
                    tipeSenimanSpinner.setSelection(tipeSenimanPosition);


                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormSenimanDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormSenimanDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailSenimanDiproses> call, Throwable t) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormSenimanDiproses.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed(){
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }

}