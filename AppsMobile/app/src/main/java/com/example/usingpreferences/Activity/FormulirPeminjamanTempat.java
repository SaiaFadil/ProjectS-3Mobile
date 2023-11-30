package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.DataShared;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.Activity.PinjamTempatList;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormulirPeminjamanTempat extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private CheckBox syrt;
    private DatePickerDialog picker;
    private EditText inpWaktuMulai, inpWaktuAkhir;
    private Button selectFileButton2;
    private TextView cardViewFileNameTextView1;

    private EditText inpNamaTempat, inpNamaLengkap, inpKtp, inpInstansi, inpNamaKegiatan, inpDeskripsi, inpTanggalMulai, inpTanggalAkhir, inpPeserta, inpNamaPemimjam, InpWaktuMulai;

    private MaterialButton btnPickImage;

    private DataShared dataShared;

    byte[] pathSurat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulir_peminjaman_tempat);

        dataShared = new DataShared(this);

        Toast.makeText(this, dataShared.getData(DataShared.KEY.ID_NAMA_TEMPAT), Toast.LENGTH_SHORT).show();

        inpNamaPemimjam = findViewById(R.id.et_namalengkappinjam);
        inpNamaTempat = findViewById(R.id.et_tempatpinjam);
        inpNamaKegiatan = findViewById(R.id.et_namakegiatanpinjam);
        inpKtp = findViewById(R.id.et_ktppinjam);
        inpInstansi = findViewById(R.id.et_instansipinjam);
        inpPeserta = findViewById(R.id.et_jumlahpesertapinjam);
        inpDeskripsi = findViewById(R.id.et_deskripsipinjam);
        btnPickImage = findViewById(R.id.selectFileButton2);
        inpTanggalAkhir = findViewById(R.id.et_tanggalakhirpinjam);
        inpTanggalMulai = findViewById(R.id.et_tanggalawalpinjam);
        inpWaktuMulai = findViewById(R.id.waktuawalpinjam);
        inpWaktuAkhir = findViewById(R.id.waktuakhirpinjam);
        inpWaktuMulai.setInputType(InputType.TYPE_NULL);
        inpWaktuAkhir.setInputType(InputType.TYPE_NULL);

        inpNamaTempat.setText(dataShared.getData(DataShared.KEY.NAMA_TEMPAT));
        inpTanggalMulai.setText(dataShared.getData(DataShared.KEY.TANGGAL_MULAI));

        btnPickImage.setOnClickListener(v -> {
            Toast.makeText(FormulirPeminjamanTempat.this, "test", Toast.LENGTH_SHORT).show();
            selectFile("image/*", 2);
        });

        cardViewFileNameTextView1 = findViewById(R.id.textViewButton1);

        inpWaktuMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(inpWaktuMulai);
            }
        });

        inpWaktuAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showTimePickerDialog(inpWaktuAkhir);
            }
        });


        // Ambil data tanggal dari intent
        String tanggal = getIntent().getStringExtra("tanggal_awal");
        // Cari EditText dengan ID et_tanggalawal
        inpTanggalAkhir.setInputType(InputType.TYPE_NULL);

        inpTanggalAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Dapatkan tanggal saat ini
                final Calendar calendar = Calendar.getInstance();
                int tahunSaatIni = calendar.get(Calendar.YEAR);
                int bulanSaatIni = calendar.get(Calendar.MONTH);
                int hariSaatIni = calendar.get(Calendar.DAY_OF_MONTH);

                // Buat DatePickerDialog untuk memilih tanggal akhir
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormulirPeminjamanTempat.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {
                        // Format tanggal akhir sesuai kebutuhan Anda (misalnya, "dd/MM/yyyy")
                        String tanggalAkhirFormatted = String.format(Locale.getDefault(), "%02d/%02d/%04d", tahun, bulan + 1, hari);
                        // Set teks pada EditText tanggal akhir
                        inpTanggalAkhir.setText(tanggalAkhirFormatted);
                    }
                }, tahunSaatIni, bulanSaatIni, hariSaatIni);

                // Tampilkan dialog pemilihan tanggal akhir
                datePickerDialog.show();
            }
        });



        EditText editText = findViewById(R.id.et_ktppinjam);

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

        MaterialButton btnkirimpinjam = findViewById(R.id.button_kirimpinjam);
        btnkirimpinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                File ktpSenimanFile = new File(cardViewFileNameTextView1.getText().toString());

                RequestBody requestFileKtpSeniman = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
                MultipartBody.Part ktpSenimanPart = MultipartBody.Part.createFormData("surat_ket_sewa", ktpSenimanFile.getName(), requestFileKtpSeniman);


                RetroServer.getConnection().create(APIRequestData.class)
                        .sendPinjamTempat(
                                inpNamaPemimjam.getText().toString(),
                                inpKtp.getText().toString(),
                                inpInstansi.getText().toString(),
                                inpNamaKegiatan.getText().toString(),
                                inpPeserta.getText().toString(),
                                inpNamaTempat.getText().toString(),
                                inpDeskripsi.getText().toString(),
                                inpTanggalMulai.getText().toString()+" "+inpWaktuMulai.getText().toString(),
                                inpTanggalAkhir.getText().toString()+" "+inpWaktuAkhir.getText().toString(), "diajukan",
                                inpDeskripsi.getText().toString(),
                                dataShared.getData(DataShared.KEY.ID_NAMA_TEMPAT).toString(),
                                "32",
                                ktpSenimanPart
                        ).enqueue(new Callback<ModelResponseAll>() {
                            @Override
                            public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                                if (response.body() != null && response.body().getKode() == 1) {
                                    Toast.makeText(FormulirPeminjamanTempat.this, "SUKSES", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(FormulirPeminjamanTempat.this, PengajuanBerhasilTerkirim.class);
                                    startActivity(intent);
                                } else {
                                    Toast.makeText(FormulirPeminjamanTempat.this, "GAGAL", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                                t.printStackTrace();
                            }
                        });
            }
        });
        ImageButton pinjamback = findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
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
    public void selectDocumentFile(View view) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);

        if (view.getId() == R.id.selectFileButton2) {
            startActivityForResult(
                    Intent.createChooser(intent, "Pilih Dokumen PDF"),
                    REQUEST_CODE_SELECT_PDF
            );
        }
    }
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
    private void showTimePickerDialog(final EditText editText) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        // Format waktu yang dipilih
                        String selectedTime = String.format("%02d:%02d:%02d", hourOfDay, minute,00);
                        editText.setText(selectedTime);
                    }
                }, 0, 0, true);

        timePickerDialog.show();

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


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());
            cardViewFileNameTextView1.setText(selectedFileName);
            pathSurat = uriToByteArray(this, data.getData());
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(
                new Intent(FormulirPeminjamanTempat.this, PinjamTempatList.class)
        );
    }
}
