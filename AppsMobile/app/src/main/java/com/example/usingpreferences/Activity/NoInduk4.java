package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.Layout;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.SenimanResponse;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoInduk4 extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private Animation fadeIn, fadeout;
    private CheckBox menyetujui;
    private Drawable ic_error;
    private TextView textViewButton1, textViewButton2, textViewButton3, teks_kesalahan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk4);

        teks_kesalahan = findViewById(R.id.teks_kesalahan);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_down);
        fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_down);
        teks_kesalahan.setVisibility(View.INVISIBLE);
        menyetujui = findViewById(R.id.checkboxsetuju);

        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        ic_error = getResources().getDrawable(R.drawable.ic_error);

        menyetujui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    teks_kesalahan.setVisibility(View.INVISIBLE);
                    teks_kesalahan.startAnimation(fadeout);

                } else {
                    teks_kesalahan.setVisibility(View.VISIBLE);
                    teks_kesalahan.startAnimation(fadeIn);

                }
            }
        });
        ImageButton btnback = findViewById(R.id.indukback);
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        Button btnnext = findViewById(R.id.button_kiriminduk);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(NoInduk4.this, R.anim.shake_animation);

                if (TextUtils.isEmpty(textViewButton1.getText())) {
                    textViewButton1.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
                    textViewButton1.setHintTextColor(getResources().getColor(R.color.errorText));
                    textViewButton1.setHint("Surat Keterangan Belum Di Pilih    ");
                    textViewButton1.startAnimation(shake);
                } else if (TextUtils.isEmpty(textViewButton2.getText())) {
                    textViewButton2.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
                    textViewButton2.setHintTextColor(getResources().getColor(R.color.errorText));
                    textViewButton2.setHint("Foto KTP Belum Di Pilih                        ");
                    textViewButton2.startAnimation(shake);
                } else if (TextUtils.isEmpty(textViewButton3.getText())) {
                    textViewButton3.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_error, 0);
                    textViewButton3.setHintTextColor(getResources().getColor(R.color.errorText));
                    textViewButton3.setHint("Pas Foto Belum Di Pilih                        ");
                    textViewButton3.startAnimation(shake);
                } else if (!menyetujui.isChecked()) {
                    teks_kesalahan.setVisibility(View.VISIBLE);
                    teks_kesalahan.startAnimation(fadeIn);
                } else {
                    textViewButton1.clearAnimation();
                    textViewButton2.clearAnimation();
                    textViewButton3.clearAnimation();
                    textViewButton1.setHintTextColor(null);
                    textViewButton2.setHintTextColor(null);
                    textViewButton3.setHintTextColor(null);

                    AlertDialog.Builder builder = new AlertDialog.Builder(NoInduk4.this);
                    builder.setTitle("Konfirmasi Pengiriman");
                    builder.setMessage("Apakah Anda yakin ingin mengirim pengajuan pembuatan Nomor Induk Seniman?");
                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            sendDataToServer();
                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
                    builder.show();
                }
            }
        });
        // Initialize other views and listeners
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        ic_error = getResources().getDrawable(R.drawable.ic_error);
    }
    private void sendDataToServer() {
        Intent intent = getIntent();
        String nik = intent.getStringExtra("nik");

        // Get id_user dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        // Mengambil data lainnya dari intent
        String namaSeniman = intent.getStringExtra("nama_seniman");
        String jenisKelamin = intent.getStringExtra("jenis_kelamin");
        String kecamatan = intent.getStringExtra("kecamatan");
        String namaKategoriSeniman = intent.getStringExtra("nama_kategori_seniman");
        String tempatLahir = intent.getStringExtra("tempat_lahir");
        String tanggalLahir = intent.getStringExtra("tanggal_lahir");
        String alamatSeniman = intent.getStringExtra("alamat_seniman");
        String noTelpon = intent.getStringExtra("no_telpon");
        String namaOrganisasi = intent.getStringExtra("nama_organisasi");
        String jumlahAnggota = intent.getStringExtra("jumlah_anggota");

        // Persiapkan berkas gambar KTP Seniman, dokumen Surat Keterangan, dan gambar Pass Foto
        File suratKeteranganFile = new File(textViewButton1.getText().toString());
        File ktpSenimanFile = new File(textViewButton2.getText().toString());
        File passFotoFile = new File(textViewButton3.getText().toString());
        // Buat RequestBody untuk berkas-berkas tersebut
        RequestBody requestFileSuratKeterangan = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
        MultipartBody.Part suratKeteranganPart = MultipartBody.Part.createFormData("surat_keterangan", suratKeteranganFile.getName(), requestFileSuratKeterangan);
        RequestBody requestFileKtpSeniman = RequestBody.create(MediaType.parse("multipart/form-data"), pathKtp);
        MultipartBody.Part ktpSenimanPart = MultipartBody.Part.createFormData("ktp_seniman", ktpSenimanFile.getName(), requestFileKtpSeniman);
        RequestBody requestFilePassFoto = RequestBody.create(MediaType.parse("multipart/form-data"), pathPasFoto);
        MultipartBody.Part passFotoPart = MultipartBody.Part.createFormData("pass_foto", passFotoFile.getName(), requestFilePassFoto);
        // Mengirim data dan berkas ke server
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<SenimanResponse> getResponse = ardData.saveDataSeniman(nik, idUserShared, namaSeniman, jenisKelamin, kecamatan, namaKategoriSeniman, tempatLahir, tanggalLahir, alamatSeniman, noTelpon, "diajukan", namaOrganisasi, jumlahAnggota, suratKeteranganPart, ktpSenimanPart, passFotoPart);
        getResponse.enqueue(new Callback<SenimanResponse>() {
            @Override
            public void onResponse(Call<SenimanResponse> call, Response<SenimanResponse> response) {
                System.out.println("REsponse data " + response.message() + "Response data" + response.body() + "res" + response.errorBody());
                System.out.println("REsponse data " + response.body().getStatus() + "Response data" + response.body() + "res" + response.errorBody());
                Gson gson = new Gson();
                System.out.println("REsponse data " + gson.toJson(response.body()) + "Response data" + response.body() + "res" + response.errorBody());

                if (response.body() != null && "success".equals(response.body().getStatus())) {
                    startActivity(new Intent(NoInduk4.this, PengajuanBerhasilTerkirim.class));
                }
            }
            @Override
            public void onFailure(Call<SenimanResponse> call, Throwable t) {
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
                textViewButton1.setText(selectedFileName);
                textViewButton1.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathSurat = uriToByteArray(this,data.getData());
            } else if (requestCode == R.id.selectFileButton2) {
                textViewButton2.setText(selectedFileName);
                textViewButton2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathKtp = uriToByteArray(this,data.getData());
            } else if (requestCode == R.id.selectFileButton3) {
                textViewButton3.setText(selectedFileName);
                textViewButton3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathPasFoto = uriToByteArray(this,data.getData());
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
}
