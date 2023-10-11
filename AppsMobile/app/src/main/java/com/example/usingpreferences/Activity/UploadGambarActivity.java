package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.usingpreferences.UpImage.BaseResponse;
import com.example.usingpreferences.UpImage.FileUtils;
import com.example.usingpreferences.UpImage.ImageUtils;
import com.example.usingpreferences.UpImage.UploadService;
import com.example.usingpreferences.R;

import java.io.File;
import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UploadGambarActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int PICK_IMAGE = 1;
    private static final int PERMISSION_REQUEST_STORAGE = 2;

    private static final String TYPE_1 = "multipart";
    private static final String TYPE_2 = "base64";

    private ImageView imgThumb;

    private Button btnChoose;
    private Button btnUpload1;
    private Button btnUpload2;

    private UploadService uploadService;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_gambar);

        imgThumb = findViewById(R.id.img_thumb);
        btnChoose = findViewById(R.id.btn_choose);
        btnUpload1 = findViewById(R.id.btn_upload_1);
        btnUpload2 = findViewById(R.id.btn_upload_2);

        btnChoose.setOnClickListener(this);
        btnUpload1.setOnClickListener(this);
        btnUpload2.setOnClickListener(this);
    }

    private void choosePhoto() {
        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED
                && ContextCompat.checkSelfPermission(this, android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this,
                    new String[]{android.Manifest.permission.READ_EXTERNAL_STORAGE, android.Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    PERMISSION_REQUEST_STORAGE);

        } else {
            openGallery();
        }
    }

    public void openGallery() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
    }

    @Override
    public void onClick(View view) {
        if (view == btnChoose) {
            choosePhoto();
        } else if (view == btnUpload1) {
            if (uri != null) {
                File file = FileUtils.getFile(this, uri);
                uploadMultipart(file);
            } else {
                Toast.makeText(this, "You must choose the image", Toast.LENGTH_SHORT).show();
            }
        } else if (view == btnUpload2) {
            if (uri != null) {
                Bitmap bitmap = null;
                try {
                    bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String encoded = ImageUtils.bitmapToBase64String(bitmap, 100);
                uploadBase64(encoded);
            } else {
                Toast.makeText(this, "You must choose the image", Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void uploadMultipart(File file) {
        RequestBody photoBody = RequestBody.create(MediaType.parse("image/*"), file);
        MultipartBody.Part photoPart = MultipartBody.Part.createFormData("photo",
                file.getName(), photoBody);

        RequestBody action = RequestBody.create(MediaType.parse("text/plain"), TYPE_1);

        uploadService = new UploadService();
        uploadService.uploadPhotoMultipart(action, photoPart, new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                if (baseResponse != null) {
                    Toast.makeText(UploadGambarActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void uploadBase64(String imgBase64) {
        uploadService = new UploadService();
        uploadService.uploadPhotoBase64(TYPE_2, imgBase64, new Callback<BaseResponse>() {
            @Override
            public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {
                BaseResponse baseResponse = response.body();

                if (baseResponse != null) {
                    Toast.makeText(UploadGambarActivity.this, baseResponse.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<BaseResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            if (data != null) {
                uri = data.getData();

                imgThumb.setImageURI(uri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case PERMISSION_REQUEST_STORAGE: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    openGallery();
                }
                return;
            }
        }
    }
}
