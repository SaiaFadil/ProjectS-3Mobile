package com.example.usingpreferences.API;

import com.example.usingpreferences.DataModel.CekEmailModel;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ModelResponseSimpanDataSeniman;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiproses;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiproses;
import com.example.usingpreferences.DataModel.SenimanResponse;
import com.example.usingpreferences.DataModel.VerifyResponse;
import com.example.usingpreferences.DataModel.getSingkatanResponse;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

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
    Call<ModelUpdateProfil> updateUser(
            @Field("id_user") String idUser,
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
//di bawah ini Baru
    @FormUrlEncoded
    @POST("insertSuratAdvis.php")
    Call<ModelResponseAll> sendAdvisData(
            @Field("nomor_induk") String nomorInduk,
            @Field("nama_advis") String namaAdvis,
            @Field("alamat_advis") String alamatAdvis,
            @Field("deskripsi_advis") String deskripsiAdvis,
            @Field("tgl_advis") String tglAdvis,
            @Field("tempat_advis") String tempatAdvis,
            @Field("status") String status,
            @Field("catatan") String catatan,
            @Field("id_user") String idUser,
            @Field("id_seniman") String idSeniman
    );

    @FormUrlEncoded
    @POST("SimpanDataSeniman.php")
    Call<ModelResponseSimpanDataSeniman> SimpanDataSeniman(
            @Field("id_user") String idUser
    );
 @FormUrlEncoded
    @POST("getSingkatanKategori.php")
    Call<getSingkatanResponse> getSingkatan(
            @Field("NamaKategori") String Namakategori
    );

    @GET("getKategoriSeniman.php")
    Call<List<KategoriSenimanModel>> getKategoriSeniman();

    @Multipart
    @POST("NoInduk.php")
    Call<SenimanResponse> saveDataSeniman(
            @Part("nik") String nik,
            @Part("id_user") String idUser,
            @Part("nama_seniman") String namaSeniman,
            @Part("jenis_kelamin") String jenisKel,
            @Part("kecamatan") String kecamatan,
            @Part("singkatan_kategori") String singkatanKategori,
            @Part("tempat_lahir") String tempatLahir,
            @Part("tanggal_lahir") String ttlSeniman,
            @Part("alamat_seniman") String alamat,
            @Part("no_telpon") String noTelpon,
            @Part("status") String status,
            @Part("nama_organisasi") String namaOrganisasi,
            @Part("jumlah_anggota") String jumlahAnggota,
            @Part MultipartBody.Part ktpSeniman,
            @Part MultipartBody.Part suratKeterangan,
            @Part MultipartBody.Part passFoto
    );
    @FormUrlEncoded
    @POST("status_advis/status_advis_diajukan.php")
    Call<ResponseStatusAdvisDiajukan> getStatusAdvisDiajukan(
            @Field("id_user") String id_user

    );

    @FormUrlEncoded
    @POST("status_advis/detail_advis_diajukan.php")
    Call<ResponseDetailAdvisDiajukan> getDetailAdvisDiajukan(
            @Field("id_advis") String id_advis
    );

    @FormUrlEncoded
    @POST("status_advis/status_advis_diproses.php")
    Call<ResponseStatusAdvisDiproses> getStatusAdvisDiproses(
            @Field("id_user") String id_user

    );


    @FormUrlEncoded
    @POST("status_advis/detail_advis_diproses.php")
    Call<ResponseDetailAdvisDiproses> getDetailAdvisDiproses(
            @Field("id_advis") String id_advis
    );
}

