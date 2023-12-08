package com.example.usingpreferences.Activity;

import static com.example.usingpreferences.Activity.NoInduk4.uriToByteArray;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
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
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.PerpanjanganResponse;
import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NoInduk6 extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private TextView textViewButton1, textViewButton2, textViewButton3, teks_kesalahan;
    private EditText editTextNIK, editTextNamaLengkap, editTextNoIndukLama1, editTextNoIndukLama2 , editTextNoIndukLama3, editTextNoIndukLama4;
    private Animation fadeIn, fadeout;
    private CheckBox menyetujui;
    private Drawable ic_error;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_no_induk6);

        ic_error = getResources().getDrawable(R.drawable.ic_error);
        teks_kesalahan = findViewById(R.id.teks_kesalahan);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_in_down);
        fadeout = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade_out_down);
        teks_kesalahan.setVisibility(View.INVISIBLE);
        menyetujui = findViewById(R.id.checkboxsetuju);
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
         editTextNoIndukLama1 = findViewById(R.id.editTextNoIndukLama1);
         editTextNoIndukLama2 = findViewById(R.id.editTextNoIndukLama2);
         editTextNoIndukLama3 = findViewById(R.id.editTextNoIndukLama3);
        editTextNoIndukLama4 = findViewById(R.id.editTextNoIndukLama4);

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
        InputFilter filterNIK = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[0-9]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        editTextNIK.setFilters(new InputFilter[]{filterNIK});

        int minLengthNIK = 16;  // Set your desired minimum character limit for NIK
        int maxLengthNIK = 16; // Set your desired maximum character limit for NIK
 
        editTextNIK.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String input = s.toString();

                // Check for the minimum length for NIK
                if (input.length() < minLengthNIK) {
                    editTextNIK.setError("Format NIK tidak Sesuai Ketentuan");
                } else {
                    editTextNIK.setError(null); // Clear the error if the minimum length is met
                }

                // Check for the maximum length for NIK
                if (input.length() > maxLengthNIK) {
                    editTextNIK.setText(input.substring(0, maxLengthNIK));
                    editTextNIK.setSelection(maxLengthNIK);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
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

        ImageButton kembali = findViewById(R.id.indukback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        Button btnnext = findViewById(R.id.button_lanjutinduk1);
        btnnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Animation shake = AnimationUtils.loadAnimation(NoInduk6.this, R.anim.shake_animation);

                if (TextUtils.isEmpty(editTextNIK.getText())) {
                    editTextNIK.setError("NIK belum diisi");
                    editTextNIK.startAnimation(shake);
                } else if (TextUtils.isEmpty(editTextNamaLengkap.getText())) {
                    editTextNamaLengkap.setError("Nama Lengkap belum diisi");
                    editTextNamaLengkap.startAnimation(shake);
                } else if (TextUtils.isEmpty(editTextNoIndukLama1.getText())) {
                    editTextNoIndukLama1.setError("Isi kolom terlebih dahulu");
                    editTextNoIndukLama1.startAnimation(shake);
                } else if (TextUtils.isEmpty(editTextNoIndukLama2.getText())) {
                    editTextNoIndukLama2.setError("Isi kolom terlebih dahulu");
                    editTextNoIndukLama2.startAnimation(shake);
                } else if (TextUtils.isEmpty(editTextNoIndukLama3.getText())) {
                    editTextNoIndukLama3.setError("Isi kolom terlebih dahulu");
                    editTextNoIndukLama3.startAnimation(shake);
                } else if (TextUtils.isEmpty(editTextNoIndukLama4.getText())) {
                    editTextNoIndukLama4.setError("Isi kolom terlebih dahulu");
                    editTextNoIndukLama4.startAnimation(shake);
                } else if (TextUtils.isEmpty(textViewButton1.getText())) {
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

                    AlertDialog.Builder builder = new AlertDialog.Builder(NoInduk6.this);
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

        // Add TextChangedListener to each EditText to listen for changes
        editTextNoIndukLama1.addTextChangedListener(new MyTextWatcher(editTextNoIndukLama2, 4));
        editTextNoIndukLama2.addTextChangedListener(new MyTextWatcher(editTextNoIndukLama3, 4));
        editTextNoIndukLama3.addTextChangedListener(new MyTextWatcher(editTextNoIndukLama4, 10));
        editTextNoIndukLama4.addTextChangedListener(new MyTextWatcher(null, 5));
    }

    private class MyTextWatcher implements TextWatcher {
        private EditText nextEditText;
        private int maxLength;

        MyTextWatcher(EditText nextEditText, int maxLength) {
            this.nextEditText = nextEditText;
            this.maxLength = maxLength;
        }

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            if (charSequence.length() == maxLength && nextEditText != null) {
                nextEditText.requestFocus();
            }
        }

        @Override
        public void afterTextChanged(Editable editable) {
            updateCombinedValue();
        }
    }

    private String updateCombinedValue() {
        EditText editTextNoIndukLama1 = findViewById(R.id.editTextNoIndukLama1);
        EditText editTextNoIndukLama2 = findViewById(R.id.editTextNoIndukLama2);
        EditText editTextNoIndukLama3 = findViewById(R.id.editTextNoIndukLama3);
        EditText editTextNoIndukLama4 = findViewById(R.id.editTextNoIndukLama4);

        String value1 = editTextNoIndukLama1.getText().toString();
        String value2 = editTextNoIndukLama2.getText().toString();
        String value3 = editTextNoIndukLama3.getText().toString();
        String value4 = editTextNoIndukLama4.getText().toString();

        return value1 + "/" + value2 + "/" + value3 + "/" + value4;
    }

    //Fungsi selectFile from filemanager
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
    //Fungsi selectImage from filemanager
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
    //Fungsi selectDocument from filemanager
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
                pathSurat = uriToByteArray(this, data.getData());
            } else if (requestCode == R.id.selectFileButton2) {
                textViewButton2.setText(selectedFileName);
                textViewButton2.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathKtp = uriToByteArray(this, data.getData());
            } else if (requestCode == R.id.selectFileButton3) {
                textViewButton3.setText(selectedFileName);
                textViewButton3.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                pathPasFoto = uriToByteArray(this, data.getData());
            }
        }
    }
    private void showCustomErrorDialog(String heading, String description) {
        PopupDialog.getInstance(this)
                .setStyle(Styles.FAILED)
                .setHeading(heading)
                .setDescription(description)
                .setCancelable(false)
                .showDialog(new OnDialogButtonClickListener() {
                    @Override
                    public void onDismissClicked(Dialog dialog) {
                        super.onDismissClicked(dialog);
                    }
                });
    }

    //Send Data To Server
    private void sendDataToServer() {

        Intent intent = getIntent();
        // Get id_user dari SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        SharedPreferences sharedPreferencesSeniman = NoInduk6.this.getSharedPreferences("prefDataSeniman",Context.MODE_PRIVATE);
        String id_seniman = sharedPreferencesSeniman.getString("id_seniman","");
        String nama_lengkap = editTextNamaLengkap.getText().toString().trim();
        String nik = editTextNIK.getText().toString().trim();
        String combinedValue = updateCombinedValue().trim();
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
        Call<PerpanjanganResponse> getResponse = ardData.saveDataSeniman(idUserShared, id_seniman, nama_lengkap, nik, combinedValue, "diajukan", suratKeteranganPart, ktpSenimanPart, passFotoPart);
        getResponse.enqueue(new Callback<PerpanjanganResponse>() {
            @Override
            public void onResponse(Call<PerpanjanganResponse> call, Response<PerpanjanganResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    PerpanjanganResponse perpanjanganResponse = response.body();

                    // Periksa status dari respons server
                    if ("success".equals(perpanjanganResponse.getStatus())) {
                        startActivity(new Intent(NoInduk6.this, PengajuanBerhasilTerkirim.class));
                    } else {
                        // Menampilkan pesan kesalahan dari server
                        showCustomErrorDialog("Uh-Oh", perpanjanganResponse.getMessage());
                    }
                } else {
                    // Menampilkan pesan kesalahan jika respons tidak berhasil
                    showCustomErrorDialog("Uh-Oh", "Terjadi kesalahan. Silakan coba lagi.");
                }
            }

            @Override
            public void onFailure(Call<PerpanjanganResponse> call, Throwable t) {
                // Menampilkan pesan kesalahan ketika gagal melakukan permintaan jaringan
                showCustomErrorDialog("Uh-Oh", "Gagal terhubung ke server. Silakan coba lagi.");
            }
        });
    }

}