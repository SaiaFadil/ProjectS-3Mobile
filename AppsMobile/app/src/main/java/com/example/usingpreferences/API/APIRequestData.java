package com.example.usingpreferences.API;

import com.example.usingpreferences.DataModel.CekEmailModel;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.DataModel.VerifyResponse;

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
    @POST("login_google.php")
    Call<ResponseModelUsers> google_login(
            @Field("email") String email
    );
    @FormUrlEncoded
    @POST("Register.php")
    Call<ResponseModelUsers> register(
            @Field("nama_lengkap") String namaLengkap,
            @Field("no_telpon") String noTelpon,
            @Field("email") String email,
            @Field("password") String password
    );



    @FormUrlEncoded
    @POST("UpdateProfil.php")
    Call<ModelUpdateProfil> updateUser(@Field("id_user") String idUser,
            @Field("nama_lengkap") String namaLengkap,
            @Field("no_telpon") String noTelpon,
            @Field("jenis_kelamin") String selectedGender,
            @Field("tanggal_lahir") String tanggalLahir,
            @Field("tempat_lahir") String tempatLahir,
            @Field("email") String emailteks
    );
    @FormUrlEncoded
    @POST("updatepasswordprofil.php")
    Call<ModelUpdateProfil> updatePasswordProfil(
            @Field("id_user") String idUser,
            @Field("password_lama") String passwordlama,
            @Field("password_baru") String passwordbaru

    );
 @FormUrlEncoded
    @POST("updatepasswordlupa.php")
    Call<ModelUpdateProfil> updatePasswordLupa(
            @Field("email") String email,
            @Field("password_baru") String passwordbaru

    );
 @FormUrlEncoded
    @POST("cekemail.php")
    Call<CekEmailModel> cekemail(
            @Field("email") String email

    );

    @FormUrlEncoded
    @POST("mail.php")
    Call<VerifyResponse> sendEmail(
            @Field("email") String email,
            @Field("type") String type,
            @Field("action") String action,
            @Field("id_user") String idUser
    );


    //ntar create.php dan lain lain di tambah di bawah sini
}

