package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.OpenableColumns;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.KonfirmMenu.PengajuanBerhasilTerkirim;
import com.example.usingpreferences.R;

public class NoInduk4 extends AppCompatActivity {
    private static final int REQUEST_CODE_SELECT_PDF = 1;
    private static final int REQUEST_CODE_SELECT_IMAGE = 2;
    private TextView cardViewFileNameTextView1,cardViewFileNameTextView2,cardViewFileNameTextView3 ;

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
                Intent intent = new Intent(NoInduk4.this, PengajuanBerhasilTerkirim.class);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
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



    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null) {
            String selectedFileName = getFileName(data.getData());

            if (requestCode == R.id.selectFileButton2) {
                cardViewFileNameTextView1.setText(selectedFileName);
            } else if (requestCode == R.id.selectFileButton3) {
                cardViewFileNameTextView2.setText(selectedFileName);
            } else if (requestCode == R.id.selectFileButton) {
                cardViewFileNameTextView3.setText(selectedFileName);
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
        }

        if (result == null) {
            result = uri.getLastPathSegment();
        }
        return result;
    }
}