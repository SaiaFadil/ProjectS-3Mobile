package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
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
        Toast.makeText(this, "id : "+dataId , Toast.LENGTH_SHORT).show();
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

//        cardView = findViewById(R.id.btn_card_status);
//        cardTop = findViewById(R.id.card_top);
//        txtStatus = findViewById(R.id.card_status);
//        imageView = findViewById(R.id.gambar_diterima);
//        txtStatusBawah = findViewById(R.id.text_status_diterima);

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

                picker = new DatePickerDialog(FormEventDiajukan.this, new DatePickerDialog.OnDateSetListener() {
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

                picker = new DatePickerDialog(FormEventDiajukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        inpTglAkhir.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, tahun, bulan, tgl);
                picker.show();
            }
        });
        btnEdit.setOnClickListener(v -> {
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

            Toast.makeText(this, "Data Id : " + dataId, Toast.LENGTH_SHORT).show();

            RetroServer.getInstance().ajukanUlangEvent(
                    dataId, namaEvent, deskripsi, tempat, tglAwal, tglAkhir, link, poster
            ).enqueue(new Callback<EditEventResponse>() {
                @Override
                public void onResponse(Call<EditEventResponse> call, Response<EditEventResponse> response) {

                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                        Toast.makeText(FormEventDiajukan.this, "DATA BERHASIL DI EDIT", Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(FormEventDiajukan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        Log.e("DEBUG", response.body().getMessage());
                    }

                }

                @Override
                public void onFailure(Call<EditEventResponse> call, Throwable t) {
                    t.printStackTrace();
                    Toast.makeText(FormEventDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        });

        btnHapus.setOnClickListener(v -> {

            RetroServer.getInstance().deleteEvent(dataId).enqueue(new Callback<ResponseDetailEvent>() {
                @Override
                public void onResponse(Call<ResponseDetailEvent> call, Response<ResponseDetailEvent> response) {
                    if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){
                        Toast.makeText(FormEventDiajukan.this, "Event Berhasil Dihapus", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(FormEventDiajukan.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseDetailEvent> call, Throwable t) {
                    Toast.makeText(FormEventDiajukan.this, t.getMessage(), Toast.LENGTH_SHORT).show();
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
