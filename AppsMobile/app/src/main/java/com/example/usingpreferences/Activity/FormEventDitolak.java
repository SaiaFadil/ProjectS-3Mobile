package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailEvent;
import com.example.usingpreferences.DataModel.EditEventResponse;
import com.example.usingpreferences.DataModel.ResponseDetailEvent;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.google.android.material.button.MaterialButton;

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

public class FormEventDitolak extends AppCompatActivity {

    public static String ID = "id";
    private DatePickerDialog picker;
    private byte[] posterPath;

    private String dataId;
    private static final int REQUEST_CODE_SELECT_IMAGE = 1;

    private ImageButton imgBack;
    private TextView inpPengirim, inpTglAwal, inpTglAkhir, inpPilihFile, txtStatus, txtStatusBawah, txtFilename;
    private EditText inpNamaEvent, inpTempat, inpDeskripsi, inpLink;

    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        dataId = getIntent().getStringExtra(ID);

        setContentView(R.layout.activity_form_event_ditolak);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

        inpPengirim = findViewById(R.id.et_namapengirimevent);
        inpNamaEvent = findViewById(R.id.et_namaeventevent);
        inpTempat = findViewById(R.id.et_alamateventevent);
        inpDeskripsi = findViewById(R.id.et_deskripsievent);
        inpLink = findViewById(R.id.et_linkpendaftaranevent);
        inpTglAkhir = findViewById(R.id.et_tanggalakhirevent);
        inpTglAwal = findViewById(R.id.et_tanggalawalevent);
        inpPilihFile = findViewById(R.id.pilihfile);


        inpTglAwal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);
//                picker.setContextCompat.getColor(this, R.color.greendark));

                picker = new DatePickerDialog(FormEventDitolak.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        inpTglAwal.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, tahun, bulan, tgl);
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

                picker = new DatePickerDialog(FormEventDitolak.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        inpTglAkhir.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, tahun, bulan, tgl);
                picker.show();
            }
        });
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

                            Toast.makeText(FormEventDitolak.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDetailEvent> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(FormEventDitolak.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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

        MaterialButton btn = findViewById(R.id.ajukanulang_event);
        btn.setOnClickListener(v -> {

            Toast.makeText(this, dataId, Toast.LENGTH_SHORT).show();

            String namaPengirim = inpPengirim.getText().toString(),
                    namaEvent = inpNamaEvent.getText().toString(),
                    tempat = inpTempat.getText().toString(),
                    tglAwal = inpTglAwal.getText().toString(),
                    tglAkhir = inpTglAkhir.getText().toString(),
                    deskripsi = inpDeskripsi.getText().toString(),
                    link = inpLink.getText().toString();

            File uploadposter = new File(inpPilihFile.getText().toString());
            MultipartBody.Part poster;

            if (posterPath != null) {
                RequestBody requestposter = RequestBody.create(MediaType.parse("multipart/form-data"), posterPath);
                poster = MultipartBody.Part.createFormData("poster_event", uploadposter.getName(), requestposter);
            } else {
                Toast.makeText(this, "Masukkan Ulang Foto", Toast.LENGTH_SHORT).show();
                poster = null;
            }

//            Toast.makeText(this, "Data Id : " + dataId, Toast.LENGTH_SHORT).show();

            RetroServer.getInstance().ajukanUlangEvent(
                    dataId, namaEvent, deskripsi, tempat, tglAwal, tglAkhir, link, poster
            ).enqueue(new Callback<EditEventResponse>() {
                @Override
                public void onResponse(Call<EditEventResponse> call, Response<EditEventResponse> response) {

                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                        Toast.makeText(FormEventDitolak.this, "DATA BERHASIL DI EDIT", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(FormEventDitolak.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("DEBUG", response.body().getMessage());
                    }

                }

                @Override
                public void onFailure(Call<EditEventResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(FormEventDitolak.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


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


    @Override
    public void onResume() {
        super.onResume();

    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
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