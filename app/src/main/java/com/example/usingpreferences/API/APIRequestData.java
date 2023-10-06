package com.example.usingpreferences.API;

import com.example.usingpreferences.DataModel.ResponseModelUsers;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIRequestData {
    //buat ngambil data dari API/webservice retrieve.php
    @GET("retrieve.php")
    Call<ResponseModelUsers> ardRetrieveData();

    @FormUrlEncoded
    @POST("Login.php")
    Call<ResponseModelUsers> login(
            @Field("email") String email,
            @Field("password") String password
    );


    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseModelUsers> register(
            @Field("nama_lengkap") String namaLengkap,
            @Field("no_telpon") String noTelpon,
            @Field("email") String email,
            @Field("password") String password
    );




    //ntar create.php dan lain lain di tambah di bawah sini
}

