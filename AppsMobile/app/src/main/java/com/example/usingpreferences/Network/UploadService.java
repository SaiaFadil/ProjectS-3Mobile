package com.example.usingpreferences.Network;

import java.util.concurrent.TimeUnit;
//import com.example.usingpreferences.BuildConfig;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class UploadService {


    private UploadInterface uploadInterface;

    public UploadService() {
        OkHttpClient.Builder okhttpBuilder = new OkHttpClient().newBuilder();
        okhttpBuilder.connectTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.writeTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.readTimeout(60, TimeUnit.SECONDS);
        okhttpBuilder.retryOnConnectionFailure(true);


            HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
            okhttpBuilder.addInterceptor(interceptor);




        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Config.BASE_URL)
                .client(okhttpBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        uploadInterface = retrofit.create(UploadInterface.class);
    }

    public void uploadPhotoMultipart(RequestBody action, MultipartBody.Part photo, Callback callback) {
        uploadInterface.uploadPhotoMultipart(action, photo).enqueue(callback);
    }

    public void uploadPhotoBase64(String action, String photo, Callback callback) {
        uploadInterface.uploadPhotoBase64(action, photo).enqueue(callback);
    }

}
