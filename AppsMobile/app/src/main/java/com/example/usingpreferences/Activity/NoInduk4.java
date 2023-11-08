package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
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

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoInduk4 extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private TextView cardViewFileNameTextView1, cardViewFileNameTextView2, cardViewFileNameTextView3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk4);

        cardViewFileNameTextView1 = findViewById(R.id.textViewButton1);
        cardViewFileNameTextView2 = findViewById(R.id.textViewButton2);
        cardViewFileNameTextView3 = findViewById(R.id.textViewButton3);

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
                // Menerima data dari Intent yang dikirimkan dari NoInduk3
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
                File ktpSenimanFile = new File(cardViewFileNameTextView1.getText().toString());
                File suratKeteranganFile = new File(cardViewFileNameTextView2.getText().toString());
                File passFotoFile = new File(cardViewFileNameTextView3.getText().toString());

                // Buat RequestBody untuk berkas-berkas tersebut
                RequestBody requestFileKtpSeniman = RequestBody.create(MediaType.parse("multipart/form-data"), pathKtp);

                MultipartBody.Part ktpSenimanPart = MultipartBody.Part.createFormData("ktp_seniman", ktpSenimanFile.getName(), requestFileKtpSeniman);

                RequestBody requestFileSuratKeterangan = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
                MultipartBody.Part suratKeteranganPart = MultipartBody.Part.createFormData("surat_keterangan", suratKeteranganFile.getName(), requestFileSuratKeterangan);

                RequestBody requestFilePassFoto = RequestBody.create(MediaType.parse("multipart/form-data"), pathPasFoto);
                MultipartBody.Part passFotoPart = MultipartBody.Part.createFormData("pass_foto", passFotoFile.getName(), requestFilePassFoto);

                // Mengirim data dan berkas ke server
                APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                Call<SenimanResponse> getResponse = ardData.saveDataSeniman(nik, idUserShared, namaSeniman, jenisKelamin, kecamatan, namaKategoriSeniman, tempatLahir, tanggalLahir, alamatSeniman, noTelpon, "diajukan", namaOrganisasi, jumlahAnggota, ktpSenimanPart, suratKeteranganPart, passFotoPart);
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
                        Toast.makeText(NoInduk4.this, "Terjadi kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                        // Handle error
                    }


                });
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
        if (view.getId() == R.id.selectFileButton3) {
            requestCode = REQUEST_CODE_SELECT_IMAGE;
        } else if (view.getId() == R.id.selectFileButton) {
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

            if (requestCode == R.id.selectFileButton2) {
                cardViewFileNameTextView1.setText(selectedFileName);
                pathSurat = uriToByteArray(this,data.getData());
            } else if (requestCode == R.id.selectFileButton3) {
                cardViewFileNameTextView2.setText(selectedFileName);
                pathKtp = uriToByteArray(this,data.getData());
            } else if (requestCode == R.id.selectFileButton) {
                cardViewFileNameTextView3.setText(selectedFileName);
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
