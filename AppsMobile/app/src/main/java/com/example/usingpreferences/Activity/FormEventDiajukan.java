package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.EditEventResponse;
import com.example.usingpreferences.DataModel.ModelDetailEvent;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ResponseDetailEvent;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

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

public class FormEventDiajukan extends AppCompatActivity {

    public static String ID = "id";
    private DatePickerDialog picker;
    private String dataId;

    private ImageButton imgBack;
    private CardView cardView, cardTop;
    private TextView inpPengirim, inpTglAwal, inpTglAkhir, inpPilihFile, txtStatus, txtStatusBawah;
    private EditText inpNamaEvent, inpTempat, inpDeskripsi, inpLink;
    private ImageView imageView;
    private MaterialButton btnEdit, btnHapus;

    private byte[] posterPath;

    private static final int REQUEST_CODE_SELECT_IMAGE = 1;

    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Button lihatkodesurat;
    private Animation fadeIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_event_diajukan2);

        dataId = getIntent().getStringExtra(ID);
        imgBack = findViewById(R.id.statusback);
        inpPengirim = findViewById(R.id.et_namapengirimevent);
        inpNamaEvent = findViewById(R.id.et_namaeventevent);
        inpTempat = findViewById(R.id.et_alamateventevent);
        inpDeskripsi = findViewById(R.id.et_deskripsievent);
        inpLink = findViewById(R.id.et_linkpendaftaranevent);
        inpTglAkhir = findViewById(R.id.et_tanggalakhirevent);
        inpTglAwal = findViewById(R.id.et_tanggalawalevent);
        inpPilihFile = findViewById(R.id.pilihfile);
        btnEdit = findViewById(R.id.editdiajukan);
        btnHapus = findViewById(R.id.batalkandiajukan);


        ProgressDialog progressDialog = new ProgressDialog(FormEventDiajukan.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        RetroServer.getConnection().create(APIRequestData.class).getModelDetailEvent(dataId)
                .enqueue(new Callback<ResponseDetailEvent>() {
                    @Override
                    public void onResponse(Call<ResponseDetailEvent> call, Response<ResponseDetailEvent> response) {
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                            ModelDetailEvent model = response.body().getData();
                            inpPengirim.setText(model.getNama_pengirim());
                            inpNamaEvent.setText(model.getNama_event());
                            inpTempat.setText(model.getTempat_event());
                            inpDeskripsi.setText(model.getDeskripsi());
                            inpLink.setText(model.getLink_pendaftaran());
                            inpTglAwal.setText(model.getTanggal_awal());
                            inpTglAkhir.setText(model.getTanggal_akhir());
                            inpPilihFile.setText(model.getPoster_event());

                        } else {

                            Toast.makeText(FormEventDiajukan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDetailEvent> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(FormEventDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

        imgBack.setOnClickListener(v -> {
            onBackPressed();
        });
        inpTglAwal.setOnClickListener(new View.OnClickListener() {

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
                picker = new DatePickerDialog(FormEventDiajukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            inpTglAwal.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });
        inpTglAkhir.setOnClickListener(new View.OnClickListener() {
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
                picker = new DatePickerDialog(FormEventDiajukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        Calendar selectedDate = Calendar.getInstance();
                        selectedDate.set(year, month, dayOfMonth);
                        if (selectedDate.after(cldr)) {
                            inpTglAkhir.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                        } else {
                            Toast.makeText(getApplicationContext(), "Pilih tanggal setelah 5 hari dari hari ini", Toast.LENGTH_SHORT).show();
                        }
                    }
                }, tahun, bulan, tgl);

                picker.getDatePicker().setMinDate(cldr.getTimeInMillis());
                picker.show();
            }
        });


        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupDialog.getInstance(FormEventDiajukan.this)
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

//            Toast.makeText(this, dataId, Toast.LENGTH_SHORT).show();

                                String namaPengirim = inpPengirim.getText().toString(),
                                        namaEvent = inpNamaEvent.getText().toString(),
                                        tempat = inpTempat.getText().toString(),
                                        tglAwal = inpTglAwal.getText().toString(),
                                        tglAkhir = inpTglAkhir.getText().toString(),
                                        deskripsi = inpDeskripsi.getText().toString(),
                                        link = inpLink.getText().toString();


                                if (TextUtils.isEmpty(namaPengirim)) {
                                    inpPengirim.setError("Nama Pengirim Harus Diisi!");
                                    inpPengirim.requestFocus();
                                } else if (namaEvent.length() <= 10) {
                                    inpNamaEvent.setError("Nama Event Harus Lebih dari 10!");
                                    inpNamaEvent.requestFocus();
                                } else if (namaEvent.length() >= 75) {
                                    inpNamaEvent.setError("Nama Event Harus Kurang dari 75 karakter!");
                                    inpNamaEvent.requestFocus();
                                } else if (TextUtils.isEmpty(tempat)) {
                                    inpNamaEvent.setError("Nama Event Harus Diisi!");
                                    inpNamaEvent.requestFocus();
                                } else if (tempat.length() <= 5) {
                                    inpTempat.setError("Tempat Event Tempat Event Harus Lebih Dari 5 Karakter!");
                                    inpTempat.requestFocus();
                                } else if (tempat.length() >= 150) {
                                    inpTempat.setError("Tempat Event Tempat Event Harus Kurang Dari 150 Karakter!");
                                    inpTempat.requestFocus();
                                } else if (TextUtils.isEmpty(tempat)) {
                                    inpTempat.setError("Tempat Event Harus Diisi!");
                                    inpTempat.requestFocus();
                                } else if (TextUtils.isEmpty(tglAwal)) {
                                    inpTglAwal.setError("Tanggal Awal Event Harus Diisi!");
                                    inpTglAwal.requestFocus();
                                } else if (TextUtils.isEmpty(tglAkhir)) {
                                    inpTglAkhir.setError("Tanggal Akhir Event Harus Diisi!");
                                    inpTglAkhir.requestFocus();
                                } else if (deskripsi.length() <= 75) {
                                    inpDeskripsi.setError("Deskripsi Event Harus Lebih Dari 75 Karakter!");
                                    inpDeskripsi.requestFocus();
                                } else if (deskripsi.length() >= 360) {
                                    inpDeskripsi.setError("Deskripsi Event Harus Kurang Dari 360 Karakter!");
                                    inpDeskripsi.requestFocus();
                                } else if (TextUtils.isEmpty(deskripsi)) {
                                    inpDeskripsi.setError("Deskripsi Event Harus Diisi!");
                                    inpDeskripsi.requestFocus();
                                } else {


                                    File uploadposter = new File(inpPilihFile.getText().toString());
                                    MultipartBody.Part poster;

                                    if (posterPath != null) {
                                        RequestBody requestposter = RequestBody.create(MediaType.parse("multipart/form-data"), posterPath);
                                        poster = MultipartBody.Part.createFormData("poster_event", uploadposter.getName(), requestposter);
                                    } else {
//                                        Toast.makeText(this, "Masukkan Ulang Foto", Toast.LENGTH_SHORT).show();
                                        poster = null;
                                    }

//            Toast.makeText(this, "Data Id : " + dataId, Toast.LENGTH_SHORT).show();

                                    RetroServer.getInstance().ajukanUlangEvent(
                                            dataId, namaEvent, deskripsi, tempat, tglAwal, tglAkhir, link, poster
                                    ).enqueue(new Callback<EditEventResponse>() {
                                        @Override
                                        public void onResponse(Call<EditEventResponse> call, Response<EditEventResponse> response) {

                                            if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                                                Toast.makeText(FormEventDiajukan.this, "DATA BERHASIL DI EDIT", Toast.LENGTH_SHORT).show();
                                                progressDialog.dismiss();
                                            } else {
                                                Toast.makeText(FormEventDiajukan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                                Log.e("DEBUG", response.body().getMessage());
                                                progressDialog.dismiss();
                                            }

                                        }

                                        @Override
                                        public void onFailure(Call<EditEventResponse> call, Throwable t) {

                                            t.printStackTrace();
                                            Toast.makeText(FormEventDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }

                                    });
                                }
                            }

                            @Override
                            public void onNegativeClicked(Dialog dialog) {
                                super.onNegativeClicked(dialog);
                                dialog.dismiss();

                            }
                        });
            }
        });

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
        inpPengirim.setFilters(new InputFilter[]

                {
                        filter
                });
        inpNamaEvent.setFilters(new InputFilter[]

                {
                        filter
                });
        inpTempat.setFilters(new InputFilter[]

                {
                        filter
                });
        inpDeskripsi.setFilters(new InputFilter[]

                {
                        filter
                });


        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupDialog.getInstance(FormEventDiajukan.this)
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

                                RetroServer.getInstance().deleteEvent(dataId).enqueue(new Callback<ResponseDetailEvent>() {
                                    @Override
                                    public void onResponse(Call<ResponseDetailEvent> call, Response<ResponseDetailEvent> response) {
                                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                                            Toast.makeText(FormEventDiajukan.this, "Event Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        } else {
                                            Toast.makeText(FormEventDiajukan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                                            progressDialog.dismiss();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ResponseDetailEvent> call, Throwable t) {
                                        Toast.makeText(FormEventDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                                        progressDialog.dismiss();
                                    }

                                });
                            }
                            @Override
                            public void onNegativeClicked (Dialog dialog){
                                super.onNegativeClicked(dialog);
                                dialog.dismiss();
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


    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());
            inpPilihFile.setText(selectedFileName);
            posterPath = uriToByteArray(this, data.getData());
        }
    }

}