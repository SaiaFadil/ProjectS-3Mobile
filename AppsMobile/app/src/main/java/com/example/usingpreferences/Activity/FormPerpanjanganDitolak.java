package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
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
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ModelDetailPerpanjanganDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDitolak;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.saadahmedsoft.popupdialog.PopupDialog;
import com.saadahmedsoft.popupdialog.Styles;
import com.saadahmedsoft.popupdialog.listener.OnDialogButtonClickListener;

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

public class FormPerpanjanganDitolak extends AppCompatActivity {
    private TextView textViewButton1, textViewButton2, textViewButton3, editTextNoInduk, editTextNIK, editTextNamaLengkap, catatanDitolakseniman;
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private Button ajukanulang;
    private ProgressDialog progressDialog;
    public static ShimmerFrameLayout mFrameLayout;
    public LinearLayout mDataSemua;
    public static Animation fadeIn;
    private String Perubahan1 = null;
    private String Perubahan2 = null;
    private String Perubahan3 = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_perpanjangan_ditolak);
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
        editTextNIK = findViewById(R.id.editTextNIK);
        editTextNamaLengkap = findViewById(R.id.editTextNamaLengkap);
        catatanDitolakseniman = findViewById(R.id.catatanDitolakseniman);
        editTextNoInduk = findViewById(R.id.noinduk);
        textViewButton1 = findViewById(R.id.textViewButton1);
        textViewButton2 = findViewById(R.id.textViewButton2);
        textViewButton3 = findViewById(R.id.textViewButton3);
        ajukanulang = findViewById(R.id.ajukanulang);
        mDataSemua = findViewById(R.id.layoutData);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);
        showData();
        progressDialog = new ProgressDialog(FormPerpanjanganDitolak.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
        InputFilter filter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                String regex = "^[a-zA-Z0-9'. ]*";
                if (source.toString().matches(regex)) {
                    return source;
                } else {
                    return "";
                }
            }
        };
        ImageButton kembali = findViewById(R.id.statusback);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        ajukanulang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FormPerpanjanganDitolak.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_home);
                startActivity(intent);
            }
        });

    }
        private void showData () {
            mFrameLayout.startShimmer();
            mDataSemua.setVisibility(View.GONE);
            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
            Call<ResponseDetailPerpanjanganDitolak> getDetail = ardData.getDetailPerpanjanganDitolak(getIntent().getStringExtra("id_perpanjangan"));
            getDetail.enqueue(new Callback<ResponseDetailPerpanjanganDitolak>() {
                @Override
                public void onResponse(Call<ResponseDetailPerpanjanganDitolak> call, Response<ResponseDetailPerpanjanganDitolak> response) {
                    if (response.body().getKode() == 1) {
                        ModelDetailPerpanjanganDitolak ambildata = response.body().getData();
                        if (ambildata.getId_perpanjangan().isEmpty()) {
                            mFrameLayout.startShimmer();
                            mDataSemua.setVisibility(View.GONE);
                        } else {
                            mFrameLayout.setVisibility(View.GONE);
                            mFrameLayout.stopShimmer();
                            mDataSemua.setVisibility(View.VISIBLE);
                            mDataSemua.startAnimation(fadeIn);
                        }
                        catatanDitolakseniman.setText(ambildata.getCatatan());
                        editTextNIK.setText(ambildata.getNik());
                        editTextNamaLengkap.setText(ambildata.getNama_seniman());
                        editTextNoInduk.setText(ambildata.getNomor_induk());
                        textViewButton1.setText(removeUploadPath(ambildata.getSurat_keterangan()));
                        textViewButton2.setText(removeUploadPath(ambildata.getKtp_seniman()));
                        textViewButton3.setText(removeUploadPath(ambildata.getPass_foto()));
                    } else if (response.body().getKode() == 0) {
                        Toast.makeText(FormPerpanjanganDitolak.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    } else if (response.body().getKode() == 2) {
                        Toast.makeText(FormPerpanjanganDitolak.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<ResponseDetailPerpanjanganDitolak> call, Throwable t) {
                    t.printStackTrace();
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
        AlertDialog.Builder builder = new AlertDialog.Builder(FormPerpanjanganDitolak.this);
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
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
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
