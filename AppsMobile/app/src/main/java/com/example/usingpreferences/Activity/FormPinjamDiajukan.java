package com.example.usingpreferences.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatCheckBox;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.OpenableColumns;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.DataShared;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDiajukan;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDitolak;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDitolak;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPinjamDiajukan extends AppCompatActivity {
    EditText namaLengkap , noKtp , instansi , namaKegiatan , jumlahPeserta , TempatKegiatan , tglMulai , tglAkhir , waktuMulai , waktuAkhir, deskripsi ;
    TextView pathFile;
    Button edit , batal ;
    MaterialButton pickImage;
    LinearLayout mData;
    private Calendar tanggalMulaiCalendar , tanggalAkhirCalendar;
    private ShimmerFrameLayout mFrameLayout;
    private Animation fadeIn;
    String idSewa , idTempat;
    ImageButton back;
    boolean tanggalBerubah = false;
    boolean tanggalberubahakhir = false;
    byte[] pathSurat;
    private Calendar waktuMulaiSelected = null;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pinjam_diajukan);

        namaLengkap = findViewById(R.id.et_namalengkappinjam);
        noKtp = findViewById(R.id.et_ktppinjam);
        instansi = findViewById(R.id.et_instansipinjam);
        namaKegiatan = findViewById(R.id.et_namakegiatanpinjam);
        jumlahPeserta = findViewById(R.id.et_jumlahpesertapinjam);
        TempatKegiatan = findViewById(R.id.et_tempatpinjam);
        tglMulai = findViewById(R.id.inputTglAwal);
        tglAkhir = findViewById(R.id.inputTglAkhir);
        waktuMulai = findViewById(R.id.inputWaktuAwal);
        waktuAkhir = findViewById(R.id.inputWaktuAkhir);
        deskripsi = findViewById(R.id.et_deskripsipinjam);
        pathFile = findViewById(R.id.pilihfile);
        pickImage = findViewById(R.id.selectFileFormulirButton2);
        edit = findViewById(R.id.editdiajukan);
        batal = findViewById(R.id.batalkandiajukan);
        mData = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);

        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder filteredStringBuilder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetter(currentChar) || currentChar == ' ') {
                        filteredStringBuilder.append(currentChar); // Memasukkan karakter yang diizinkan
                    }
                }

                return filteredStringBuilder.toString(); // Mengembalikan karakter yang diizinkan
            }
        };

        InputFilter angkaBoleh = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                StringBuilder filteredStringBuilder = new StringBuilder();

                for (int i = start; i < end; i++) {
                    char currentChar = source.charAt(i);

                    if (Character.isLetter(currentChar) || currentChar == ' ' || Character.isDigit(currentChar)) {
                        filteredStringBuilder.append(currentChar); // Memasukkan karakter yang diizinkan
                    }
                }

                return filteredStringBuilder.toString(); // Mengembalikan karakter yang diizinkan
            }
        };




        InputFilter filterDesc = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    char inputChar = source.charAt(i);
                    if (!Character.isLetterOrDigit(inputChar) && !Character.isSpaceChar(inputChar) && inputChar != '\n') {
                        return ""; // Hanya izinkan huruf, spasi, dan Enter (newline)
                    }
                }
                return null; // Input valid
            }
        };

        namaLengkap.setFilters(new InputFilter[]{filter});
        namaKegiatan.setFilters(new InputFilter[]{filter});
        instansi.setFilters(new InputFilter[]{angkaBoleh});
        deskripsi.setFilters(new InputFilter[]{filterDesc , new InputFilter.LengthFilter(500)});

        back = findViewById(R.id.statusback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormPinjamDiajukan.super.onBackPressed();
            }
        });

        TempatKegiatan.setInputType(InputType.TYPE_NULL);
        TempatKegiatan.setClickable(true);
        TempatKegiatan.setFocusable(false);
        TempatKegiatan.setFocusableInTouchMode(false);

        tglMulai.setInputType(InputType.TYPE_NULL);
        tglMulai.setClickable(true);
        tglMulai.setFocusable(false);
        tglMulai.setFocusableInTouchMode(false);

        tglAkhir.setInputType(InputType.TYPE_NULL);
        tglAkhir.setClickable(true);
        tglAkhir.setFocusable(false);
        tglAkhir.setFocusableInTouchMode(false);

        waktuMulai.setInputType(InputType.TYPE_NULL);
        waktuMulai.setClickable(true);
        waktuMulai.setFocusable(false);
        waktuMulai.setFocusableInTouchMode(false);

        waktuAkhir.setInputType(InputType.TYPE_NULL);
        waktuAkhir.setClickable(true);
        waktuAkhir.setFocusable(false);
        waktuAkhir.setFocusableInTouchMode(false);



        namaLengkap.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String input = s.toString();
                // Check if the input contains numbers
                if (containsNumbers(input) || input.trim().isEmpty()) {
                    namaLengkap.setError("Nama lengkap harus diisi dan tidak boleh mengandung angka");
                } else {
                    namaLengkap.setError(null); // Remove error if the conditions are met
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            private boolean containsNumbers(String input) {
                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }
        });



        namaKegiatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence s, int i, int i1, int i2) {
                String input = s.toString();

                // Check if the input contains numbers
                if (containsNumbers(input) || input.trim().isEmpty()) {
                    namaKegiatan.setError("Nama Kegiatan harus diisi dan tidak boleh mengandung angka");
                } else {
                    namaKegiatan.setError(null); // Remove error if the conditions are met
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }

            private boolean containsNumbers(String input) {
                for (char c : input.toCharArray()) {
                    if (Character.isDigit(c)) {
                        return true;
                    }
                }
                return false;
            }
        });

        progressDialog = new ProgressDialog(FormPinjamDiajukan.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        showDataDiajukan();
        setWaktuMulai();
        setWaktuAkhir();


        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectFile("image/*" , 2);
            }
        });
        batal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popUp();
            }
        });

        update();

    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormPinjamDiajukan.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showDataDiajukan();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
    public  void setWaktuMulai(){
        waktuMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(waktuMulai);
            }
        });
    }

    public  void setWaktuAkhir(){
        waktuAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showTimePickerDialog(waktuAkhir );
            }
        });
    }

    public void showDataDiajukan(){
        mFrameLayout.startShimmer();
        mData.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailPinjamDiajukan> getDetail = ardData.getDetailPinjamDianjukan(getIntent().getStringExtra("id_sewa"));
        getDetail.enqueue(new Callback<ResponseDetailPinjamDiajukan>() {
            @Override
            public void onResponse(Call<ResponseDetailPinjamDiajukan> call, Response<ResponseDetailPinjamDiajukan> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailPinjamDiajukan ambildata = response.body().getData();
                    if (ambildata.getId_sewa().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mData.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mData.setVisibility(View.VISIBLE);
                        mData.startAnimation(fadeIn);
                    }

                    idSewa = ambildata.getId_sewa();
                    idTempat = ambildata.getId_tempat();
                    namaLengkap.setText(ambildata.getNama_peminjam());
                    noKtp.setText(ambildata.getNik_sewa());
                    instansi.setText(ambildata.getInstansi());
                    namaKegiatan.setText(ambildata.getNama_kegiatan_sewa());
                    jumlahPeserta.setText(ambildata.getJumlah_peserta());
                    TempatKegiatan.setText(ambildata.getNama_tempat());

                    String[] parts = ambildata.getTgl_awal_peminjaman().split(" ");
                    String tglAwal = parts[0];
                    String JamAwal = parts[1];
                    tglMulai.setText(tglAwal);
                    waktuMulai.setText(JamAwal);


                    String[] parts2 = ambildata.getTgl_akhir_peminjaman().split(" ");
                    String tglAkhirs = parts2[0];
                    String jamAkhri = parts2[1];
                    setTglMulai(tglAwal , tglAkhirs);
                    setTglAkhir(tglAwal , tglAkhirs);
                    tglAkhir.setText(tglAkhirs);
                    waktuAkhir.setText(jamAkhri);
                    deskripsi.setText(ambildata.getDeskripsi_sewa_tempat());
                    pathFile.setText(ambildata.getSurat_ket_sewa());
                    System.out.println("tanggal awal =" + ambildata.getTgl_awal_peminjaman());
                    System.out.println("ID TEMPAT = " + ambildata.getId_tempat());


                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormPinjamDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormPinjamDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPinjamDiajukan> call, Throwable t) {
                showAlertDialog();
            }
        });

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
    private void showTimePickerDialogCondition(final EditText editText, final boolean isEndTime) {
        // Tentukan waktu awal sebagai jam 0:0 jika isEndTime false atau waktu mulai jika true
        int hour = 0;
        int minute = 0;
        if (isEndTime && waktuMulaiSelected != null) {
            hour = waktuMulaiSelected.get(Calendar.HOUR_OF_DAY);
            minute = waktuMulaiSelected.get(Calendar.MINUTE);
        }
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if (isEndTime && waktuMulaiSelected != null) {
                            // Cek apakah waktu akhir setelah waktu mulai
                            Calendar waktuAkhir = Calendar.getInstance();
                            waktuAkhir.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            waktuAkhir.set(Calendar.MINUTE, minute);
                            if (waktuAkhir.before(waktuMulaiSelected)) {
                                // Tampilkan pesan error jika waktu akhir sebelum waktu mulai
                                Toast.makeText(getApplicationContext(), "Waktu akhir harus setelah waktu mulai", Toast.LENGTH_SHORT).show();
                                return;
                            }
                        }

                        String selectedTime = String.format("%02d:%02d", hourOfDay, minute);
                        editText.setText(selectedTime);

                        if (!isEndTime) {

                            if (waktuMulaiSelected == null) waktuMulaiSelected = Calendar.getInstance();
                            waktuMulaiSelected.set(Calendar.HOUR_OF_DAY, hourOfDay);
                            waktuMulaiSelected.set(Calendar.MINUTE, minute);
                        }
                    }
                }, 0, 0, true);

        timePickerDialog.show();

    }

    public void setTglMulai(String tanggalAwal , String tanggalAkhir){
        String[] parts = tanggalAwal.split("-");
        int tahunSaatIni = Integer.parseInt(parts[0]);
        int bulanSaatIni = Integer.parseInt(parts[1]) -1;
        int hariSaatIni = Integer.parseInt(parts[2]);


        String[] partsAkhir = tanggalAkhir.split("-");
        int tahunAkhir = Integer.parseInt(partsAkhir[0]);
        int bulanAkhir = Integer.parseInt(partsAkhir[1]) -1;
        int hariAkhir = Integer.parseInt(partsAkhir[2]);


        tanggalAkhirCalendar = Calendar.getInstance();
        tanggalMulaiCalendar = Calendar.getInstance();
        if(!tanggalBerubah){
            tanggalMulaiCalendar.set(tahunSaatIni, bulanSaatIni, hariSaatIni);
            tanggalAkhirCalendar.set(tahunAkhir , bulanAkhir , hariAkhir);
        }


        tglMulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Buat DatePickerDialog untuk memilih tanggal mulai
                DatePickerDialog datePickerDialog = new DatePickerDialog(FormPinjamDiajukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {

                        String tanggalMulaiFormatted = String.format(Locale.getDefault(), "%02d-%02d-%02d", tahun, bulan + 1, hari);
                        tanggalMulaiCalendar.set(tahun, bulan, hari);
                        tglMulai.setText(tanggalMulaiFormatted);


                    }
                }, tahunSaatIni, bulanSaatIni, hariSaatIni);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                final Calendar calendar = Calendar.getInstance();
                datePicker.setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
            }
        });
    }

    public void setTglAkhir(String tanggalAwal , String tanggalAkhir){
        String[] parts = tanggalAwal.split("-");
        int tahunSaatIni = Integer.parseInt(parts[0]);
        int bulanSaatIni = Integer.parseInt(parts[1]) -1;
        int hariSaatIni = Integer.parseInt(parts[2]);

        String[] partsAkhir = tanggalAkhir.split("-");
        int tahunAkhir = Integer.parseInt(partsAkhir[0]);
        int bulanAkhir = Integer.parseInt(partsAkhir[1]) -1;
        int hariAkhir = Integer.parseInt(partsAkhir[2]);
        tanggalAkhirCalendar = Calendar.getInstance();
        if(!tanggalberubahakhir){
            tanggalAkhirCalendar.set(tahunAkhir , bulanAkhir , hariAkhir);
        }

        tglAkhir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                DatePickerDialog datePickerDialog = new DatePickerDialog(FormPinjamDiajukan.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int tahun, int bulan, int hari) {

                        Calendar selectedCalendar = Calendar.getInstance();
                        selectedCalendar.set(tahun , bulan , hari);

                        if (selectedCalendar.after(tanggalMulaiCalendar)){
                            String tanggalAkhirFormatted = String.format(Locale.getDefault(), "%02d-%02d-%02d", tahun, bulan + 1, hari);
                            tanggalAkhirCalendar.set(tahun , bulan , hari);
                            tanggalberubahakhir = true;
                            tglAkhir.setText(tanggalAkhirFormatted);
                        } else {
                            Toast.makeText(FormPinjamDiajukan.this, "Tanggal Akhir", Toast.LENGTH_SHORT).show();
                        }

                    }
                }, tahunAkhir, bulanAkhir, hariAkhir);
                DatePicker datePicker = datePickerDialog.getDatePicker();
                final Calendar calendar = Calendar.getInstance();
                datePicker.setMinDate(calendar.getTimeInMillis());
                datePickerDialog.show();
                datePickerDialog.show();


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

        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());
            pathFile.setText(selectedFileName);
            pathSurat = uriToByteArray(this, data.getData());
        }
    }

    public void popUp(){
        PopupDialog.getInstance(FormPinjamDiajukan.this)
                .setStyle(Styles.IOS)
                .setHeading("Pengajuan Dibatalkan")
                .setDescription("Yakin ingin menghapus pengajuan tempat?")
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
                        String id_sewa = getIntent().getStringExtra("id_sewa");
                        Toast.makeText(FormPinjamDiajukan.this, "halo :" +id_sewa, Toast.LENGTH_SHORT).show();
                        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                        Call<ResponseDetailPinjamDitolak> getResponse = ardData.hapusPinjamDitolak(id_sewa);
                        getResponse.enqueue(new Callback<ResponseDetailPinjamDitolak>() {
                            @Override
                            public void onResponse(Call<ResponseDetailPinjamDitolak> call, Response<ResponseDetailPinjamDitolak> response) {
                                if (response.body().getKode() == 1) {
                                    new Handler().postDelayed(new Runnable() {
                                        @Override
                                        public void run() {
                                            Intent intent = new Intent(FormPinjamDiajukan.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
                                            startActivity(intent);
                                            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                        }
                                    }, 3000);
                                } else {
                                    System.out.println(response.body().getPesan());
                                }
                            }

                            @Override
                            public void onFailure(Call<ResponseDetailPinjamDitolak> call, Throwable t) {
                                progressDialog.dismiss();
                                showAlertDialog();
                            }
                        });

                    }

                    @Override
                    public void onNegativeClicked(Dialog dialog) {
                        super.onNegativeClicked(dialog);
                        progressDialog.dismiss();
                        dialog.dismiss();
                    }
                });
    }

    public void update(){
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String checkWaktuMulai = waktuMulai.getText().toString().trim();
                String checkWaktuBerakhir = waktuAkhir.getText().toString().trim();
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");
                Date checkMulai , checkAkhir;

//                try {
//                    checkMulai = format.parse(checkWaktuMulai);
//                    checkAkhir = format.parse(checkWaktuBerakhir);
//                } catch (ParseException e) {
//                    throw new RuntimeException(e);
//                }
//                if (checkMulai.after(checkAkhir)){
//                    Toast.makeText(FormPinjamDiajukan.this, "harap waktu selesai setelah waktu mulai", Toast.LENGTH_SHORT).show();
//
//                }
                //Condition Waktu


                if(tanggalMulaiCalendar.after(tanggalAkhirCalendar)){
                    Toast.makeText(FormPinjamDiajukan.this, "tanggal", Toast.LENGTH_SHORT).show();
                } else if(noKtp.length() != 16){
                    Toast.makeText(FormPinjamDiajukan.this, "harap masukan 16 nomor NIK!", Toast.LENGTH_SHORT).show();
                } else if(pathSurat == null){
                    Toast.makeText(FormPinjamDiajukan.this, "harap masukan gambar ulang", Toast.LENGTH_SHORT).show();
                } else {
                    SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    String idUserShared = sharedPreferences.getString("id_user", "").trim();
                    String namaPeminjam = namaLengkap.getText().toString();
                    String nikSewa = noKtp.getText().toString();
                    String namaTempat = TempatKegiatan.getText().toString();
                    String deskripsitempat = deskripsi.getText().toString();
                    String getnamaKegiatan =  namaKegiatan.getText().toString();
                    String getJumlah = jumlahPeserta.getText().toString();
                    String getInstansi = instansi.getText().toString();
                    String getTanggalAwal = tglMulai.getText().toString() + " " + waktuMulai.getText().toString();
                    String getTanggalAkhir = tglAkhir.getText().toString() + " " + waktuAkhir.getText().toString();
                    String status = "diajukan";
                    String getCatatan = "";
                    String getIdTempat = idTempat;
                    String getIdSewa = idSewa;

                    if(TextUtils.isEmpty(namaPeminjam)){
                        namaLengkap.setError("nama harus diisi");
                        namaLengkap.requestFocus();
                        return;
                    }
                    if(TextUtils.isEmpty(nikSewa)){
                        noKtp.setError("nik harus diisi");
                        noKtp.requestFocus();
                        return;
                    }

                    if(TextUtils.isEmpty(namaTempat)){
                        TempatKegiatan.setError("tempat harus diisi");
                        TempatKegiatan.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(deskripsitempat)) {
                        deskripsi.setError("Deskripsi tempat harus diisi");
                        deskripsi.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(getnamaKegiatan)) {
                        namaKegiatan.setError("Nama kegiatan harus diisi");
                        namaKegiatan.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(getJumlah)) {
                        jumlahPeserta.setError("Jumlah peserta harus diisi");
                        jumlahPeserta.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(getInstansi)) {
                        instansi.setError("Instansi harus diisi");
                        instansi.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(getTanggalAwal)) {
                        tglMulai.setError("Tanggal dan waktu mulai harus diisi");
                        tglMulai.requestFocus();
                        return;
                    }

                    if (TextUtils.isEmpty(getTanggalAkhir)) {
                        tglAkhir.setError("Tanggal dan waktu akhir harus diisi");
                        tglAkhir.requestFocus();
                        return;
                    }

                    String filePath = pathFile.getText().toString().trim();
                    File ktpSenimanFile = new File(filePath);
                    RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), pathSurat);
                    MultipartBody.Part kirimReq = MultipartBody.Part.createFormData("surat_ket_sewa", ktpSenimanFile.getName(), requestBody);

                    RequestBody requestBodyIdUser = RequestBody.create(MediaType.parse("multipart/form-data"), idUserShared);
                    RequestBody requestBodyNamaPeminjam = RequestBody.create(MediaType.parse("multipart/form-data"), namaPeminjam);
                    RequestBody requestBodyNikSewa = RequestBody.create(MediaType.parse("multipart/form-data"), nikSewa);
                    RequestBody requestBodyNamaTempat = RequestBody.create(MediaType.parse("multipart/form-data"), namaTempat);
                    RequestBody requestBodyDeskripsiTempat = RequestBody.create(MediaType.parse("multipart/form-data"), deskripsitempat);
                    RequestBody requestBodyNamaKegiatan = RequestBody.create(MediaType.parse("multipart/form-data"), getnamaKegiatan);
                    RequestBody requestBodyJumlahPeserta = RequestBody.create(MediaType.parse("multipart/form-data"), getJumlah);
                    RequestBody requestBodyInstansi = RequestBody.create(MediaType.parse("multipart/form-data"), getInstansi);
                    RequestBody requestBodyTanggalAwal = RequestBody.create(MediaType.parse("multipart/form-data"), getTanggalAwal);
                    RequestBody requestBodyTanggalAkhir = RequestBody.create(MediaType.parse("multipart/form-data"), getTanggalAkhir);
                    RequestBody requestBodyStatus = RequestBody.create(MediaType.parse("multipart/form-data"), status);
                    RequestBody requestBodyCatatan = RequestBody.create(MediaType.parse("multipart/form-data"), getCatatan);
                    RequestBody requestBodyIdTempat = RequestBody.create(MediaType.parse("multipart/form-data"), getIdTempat);
                    RequestBody requestBodyIdSewa = RequestBody.create(MediaType.parse("multipart/form-data"), getIdSewa);

                    progressDialog.show();
                    APIRequestData data = RetroServer.getConnection().create(APIRequestData.class);
                    Call<ModelResponseAll> getResponse = data.editPinjamDiajukan(requestBodyNamaPeminjam , requestBodyNikSewa , requestBodyNamaTempat , requestBodyDeskripsiTempat , requestBodyNamaKegiatan , requestBodyJumlahPeserta , requestBodyInstansi , requestBodyTanggalAwal , requestBodyTanggalAkhir , requestBodyStatus , requestBodyCatatan , requestBodyIdTempat , requestBodyIdUser , requestBodyIdSewa , kirimReq);
                    getResponse.enqueue(new Callback<ModelResponseAll>() {
                        @Override
                        public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
                            if (response.body() != null && response.body().getKode() == 1) {
                                Toast.makeText(FormPinjamDiajukan.this, "SUKSES", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(FormPinjamDiajukan.this, PengajuanBerhasilTerkirim.class);
                                startActivity(intent);
                            } else {
                                progressDialog.dismiss();
                                Toast.makeText(FormPinjamDiajukan.this, "GAGAL", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ModelResponseAll> call, Throwable t) {
                            t.printStackTrace();

                        }
                    });


                }
            }
        });
    }

}