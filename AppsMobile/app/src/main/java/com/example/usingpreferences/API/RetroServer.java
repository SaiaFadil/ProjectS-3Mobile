package com.example.usingpreferences.API;
import androidx.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
public class RetroServer {
<<<<<<< Updated upstream
    public static final String BASE_URL = "http://192.168.0.105/HufflePuff/DatabaseMobile/";
=======
    private static final String baseURL = "http://192.168.0.105/HufflePuff/DatabaseMobile/";
                               // http:// ip internetmu /HufflePuff/DatabaseMobile/
>>>>>>> Stashed changes

//    public static final String BASE_URL = "http://172.16.106.70/pertama/";


//    public static final String BASE_URL = "http://172.16.106.58/arenafinder/";


    public static final String SUCCESSFUL_RESPONSE = "success";


    @NonNull
    public static APIRequestData getInstance(){
        return getConnection().create(APIRequestData.class);
    }

    public static Retrofit getConnection(){

        Gson gson = new GsonBuilder().setLenient().create();
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .connectTimeout(5, TimeUnit.SECONDS)
                .writeTimeout(5,TimeUnit.SECONDS)
                .build();


        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

    }
}
