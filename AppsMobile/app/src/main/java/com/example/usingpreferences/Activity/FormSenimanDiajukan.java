package com.example.usingpreferences.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.DataModel.ModelDetailSenimanDiajukan;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiajukan;
import com.example.usingpreferences.DataModel.ResponsesetKategoriOnSpinner;
import com.example.usingpreferences.DataModel.getSingkatanResponse;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSenimanDiajukan extends AppCompatActivity {
    private TextView tanggalinduk, textViewButton1, textViewButton2, textViewButton3;
    private EditText editTextNIK, editTextNamaLengkap, editTextTL, editTextAlamat, editTextNOHP, editTextNamaOrganisasi, editTextJmlAnggota;
    private Spinner gender_spinner, kecamatan_spinner, kategoriSenimanSpinner, tipeSenimanSpinner;
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private Button editdiajukan, batalkandiajukan;
    private String simpanSingkatannya = "";
    private String simpanSingkatannyadua = "";
    private CardView cardViewOrganisasi;
    private DatePickerDialog picker;
    private ProgressDialog progressDialog;
    public static ShimmerFrameLayout mFrameLayout;
    public LinearLayout mDataSemua;
    public static Animation fadeIn;
    List<String> kategoriNamaList;
    private String Perubahan1 = null;
    private String Perubahan2 = null;
    private String Perubahan3 = null;
    private List<String> tipeList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_seniman_diajukan);
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
        kecamatan_spinner = findViewById(R.id.kecamatan_spinner);
        kategoriSenimanSpinner = findViewById(R.id.kategoriSeniman_spinner_diajukan);
        tipeSenimanSpinner = findViewById(R.id.tipeseniman_spinner);
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        editdiajukan = findViewById(R.id.editdiajukan);
        cardViewOrganisasi = findViewById(R.id.cardview_organisasi);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        showData();
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

        progressDialog = new ProgressDialog(FormSenimanDiajukan.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        // Mengatur OnClickListener untuk tanggalinduk
        tanggalinduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mendapatkan tanggal dari TextView
                String dateString = tanggalinduk.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                Date date;
                Calendar cldr = Calendar.getInstance();
                try {
                    date = sdf.parse(dateString);
                    cldr.setTime(date);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
                picker = new DatePickerDialog(FormSenimanDiajukan.this, new DatePickerDialog.OnDateSetListener() {
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

        int minLength = 16;  // Set your desired minimum character limit
        int maxLength = 16; // Set your desired maximum character limit

        editTextNIK.addTextChangedListener(new TextWatcher() {
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
                    editTextNIK.setError("Format NIK tidak Sesuai Ketentuan");
                } else {
                    editTextNIK.setError(null); // Clear the error if the minimum length is met
                }

                // Check for the maximum length
                if (input.length() > maxLength) {
                    editTextNIK.setText(input.substring(0, maxLength)); // Trim the text to the maximum length
                    editTextNIK.setSelection(maxLength); // Move the cursor to the end
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                // This method is called after text changes
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
        batalkandiajukan = findViewById(R.id.batalkandiajukan);
        batalkandiajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupDialog.getInstance(FormSenimanDiajukan.this)
                        .setStyle(Styles.IOS)
                        .setHeading("Batalkan Pengajuan?")
                        .setDescription("Apakah Anda Ingin Membatalkan Pengajuan Yang Telah Terkirim??")
                        .setCancelable(false)
                        .setPositiveButtonText("Ya")
                        .setNegativeButtonText("Tidak")
                        .setPositiveButtonTextColor(R.color.greendark)
                        .setNegativeButtonTextColor(R.color.greendark)
                        .showDialog(new OnDialogButtonClickListener() {
                            @Override
                            public void onPositiveClicked(Dialog dialog) {
                                super.onPositiveClicked(dialog);
                                progressDialog.show();
                                String id_seniman = getIntent().getStringExtra("id_seniman");
                                APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                Call<ResponseDetailSenimanDiajukan> getResponse = ardData.hapusSenimanDiajukan(id_seniman);
                                getResponse.enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                    @Override
                                    public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                        if (response.body().getKode() == 1) {
                                            new Handler().postDelayed(new Runnable() {
                                                @Override
                                                public void run() {
                                                    Intent intent = new Intent(FormSenimanDiajukan.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                                }
                                            }, 3000);
                                        } else {
                                            System.out.println(response.body().getPesan());
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                        progressDialog.dismiss();
                                        showAlertDialog();
                                    }
                                });
                            }
                            @Override
                            public void onNegativeClicked(Dialog dialog) {
                                super.onNegativeClicked(dialog);
                                dialog.dismiss();

                            }
                        });
            }
        });
        editdiajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getSingkatan();
                String id_seniman = getIntent().getStringExtra("id_seniman");
                String nik = editTextNIK.getText().toString().trim();
                String namaSenimanik = editTextNamaLengkap.getText().toString().trim();
                String jenisKelamin = gender_spinner.getSelectedItem().toString().trim();
                String kecamatan = kecamatan_spinner.getSelectedItem().toString().trim();
                String tempatLahir = editTextTL.getText().toString().trim();
                String tanggalLahir = tanggalinduk.getText().toString().trim();
                String alamatSeniman = editTextAlamat.getText().toString().trim();
                String noTelpon = editTextNOHP.getText().toString().trim();
                String namaOrganisasi = editTextNamaOrganisasi.getText().toString().trim();
                String jumlahAnggota = editTextJmlAnggota.getText().toString().trim();
                String surat = textViewButton1.getText().toString().trim();
                String ktp = textViewButton2.getText().toString().trim();
                String passfoto = textViewButton3.getText().toString().trim();

                // Penanganan setError untuk setiap widget input
                if (TextUtils.isEmpty(nik)) {
                    editTextNIK.setError("NIK harus diisi!");
                    editTextNIK.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(namaSenimanik)) {
                    editTextNamaLengkap.setError("Nama Lengkap harus diisi!");
                    editTextNamaLengkap.requestFocus();
                    return;
                }

                if (gender_spinner.getSelectedItemPosition() == 0) {
                    // Penanganan setError untuk Spinner
                    ((TextView) gender_spinner.getSelectedView()).setError("Pilih Jenis Kelamin!");
                    gender_spinner.requestFocus();
                    return;
                }

                if (kecamatan_spinner.getSelectedItemPosition() == 0) {
                    // Penanganan setError untuk Spinner
                    ((TextView) kecamatan_spinner.getSelectedView()).setError("Pilih Kecamatan!");
                    kecamatan_spinner.requestFocus();
                    return;
                }
                if (tipeSenimanSpinner.getSelectedItemPosition() == 0) {
                    // Penanganan setError untuk Spinner
                    ((TextView) tipeSenimanSpinner.getSelectedView()).setError("Pilih Tipe Seniman!");
                    tipeSenimanSpinner.requestFocus();
                    return;
                }

                if (TextUtils.isEmpty(tempatLahir)) {
                    editTextTL.setError("Tempat Lahir harus diisi!");
                    editTextTL.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(tanggalLahir)) {
                    tanggalinduk.setError("Tanggal Lahir harus diisi!");
                    tanggalinduk.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(alamatSeniman)) {
                    editTextAlamat.setError("Alamat harus diisi!");
                    editTextAlamat.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(noTelpon)) {
                    editTextNOHP.setError("No Hp Harus diisi!");
                    editTextNOHP.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(namaOrganisasi)) {
                    editTextNamaOrganisasi.setError("Nama Organisasi harus diisi!");
                    editTextNamaOrganisasi.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(jumlahAnggota)) {
                    editTextJmlAnggota.setError("Jumlah Anggota harus diisi!");
                    editTextJmlAnggota.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(surat)) {
                    textViewButton1.setError("Surat Keterangan harus diisi!");
                    textViewButton1.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(ktp)) {
                    textViewButton2.setError("Ktp harus diisi!");
                    textViewButton2.requestFocus();
                    return;
                }
                if (TextUtils.isEmpty(passfoto)) {
                    textViewButton3.setError("Pass Foto harus diisi!");
                    textViewButton3.requestFocus();
                    return;
                }

                PopupDialog.getInstance(FormSenimanDiajukan.this)
                        .setStyle(Styles.IOS)
                        .setHeading("Ubah Data?")
                        .setDescription("Apakah Anda Sudah Yakin Dengan Data Yang Ingin Anda Ubah??")
                        .setCancelable(false)
                        .setPositiveButtonText("Yakin")
                        .setNegativeButtonText("Belum")
                        .setPositiveButtonTextColor(R.color.greendark)
                        .setNegativeButtonTextColor(R.color.greendark)
                        .showDialog(new OnDialogButtonClickListener() {
                            @Override
                            public void onPositiveClicked(Dialog dialog) {
                                super.onPositiveClicked(dialog);
                                progressDialog.show();
                                // Persiapkan berkas gambar KTP Seniman, dokumen Surat Keterangan, dan gambar Pass Foto


                                if (Perubahan1 == null && Perubahan2 == null && Perubahan3 == null) {
                                        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                        Call<ResponseDetailSenimanDiajukan> getResponsewo = ardData.editSenimanDiajukanWithout(id_seniman, nik, namaSenimanik, jenisKelamin, kecamatan, simpanSingkatannya, tempatLahir, tanggalLahir, alamatSeniman, noTelpon, namaOrganisasi, jumlahAnggota);
                                    getResponsewo.enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                        @Override
                                        public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                            progressDialog.dismiss();
                                            if (response.isSuccessful() && response.body() != null && response.body().getKode() == 1) {
                                                Log.e("Berhasil", response.body().getPesan());
                                                // Tanggapan sukses, lakukan tindakan setelah pembaruan data
                                                new Handler().postDelayed(() -> {
                                                    Intent intent = new Intent(FormSenimanDiajukan.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                                }, 3000);
                                            } else {
                                                // Tanggapan tidak sukses, tangani kesalahan jika diperlukan
                                                progressDialog.dismiss();
                                                Log.e("Edit Seniman Error", response.message());
                                                Toast.makeText(FormSenimanDiajukan.this, "Gagal mengubah data seniman", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                            Toast.makeText(FormSenimanDiajukan.this, "gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                            t.printStackTrace();
                                            progressDialog.dismiss();
                                            Log.e("Ggal : ", t.getMessage());
                                        }
                                    });
                                } else {
                                    File suratKeteranganFile = new File(textViewButton1.getText().toString());
                                    File ktpSenimanFile = new File(textViewButton2.getText().toString());
                                    File passFotoFile = new File(textViewButton3.getText().toString());
                                    // Buat RequestBody untuk berkas-berkas tersebut
                                    if (Perubahan1 != null) {
                                        RequestBody requestFileSuratKeterangan = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
                                        MultipartBody.Part suratKeteranganPart = MultipartBody.Part.createFormData("surat_keterangan", suratKeteranganFile.getName(), requestFileSuratKeterangan);

                                        RetroServer.getInstance().updateSuratKet(id_seniman, suratKeteranganPart)
                                                .enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                                    @Override
                                                    public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                                        if (response.body().getKode() == 1) {
                                                        } else {
                                                        }
                                                    }

                                                    @Override
                                                    public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                                        Toast.makeText(FormSenimanDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });


                                    }

                                    if (Perubahan2 != null) {
                                        Toast.makeText(FormSenimanDiajukan.this, "Perubahan 2", Toast.LENGTH_SHORT).show();
                                        RequestBody requestFileKtpSeniman = RequestBody.create(MediaType.parse("multipart/form-data"), pathKtp);
                                        MultipartBody.Part ktpSenimanPart = MultipartBody.Part.createFormData("ktp_seniman", ktpSenimanFile.getName(), requestFileKtpSeniman);

                                        RetroServer.getInstance().updateKtp(id_seniman, ktpSenimanPart).enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                            @Override
                                            public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                                if (response.body().getKode() == 1) {
                                                } else {
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                                Toast.makeText(FormSenimanDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    if (Perubahan3 != null) {
                                        RequestBody requestFilePassFoto = RequestBody.create(MediaType.parse("multipart/form-data"), pathPasFoto);
                                        MultipartBody.Part passFotoPart = MultipartBody.Part.createFormData("pass_foto", passFotoFile.getName(), requestFilePassFoto);

                                        RetroServer.getInstance().updatePassFoto(id_seniman, passFotoPart).enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                            @Override
                                            public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                                if (response.body().getKode() == 1) {
                                                } else {
                                                }
                                            }

                                            @Override
                                            public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                                Toast.makeText(FormSenimanDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            }
                                        });
                                    }

                                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                    Call<ResponseDetailSenimanDiajukan> getResponsewo = ardData.editSenimanDiajukanWithout(id_seniman, nik, namaSenimanik, jenisKelamin, kecamatan, simpanSingkatannya, tempatLahir, tanggalLahir, alamatSeniman, noTelpon, namaOrganisasi, jumlahAnggota);
                                    getResponsewo.enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
                                        @Override
                                        public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                                            progressDialog.dismiss();
                                            if (response.isSuccessful() && response.body() != null && response.body().getKode() == 1) {
                                                Log.e("Berhasil", response.body().getPesan());

                                                // Tanggapan sukses, lakukan tindakan setelah pembaruan data
                                                new Handler().postDelayed(() -> {
                                                    Intent intent = new Intent(FormSenimanDiajukan.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                                    startActivity(intent);
                                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                                }, 3000);
                                            } else {
                                                // Tanggapan tidak sukses, tangani kesalahan jika diperlukan
                                                progressDialog.dismiss();
                                                Log.e("Edit Seniman Error", response.message());
                                                Toast.makeText(FormSenimanDiajukan.this, "Gagal mengubah data seniman", Toast.LENGTH_SHORT).show();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                                            Toast.makeText(FormSenimanDiajukan.this, "gagal" + t.getMessage(), Toast.LENGTH_SHORT).show();
                                            t.printStackTrace();
                                            progressDialog.dismiss();
                                            Log.e("Ggal : ", t.getMessage());
                                        }
                                    });
                                }
                            }

                            @Override
                            public void onNegativeClicked(Dialog dialog) {
                                super.onNegativeClicked(dialog);
                                progressDialog.dismiss();
                                dialog.dismiss();
                            }
                        });

            }
        });
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

                    ArrayAdapter<String> adapter = new ArrayAdapter<>(FormSenimanDiajukan.this, android.R.layout.simple_spinner_item, kategoriNamaList);

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

        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
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

    private void showData() {
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailSenimanDiajukan> getDetail = ardData.getDetailSenimanDiajukan(getIntent().getStringExtra("id_seniman"));
        getDetail.enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
            @Override
            public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailSenimanDiajukan ambildata = response.body().getData();
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
                    ArrayAdapter<String> tipeAdapter = new ArrayAdapter<>(FormSenimanDiajukan.this, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.tipe_seniman_array));
                    tipeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    tipeSenimanSpinner.setAdapter(tipeAdapter);
                    tipeSenimanSpinner.setSelection(tipeSenimanPosition);


                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormSenimanDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormSenimanDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                showAlertDialog();
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

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormSenimanDiajukan.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false).setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
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
                    Toast.makeText(FormSenimanDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponsesetKategoriOnSpinner> call, Throwable t) {

            }
        });
    }

    public static byte[] uriToByteArray(Context context, Uri uri) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            byte[] buffer = new byte[1024];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                byteArrayOutputStream.write(buffer, 0, bytesRead);
            }
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayOutputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void selectFile(String mimeType, int buttonId) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih File"),
                    buttonId // Menggunakan buttonId sebagai requestCode
            );
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle jika tidak ada aplikasi yang dapat memilih file
        }
    }

    public void selectImageFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        selectFile("image/*", view.getId());

        int requestCode = 0;
        if (view.getId() == R.id.selectFileButton2) {
            requestCode = REQUEST_CODE_SELECT_IMAGE;
        } else if (view.getId() == R.id.selectFileButton3) {
            requestCode = REQUEST_CODE_SELECT_IMAGE;
        }
        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih Foto"),
                    requestCode
            );
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle jika tidak ada aplikasi yang dapat memilih foto
        }
    }

    public void selectDocumentFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        selectFile("application/pdf", view.getId());

        if (view.getId() == R.id.selectFileButton2) {
            startActivityForResult(

                    Intent.createChooser(intent, "Pilih Dokumen PDF"),
                    REQUEST_CODE_SELECT_PDF
            );

        }
    }

    byte[] pathSurat;
    byte[] pathKtp;
    byte[] pathPasFoto;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());

            if (requestCode == R.id.selectFileButton1) {
                Perubahan1 = "ada";
                textViewButton1.setText(selectedFileName);
                textViewButton1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathSurat = uriToByteArray(this, data.getData());
            } else if (requestCode == R.id.selectFileButton2) {
                Perubahan2 = "ada";
                textViewButton2.setText(selectedFileName);
                textViewButton2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathKtp = uriToByteArray(this, data.getData());
            } else if (requestCode == R.id.selectFileButton3) {
                Perubahan3 = "ada";
                textViewButton3.setText(selectedFileName);
                textViewButton3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathPasFoto = uriToByteArray(this, data.getData());
            }
        }
    }

    public static String getFilePathFromUri(Context context, Uri uri) {
        String filePath = null;
        if (uri.getScheme() != null && uri.getScheme().equals("content")) {
            ContentResolver contentResolver = context.getContentResolver();
            Cursor cursor = contentResolver.query(uri, null, null, null, null);

            if (cursor != null && cursor.moveToFirst()) {
                int columnIndex = cursor.getColumnIndex(MediaStore.MediaColumns.DATA);
                if (columnIndex != -1) {
                    filePath = cursor.getString(columnIndex);
                }
                cursor.close();
            }
        }

        if (filePath == null) {
            // Jika pengambilan menggunakan ContentResolver gagal, coba alternatif untuk mengambil jalur dari Uri
            filePath = getFilePathFromUriAlternative(context, uri);
        }

        return filePath;
    }

    // Alternatif untuk mengambil jalur berkas dari Uri
    private static String getFilePathFromUriAlternative(Context context, Uri uri) {
        String filePath = null;
        String[] projection = {MediaStore.Images.Media.DATA};
        Cursor cursor = context.getContentResolver().query(uri, projection, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            int columnIndex = cursor.getColumnIndex(MediaStore.Images.Media.DATA);
            if (columnIndex != -1) {
                filePath = cursor.getString(columnIndex);
            }
            cursor.close();
        }

        return filePath;
    }

    // Mendapatkan ekstensi berkas dari Uri
    public static String getFileExtension(Context context, Uri uri) {
        ContentResolver contentResolver = context.getContentResolver();
        MimeTypeMap mimeTypeMap = MimeTypeMap.getSingleton();
        String extension = mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri));
        if (extension == null) {
            // Jika tidak dapat menentukan ekstensi, gunakan ekstensi default
            extension = "txt";
        }
        return extension;
    }

    @SuppressLint("Range")
    private String getFileName(Uri uri) {
        String result = null;
        if (uri.getScheme().equals("content")) {
            String[] projection = {OpenableColumns.DISPLAY_NAME};
            Cursor cursor = getContentResolver().query(uri, projection, null, null, null);

            if (cursor != null) {
                try {
                    if (cursor.moveToFirst()) {
                        result = cursor.getString(cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    }
                } finally {
                    cursor.close();
                }
            }

            if (result == null) {
                result = uri.getLastPathSegment();
            }
        }
        return result;
    }

    private void getSingkatan() {
        String teksNamaKategori = kategoriSenimanSpinner.getSelectedItem().toString();
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<getSingkatanResponse> getRespon = ardData.getSingkatan(teksNamaKategori);

        getRespon.enqueue(
                new Callback<getSingkatanResponse>() {
                    @Override
                    public void onResponse(Call<getSingkatanResponse> call, Response<getSingkatanResponse> response) {
                        if (response.body().getKode() == 1) {
                            simpanSingkatannya = response.body().getdata();
                        }
                    }

                    @Override
                    public void onFailure(Call<getSingkatanResponse> call, Throwable t) {
                    }
                }
        );
    }
}