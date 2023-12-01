package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelResponseSimpanEvent;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.google.gson.Gson;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirUploadEvent extends AppCompatActivity {
    //    private DatePickerDialog picker;
//    private EditText namaPengirim, namaEvent, tempatEvent, deskripsiEvent, linkPendaftaran;
//    private Button kirimEvent, selectFileButton2;
//    private ProgressDialog progressDialog;
//    private TextView tanggalAwalEvent, tanggalAkhirEvent, pilihfile;
//    private CheckBox menyetujuiEvent;
//    private static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private Uri imageUri;
    static final int REQUEST_CODE_SELECT_IMAGE = 1;
    private DatePickerDialog picker;
    private Animation fadeIn, fadeout;
    private CheckBox menyetujui;
    private TextView namaPengirim, tanggalAwalEvent, tanggalAkhirEvent, pilihfile, teks_kesalahan;
    private EditText namaEvent, tempatEvent, deskripsiEvent, linkPendaftaran;
    private Button buttonKirimdata, buttonpilihfile ;
    private  ImageButton eventBack;
    private ProgressDialog progressDialog;
    private String idUser, nama;

    private byte[] posterPath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_upload_event);

        teks_kesalahan = findViewById(R.id.teks_kesalahanevent);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_down);
        fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_down);

//        teks_kesalahan = findViewById(R.id.);

        teks_kesalahan.setVisibility(View.INVISIBLE);
        menyetujui = findViewById(R.id.checkboxsetujuevent);
        namaEvent = findViewById(R.id.et_namaeventevent);
        namaPengirim = findViewById(R.id.et_namapengirimevent);
//        tanggalAwalEvent = findViewById(R.id.et_tanggalawalevent);
//        tanggalAkhirEvent = findViewById(R.id.et_tanggalakhirevent);
        buttonpilihfile = findViewById(R.id.selectFileFormulirButton2);
        tempatEvent = findViewById(R.id.et_alamateventevent);
        deskripsiEvent = findViewById(R.id.et_deskripsievent);
        linkPendaftaran = findViewById(R.id.et_linkpendaftaranevent);
        ImageButton eventBack = findViewById(R.id.eventback);
        buttonKirimdata = findViewById(R.id.button_kirimevent);
        progressDialog = new ProgressDialog(FormulirUploadEvent.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        pilihfile = findViewById(R.id.pilihfile);

        SimpanDataEvent();


        InputFilter filter = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[a-zA-Z0-9'\"., ]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        namaPengirim.setFilters(new InputFilter[]{filter});
        namaEvent.setFilters(new InputFilter[]{filter});
        tempatEvent.setFilters(new InputFilter[]{filter});
        deskripsiEvent.setFilters(new InputFilter[]{filter});

        menyetujui.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    buttonKirimdata.setEnabled(true);
                    teks_kesalahan.setVisibility(View.INVISIBLE);
                    teks_kesalahan.startAnimation(fadeout);

                } else {
                    buttonKirimdata.setEnabled(false);
                    teks_kesalahan.setVisibility(View.VISIBLE);
                    teks_kesalahan.startAnimation(fadeIn);
                }
            }
        });

        eventBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        tanggalAwalEvent = findViewById(R.id.et_tanggalawalevent);
        tanggalAwalEvent.setInputType(InputType.TYPE_NULL);
        tanggalAkhirEvent = findViewById(R.id.et_tanggalakhirevent);
        tanggalAkhirEvent.setInputType(InputType.TYPE_NULL);
        tanggalAwalEvent.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
//                picker.setContextCompat.getColor(this, R.color.greendark));

                cldr.add(Calendar.DAY_OF_MONTH, 5);
                int minYear = cldr.get(Calendar.YEAR);
                int minMonth = cldr.get(Calendar.MONTH);
                int minDay = cldr.get(Calendar.DAY_OF_MONTH);
                picker = new DatePickerDialog(FormulirUploadEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            tanggalAwalEvent.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });
        tanggalAkhirEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
//                picker.setContextCompat.getColor(this, R.color.greendark));
                cldr.add(Calendar.DAY_OF_MONTH, 5);
                int minYear = cldr.get(Calendar.YEAR);
                int minMonth = cldr.get(Calendar.MONTH);
                int minDay = cldr.get(Calendar.DAY_OF_MONTH);
                picker = new DatePickerDialog(FormulirUploadEvent.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            tanggalAkhirEvent.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });


        ImageButton btnback = (ImageButton) findViewById(R.id.eventback);

        buttonKirimdata.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

//                Toast.makeText(FormulirUploadEvent.this, "PREPARE SAVING", Toast.LENGTH_LONG).show();

                String namaPengirimValue = namaPengirim.getText().toString();
                String namaEventValue = namaEvent.getText().toString();
                String tempatEventValue = tempatEvent.getText().toString();
                String tanggalAwalValue = tanggalAwalEvent.getText().toString();
                String tanggalAkhirValue = tanggalAkhirEvent.getText().toString();
                String deskripsiValue = deskripsiEvent.getText().toString();
                String linkPendaftaranValue = linkPendaftaran.getText().toString();
                ByteArrayOutputStream byteArrayOutputStream;
                byteArrayOutputStream = new ByteArrayOutputStream();
                File uploadposter = new File(buttonpilihfile.getText().toString());
                // Buat RequestBody untuk berkas-berkas tersebut
//                RequestBody requestposter = RequestBody.create(MediaType.parse("multipart/form-data"), posterPath);
//                MultipartBody.Part poster = MultipartBody.Part.createFormData("poster", uploadposter.getName(), requestp?oster);

                if (TextUtils.isEmpty(namaPengirimValue)) {
                    namaPengirim.setError("Nama Pengirim Harus Diisi!");
                    namaPengirim.requestFocus();
                } else if (namaEvent.length() <= 10) {
                    namaEvent.setError("Nama Event Harus Lebih dari 10!");
                    namaEvent.requestFocus();
                } else if (namaEvent.length() >= 75) {
                    namaEvent.setError("Nama Event Harus Kurang dari 75 karakter!");
                    namaEvent.requestFocus();
                } else if (TextUtils.isEmpty(tempatEventValue)) {
                    namaEvent.setError("Nama Event Harus Diisi!");
                    namaEvent.requestFocus();
                } else if (tempatEvent.length() <= 25) {
                    tempatEvent.setError("Tempat Event Tempat Event Harus Lebih Dari 25 Karakter!");
                    tempatEvent.requestFocus();
                } else if (tempatEvent.length() >= 150) {
                    tempatEvent.setError("Tempat Event Tempat Event Harus Kurang Dari 150 Karakter!");
                    tempatEvent.requestFocus();
                } else if (TextUtils.isEmpty(tempatEventValue)) {
                    tempatEvent.setError("Tempat Event Harus Diisi!");
                    tempatEvent.requestFocus();
                } else if (TextUtils.isEmpty(tanggalAwalValue)) {
                    tanggalAwalEvent.setError("Tanggal Awal Event Harus Diisi!");
                    tanggalAwalEvent.requestFocus();
                } else if (TextUtils.isEmpty(tanggalAkhirValue)) {
                    tanggalAkhirEvent.setError("Tanggal Akhir Event Harus Diisi!");
                    tanggalAkhirEvent.requestFocus();
                } else if (deskripsiEvent.length() <= 75) {
                    deskripsiEvent.setError("Deskripsi Event Harus Lebih Dari 75 Karakter!");
                    deskripsiEvent.requestFocus();
                } else if (deskripsiEvent.length() >= 360) {
                    deskripsiEvent.setError("Deskripsi Event Harus Kurang Dari 360 Karakter!");
                    deskripsiEvent.requestFocus();
                } else if (TextUtils.isEmpty(deskripsiValue)) {
                    deskripsiEvent.setError("Deskripsi Event Harus Diisi!");
                    deskripsiEvent.requestFocus();
                } else if (!menyetujui.isChecked()) {
                    buttonKirimdata.setEnabled(false);
                } else {

                    AlertDialog.Builder builder = new AlertDialog.Builder(FormulirUploadEvent.this);
                    builder.setMessage("Apakah Anda Yakin Dengan Data yang anda masukkan?");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Tampilkan ProgressDialog sebelum penghapusan
                            progressDialog.show();
                            SharedPreferences sharedPreferences = FormulirUploadEvent.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                            String namaPengirimValue = sharedPreferences.getString("nama_lengkap", "");
                            String id_userrrr = sharedPreferences.getString("id_user", "");
                            // Panggil metode Multipart API

                            RetroServer.getConnection().create(APIRequestData.class).event2(
                                    namaPengirimValue, "diajukan",id_userrrr
                            ).enqueue(new Callback<ModelResponseSimpanEvent>() {
                                @Override
                                public void onResponse(Call<ModelResponseSimpanEvent> call, Response<ModelResponseSimpanEvent> response) {
                                    System.out.println("REsponse data " + response.message() + "Response data" + response.body() + "res" + response.errorBody());
                                    System.out.println("REsponse data " + response.body().getKode() + "Response data" + response.body() + "res" + response.errorBody());
                                    Gson gson = new Gson();
                                    System.out.println("REsponse data " + gson.toJson(response.body()) + "Response data" + response.body() + "res" + response.errorBody());

                                    if (response.body() != null && "1".equals(response.body().getKode())) {
                                        startActivity(new Intent(FormulirUploadEvent.this, PengajuanBerhasilTerkirim.class));
                                    }

                                    if(response.body() != null && response.body().kode == 1){

                                        // Buat RequestBody untuk berkas-berkas tersebut
                                        RequestBody requestFilePoster = RequestBody.create(MediaType.parse("multipart/form-data"), posterPath);
                                        MultipartBody.Part posterMultipart = MultipartBody.Part.createFormData("poster_event", pilihfile.getText().toString(), requestFilePoster);

//                                        Toast.makeText(FormulirUploadEvent.this, tanggalAkhirValue, Toast.LENGTH_LONG).show();
                                        Log.e("TANGGAL AWAL", tanggalAwalValue);
                                        Log.e("TANGGAL AKHIR", tanggalAkhirValue);

                                        RetroServer.getConnection().create(APIRequestData.class)
                                                .event(
                                                        "", namaEventValue, deskripsiValue, tempatEventValue, tanggalAwalValue.replaceAll("/", "-"),
                                                        tanggalAkhirValue, linkPendaftaranValue, posterMultipart
                                                ).enqueue(
                                                        new Callback<ModelResponseSimpanEvent>() {
                                                            @Override
                                                            public void onResponse(Call<ModelResponseSimpanEvent> call, Response<ModelResponseSimpanEvent> response) {
                                                                if (response.body() != null && response.body().kode == 1){
                                                                    Toast.makeText(FormulirUploadEvent.this, "Berhasil", Toast.LENGTH_LONG).show();
                                                                    startActivity(
                                                                            new Intent(FormulirUploadEvent.this, PengajuanBerhasilTerkirim.class)
                                                                    );
                                                                }else {
                                                                    Toast.makeText(FormulirUploadEvent.this, "event gagal " + response.body().pesan, Toast.LENGTH_LONG).show();
                                                                }
                                                            }

                                                            @Override
                                                            public void onFailure(Call<ModelResponseSimpanEvent> call, Throwable t) {
                                                                Toast.makeText(FormulirUploadEvent.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                                                t.printStackTrace();
                                                            }
                                                        }
                                                );

                                    }else {
                                        progressDialog.dismiss();
                                        Toast.makeText(FormulirUploadEvent.this, "event  gagal " + response.body().pesan, Toast.LENGTH_LONG).show();
                                    }

                                }

                                @Override
                                public void onFailure(Call<ModelResponseSimpanEvent> call, Throwable t) {
                                    progressDialog.dismiss();
                                    Toast.makeText(FormulirUploadEvent.this, t.getMessage(), Toast.LENGTH_LONG).show();
                                    t.printStackTrace();
                                }
                            });


                        }
                    });
                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Tutup dialog jika pengguna memilih "Tidak"
                        }
                    });
                    builder.show(); // Tampilkan dialog setelah konfigurasi selesai
                }


                btnback.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                    }
                });


            }
        });

    }


    public void selectImageFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        selectFile("image/*", view.getId());

        int requestCode = 0;
        if (view.getId() == R.id.selectFileButton3) {
            requestCode = REQUEST_CODE_SELECT_IMAGE;
        } else if (view.getId() == R.id.selectFileButton2) {
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


    public static byte[] uriToByteArray(Context context, Uri uri) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            ContentResolver contentResolver = context.getContentResolver();
            InputStream inputStream = contentResolver.openInputStream(uri);
            byte[] buffer = new byte[4096];
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

    private void SimpanDataEvent() {
        SharedPreferences sharedPreferences = FormulirUploadEvent.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaPengirimShared = sharedPreferences.getString("nama_lengkap", "");

        namaPengirimShared = namaPengirimShared;
        namaPengirim.setText(namaPengirimShared);

//        namaPengirim.setText("Evita");
    }

    private void selectFile(String mimeType, int selectFileButton2) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType(mimeType);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        try {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih File"),
                    selectFileButton2 // Menggunakan buttonId sebagai requestCode
            );
        } catch (android.content.ActivityNotFoundException ex) {
            // Handle jika tidak ada aplikasi yang dapat memilih file
        }
    }

    @SuppressLint("Range")
    private String getFileNamStringe(Uri uri) {
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

    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());
            pilihfile.setText(selectedFileName);
            posterPath = uriToByteArray(this,data.getData());
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
    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}