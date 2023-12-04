package com.example.usingpreferences.API;

import com.example.usingpreferences.DataModel.CekEmailModel;
import com.example.usingpreferences.DataModel.EditEventResponse;
import com.example.usingpreferences.DataModel.EventHomeResponse;
import com.example.usingpreferences.DataModel.KategoriSenimanModel;
import com.example.usingpreferences.DataModel.ListTempatResponse;
import com.example.usingpreferences.DataModel.ModelDetailSenimanDiajukan;
import com.example.usingpreferences.DataModel.ModelResponseAll;
import com.example.usingpreferences.DataModel.ModelResponseDataSeniman;
import com.example.usingpreferences.DataModel.ModelResponseSimpanDataSeniman;
import com.example.usingpreferences.DataModel.ModelResponseSimpanEvent;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.DataModel.PerpanjanganResponse;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailAdvisDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailEvent;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailPerpanjanganDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDitolak;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDitolak;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusEvent;
import com.example.usingpreferences.DataModel.ResponseStatusPerpanjanganDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusPerpanjanganDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusPerpanjanganDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusPerpanjanganDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDitolak;
import com.example.usingpreferences.DataModel.ResponsesetKategoriOnSpinner;
import com.example.usingpreferences.DataModel.SenimanResponse;
import com.example.usingpreferences.DataModel.TanggalSewaResponse;
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
import retrofit2.http.Query;

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
    @POST("data_seniman_mobile/getSingkatanKategori.php")
    Call<getSingkatanResponse> getSingkatan(
            @Field("NamaKategori") String Namakategori

    );

    @GET("data_seniman_mobile/getKategoriSeniman.php")
    Call<List<KategoriSenimanModel>> getKategoriSeniman();

    @Multipart
    @POST("data_seniman_mobile/NoInduk.php")
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

    @FormUrlEncoded
    @POST("status_advis/status_advis_ditolak.php")
    Call<ResponseStatusAdvisDitolak> getStatusAdvisDitolak(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("status_advis/detail_advis_ditolak.php")
    Call<ResponseDetailAdvisDitolak> getDetailAdvisDitolak(
            @Field("id_advis") String id_advis
    );

    @FormUrlEncoded
    @POST("status_advis/status_advis_diterima.php")
    Call<ResponseStatusAdvisDiterima> getStatusAdvisDiterima(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("status_advis/detail_advis_diterima.php")
    Call<ResponseDetailAdvisDiterima> getDetailAdvisDiterima(
            @Field("id_advis") String id_advis
    );

    @FormUrlEncoded
    @POST("status_advis/edit_advis_diajukan.php")
    Call<ModelResponseAll> editAdvisDiajukan(
            @Field("id_advis") String id_advis,
            @Field("deskripsi_advis") String deskripsi_advis,
            @Field("tgl_advis") String tgl_advis,
            @Field("tempat_advis") String tempat_advis

    );

    @FormUrlEncoded
    @POST("status_advis/ajukanulang_advis_ditolak.php")
    Call<ModelResponseAll> ajukanulangAdvisDiajukan(
            @Field("id_advis") String id_advis,
            @Field("deskripsi_advis") String deskripsi_advis,
            @Field("tgl_advis") String tgl_advis,
            @Field("tempat_advis") String tempat_advis

    );

    @FormUrlEncoded
    @POST("status_advis/delete_advis_diajukan.php")
    Call<ModelResponseAll> hapusAdvisDiajukan(
            @Field("id_advis") String id_advis

    );

    @FormUrlEncoded
    @POST("status_advis/notifditerima.php")
    Call<ModelResponseAll> notifditerima(
            @Field("id_user") String id_user

    );
    @FormUrlEncoded
    @POST("status_advis/notifditolak.php")
    Call<ModelResponseAll> notifditolak(
            @Field("id_user") String id_user

    );
    @FormUrlEncoded
    @POST("status_advis/detail_advis_diterima.php")
    Call<ResponseDetailAdvisDiterima> getKodeSurat(
            @Field("id_advis") String id_advis
    );

    //ini seniman ya

    @Multipart
    @POST("data_seniman_mobile/insertPerpanjangan.php")
    Call<PerpanjanganResponse> saveDataSeniman(

            @Part("id_user") String idUser,
            @Part("id_seniman") String idSeniman,
            @Part("nama_lengkap") String nama_lengkap,
            @Part("nik") String nik,
            @Part("nomor_induk") String nomor_induk,
            @Part("status") String status,
            @Part MultipartBody.Part ktpSeniman,
            @Part MultipartBody.Part suratKeterangan,
            @Part MultipartBody.Part passFoto
    );
    @Multipart
    @POST("data_seniman_mobile/edit_Perpanjangan_diajukan.php")
    Call<ResponseDetailPerpanjanganDiajukan> sendPerpanjanganData(
            @Part("id_perpanjangan") String id_perpanjangan,
            @Part("status") String status,
            @Part MultipartBody.Part ktpSeniman,
            @Part MultipartBody.Part suratKeterangan,
            @Part MultipartBody.Part passFoto
    );
    @Multipart
    @POST("data_seniman_mobile/edit_Perpanjangan_ditolak.php")
    Call<ResponseDetailPerpanjanganDitolak> sendPerpanjanganDataditolak(
            @Part("id_perpanjangan") String id_perpanjangan,
            @Part("status") String status,
            @Part MultipartBody.Part ktpSeniman,
            @Part MultipartBody.Part suratKeterangan,
            @Part MultipartBody.Part passFoto
    );

    @Multipart
    @POST("data_seniman_mobile/update_pass_foto.php")
    Call<ResponseDetailSenimanDiajukan> updatePassFoto(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part passfoto
    );
    @Multipart
    @POST("data_seniman_mobile/update_surat_keterangan.php")
    Call<ResponseDetailSenimanDiajukan> updateSuratKet(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part suratKet
    );

    @Multipart
    @POST("data_seniman_mobile/update_foto_ktp.php")
    Call<ResponseDetailSenimanDiajukan> updateKtp(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part ktpSeniman
    );

    @Multipart
    @POST("data_seniman_mobile/update_pass_foto_perpanjangan.php")
    Call<ResponseDetailPerpanjanganDiajukan> updatePassFotoperpanjangan(
            @Part("id_perpanjangan") String id_perpanjangan,
            @Part MultipartBody.Part passFoto1
    );
    @Multipart
    @POST("data_seniman_mobile/update_surat_keterangan_perpanjangan.php")
    Call<ResponseDetailPerpanjanganDiajukan> updateSuratKetperpanjang(
            @Part("id_perpanjangan") String id_perpanjangan,
            @Part MultipartBody.Part suratKet1
    );
    @Multipart
    @POST("data_seniman_mobile/update_foto_ktp_perpanjangan.php")
    Call<ResponseDetailPerpanjanganDiajukan> updateKtpperpanjangan(
            @Part("id_perpanjangan") String id_perpanjangan,
            @Part MultipartBody.Part ktpSeniman1
    );

    @Multipart
    @POST("data_seniman_mobile/update_pass_foto.php")
    Call<ResponseDetailSenimanDitolak> updatePassFotoDitolak(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part ktpSeniman
    );

    @Multipart
    @POST("data_seniman_mobile/update_surat_keterangan.php")
    Call<ResponseDetailSenimanDitolak> updateSuratKetDitolak(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part suratKet
    );

    @Multipart
    @POST("data_seniman_mobile/update_foto_ktp.php")
    Call<ResponseDetailSenimanDitolak> updateKtpDitolak(
            @Part("id_seniman") String idSeniman,
            @Part MultipartBody.Part ktpSeniman
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/setKategoriOnSpinner.php")
    Call<ResponsesetKategoriOnSpinner> setKategoriOnSpinner(
            @Field("id_seniman") String id_seniman
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Perpanjangan_diajukan.php")
    Call<ResponseStatusPerpanjanganDiajukan> getStatusPerpanjanganDiajukan(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Perpanjangan_diproses.php")
    Call<ResponseStatusPerpanjanganDiproses> getStatusPerpanjanganDiproses(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Perpanjangan_diterima.php")
    Call<ResponseStatusPerpanjanganDiterima> getStatusPerpanjanganDiterima(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Perpanjangan_ditolak.php")
    Call<ResponseStatusPerpanjanganDitolak> getStatusPerpanjanganDitolak(
            @Field("id_user") String id_user
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Seniman_diajukan.php")
    Call<ResponseStatusSenimanDiajukan> getStatusSenimanDiajukan(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Perpanjangan_diajukan.php")
    Call<ResponseDetailPerpanjanganDiajukan> getDetailPerpanjanganDiajukan(
            @Field("id_perpanjangan") String id_perpanjangan
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Perpanjangan_ditolak.php")
    Call<ResponseDetailPerpanjanganDitolak> getDetailPerpanjanganDitolak(
            @Field("id_perpanjangan") String id_perpanjangan
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Perpanjangan_diproses.php")
    Call<ResponseDetailPerpanjanganDiproses> getDetailPerpanjanganDiproses(
            @Field("id_perpanjangan") String id_perpanjangan
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Perpanjangan_diterima.php")
    Call<ResponseDetailPerpanjanganDiterima> getDetailPerpanjanganDiterima(
            @Field("id_perpanjangan") String id_perpanjangan
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Seniman_diajukan.php")
    Call<ResponseDetailSenimanDiajukan> getDetailSenimanDiajukan(
            @Field("id_Seniman") String id_Seniman
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Seniman_diproses.php")
    Call<ResponseStatusSenimanDiproses> getStatusSenimanDiproses(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Seniman_diproses.php")
    Call<ResponseDetailSenimanDiproses> getDetailSenimanDiproses(
            @Field("id_Seniman") String id_Seniman
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Seniman_ditolak.php")
    Call<ResponseStatusSenimanDitolak> getStatusSenimanDitolak(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Seniman_ditolak.php")
    Call<ResponseDetailSenimanDitolak> getDetailSenimanDitolak(
            @Field("id_Seniman") String id_Seniman
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/status_Seniman_diterima.php")
    Call<ResponseStatusSenimanDiterima> getStatusSenimanDiterima(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Seniman_diterima.php")
    Call<ResponseDetailSenimanDiterima> getDetailSenimanDiterima(
            @Field("id_Seniman") String id_Seniman
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/edit_Seniman_diajukan_without_multipartData.php")
    Call<ResponseDetailSenimanDiajukan> editSenimanDiajukanWithout(
            @Field("id_seniman") String idSeniman,
            @Field("nik") String nik,
            @Field("nama_seniman") String namaSeniman,
            @Field("jenis_kelamin") String jenisKel,
            @Field("kecamatan") String kecamatan,
            @Field("singkatan_kategori") String singkatanKategori,
            @Field("tempat_lahir") String tempatLahir,
            @Field("tanggal_lahir") String ttlSeniman,
            @Field("alamat_seniman") String alamat,
            @Field("no_telpon") String noTelpon,
            @Field("nama_organisasi") String namaOrganisasi,
            @Field("jumlah_anggota") String jumlahAnggota
    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/edit_Seniman_diajukan_without_multipartData.php")
    Call<ResponseDetailSenimanDitolak> editSenimanDitolakWithoutDitolak(
            @Field("id_seniman") String idSeniman,
            @Field("nik") String nik,
            @Field("nama_seniman") String namaSeniman,
            @Field("jenis_kelamin") String jenisKel,
            @Field("kecamatan") String kecamatan,
            @Field("singkatan_kategori") String singkatanKategori,
            @Field("tempat_lahir") String tempatLahir,
            @Field("tanggal_lahir") String ttlSeniman,
            @Field("alamat_seniman") String alamat,
            @Field("no_telpon") String noTelpon,
            @Field("nama_organisasi") String namaOrganisasi,
            @Field("jumlah_anggota") String jumlahAnggota
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/ajukanulang_Seniman_ditolak.php")
    Call<ModelResponseAll> ajukanulangSenimanDiajukan(
            @Field("id_Seniman") String id_Seniman

    );

    @FormUrlEncoded
    @POST("data_seniman_mobile/delete_Seniman_diajukan.php")
    Call<ResponseDetailSenimanDiajukan> hapusSenimanDiajukan(
            @Field("id_seniman") String id_seniman

    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/delete_Perpanjangan_diajukan.php")
    Call<ResponseDetailPerpanjanganDiajukan> hapusPerpanjanganDiajukan(
            @Field("id_perpanjangan") String id_perpanjangan
    );
    @FormUrlEncoded
    @POST("data_seniman_mobile/detail_Seniman_diterima.php")
    Call<ResponseDetailSenimanDiterima> getKodeSeniman (
            @Field("id_seniman") String id_seniman

    );
    @Multipart
    @POST("event.php")
    Call<ModelResponseSimpanEvent> event(
            @Part("id_event") String idEvent,
            @Part("nama_event") String namaEvent,
            @Part("deskripsi") String deskripsi,
            @Part("tempat_event") String tempat_event,
            @Part("tanggal_awal") String tanggalAwal,
            @Part("tanggal_akhir") String tanggalAkhir,
            @Part("link_pendaftaran") String linkPendaftaran,
            @Part MultipartBody.Part posterEvent
    );

    @FormUrlEncoded
    @POST("event2.php")
    Call<ModelResponseSimpanEvent> event2(
            @Field("nama_pengirim") String namaPengirim,
            @Field("status") String status,
//            @Field("id_detail") String idDetail,
            @Field("id_user") String idUser

    );
    @GET("status_event/detail_event.php")
    Call<ResponseDetailEvent> getModelDetailEvent(
            @Query("id_event") String idEvent

    );

    @GET("status_event/status_event.php")
    Call<ResponseStatusEvent> getStatus(
            @Query("id_user") String idUser
    );

    @Multipart
    @POST("status_event/ajukanulang_event_ditolak.php")
    Call<EditEventResponse> ajukanUlangEvent(
            @Part("id_event") String idEvent,
            @Part("nama_event") String namaEvent,
            @Part("deskripsi") String deskripsi,
            @Part("tempat_event") String tempat_event,
            @Part("tanggal_awal") String tanggalAwal,
            @Part("tanggal_akhir") String tanggalAkhir,
            @Part("link_pendaftaran") String linkPendaftaran,
            @Part MultipartBody.Part posterEvent
    );

    @FormUrlEncoded
    @POST("status_event/delete_event_diajukan.php")
    Call<ResponseDetailEvent> deleteEvent(
            @Field("id_event") String idEvent
    );
    @Multipart
    @POST("pinjam_tempat.php")
    Call<ModelResponseAll> sendPinjamTempat(
            @Part("nama_peminjam") String namalengkappinjam,
            @Part("nik_sewa") String et_ktppinjam,
            @Part("instansi") String et_instansipinjam,
            @Part("nama_kegiatan_sewa") String et_namakegiatanpinjam,
            @Part("jumlah_peserta") String et_jumlahpesertapinjam,
            @Part("nama_tempat") String et_tempatpinjam,
            @Part("deskripsi_sewa_tempat") String et_deskripsipinjam,
            @Part("tgl_awal_peminjaman") String et_tanggalawalpinjam,
            @Part("tgl_akhir_peminjaman") String et_tanggalakhirpinjam,
            @Part("status") String status,
            @Part("catatan") String catatan,
            @Part("id_tempat") String idTempat,
            @Part("id_user") String idUser,
            @Part MultipartBody.Part photo
    );

    @FormUrlEncoded
    @POST("Tempat/deletee_Pinjam_diajukan.php")
    Call<ModelResponseAll> hapusData(
            @Field("id_sewa") String idSewa
    );

    @GET("get_list_tempat.php")
    Call<ListTempatResponse> lisTempatttt();

    @GET("get_tanggal.php")
    Call<TanggalSewaResponse> tanggalSewa(
            @Query("id_tempat") String idTempat
    );

    @FormUrlEncoded
    @POST("Tempat/status_Pinjam_diajukan.php")
    Call<ResponseStatusPinjamDiajukan> getStatusPinjamDiajukan(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("Tempat/detail_Pinjam_diajukan.php")
    Call<ResponseDetailPinjamDiajukan> getDetailPinjamDianjukan(
            @Field("id_sewa") String id_sewa
    );

    @FormUrlEncoded
    @POST("Tempat/status_Pinjam_diproses.php")
    Call<ResponseStatusPinjamDiproses> getStatusPinjamDiproses(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("Tempat/detail_Pinjam_diproses.php")
    Call<ResponseDetailPinjamDiproses> getDetailPinjamDiproses(
            @Field("id_sewa") String id_sewa
    );

    @FormUrlEncoded
    @POST("Tempat/status_Pinjam_diterima.php")
    Call<ResponseStatusPinjamDiterima> getStatusPinjamDiterima(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("status_Pinjam/detail_Pinjam_diterima.php")
    Call<ResponseDetailPinjamDiterima> getDetailPinjamDiterima(
            @Field("id_sewa") String id_sewa
    );

    @FormUrlEncoded
    @POST("Tempat/status_Pinjam_ditolak.php")
    Call<ResponseStatusPinjamDitolak> getStatusPinjamDitolak(
            @Field("id_user") String id_user
    );

    @FormUrlEncoded
    @POST("Tempat/detail_Pinjam_ditolak.php")
    Call<ResponseDetailPinjamDitolak> getDetailPinjamDitolak(
            @Field("id_sewa") String id_sewa
    );


    @FormUrlEncoded
    @POST("Tempat/edit_Pinjam_diajukan.php")
    Call<ModelResponseAll> editPinjamDiajukan(
            @Field("id_sewa") String id_sewa,
            @Field("deskripsi_sewa_tempat") String deskripsi_sewa_tempat,
            @Field("tgl_awal_peminjaman") String tgl_awal_peminjaman,
            @Field("nama_tempat") String nama_tempat

    );

    @FormUrlEncoded
    @POST("Tempat/ajukanulang_Pinjam_ditolak.php")
    Call<ModelResponseAll> ajukanulangPinjamDiajukan(
            @Field("id_sewa") String id_sewa,
            @Field("deskripsi_sewa_tempat") String deskripsi_sewa_tempat,
            @Field("tgl_awal_peminjaman") String tgl_awal_peminjaman,
            @Field("nama_tempat") String nama_tempat

    );

    @FormUrlEncoded
    @POST("Tempat/deletee_Pinjam_diajukan.php")
    Call<ResponseDetailPinjamDiajukan> hapusPinjamDiajukan(
            @Field("id_sewa") String id_sewa
    );
    @FormUrlEncoded
    @POST("Tempat/deletee_Pinjam_diajukan.php")
    Call<ResponseDetailPinjamDitolak> hapusPinjamDitolak(
            @Field("id_sewa") String id_sewa
    );
    @FormUrlEncoded
    @POST("Tempat/notifditerima_pinjam.php")
    Call<ModelResponseAll> notifditerima_pinjam(
            @Field("id_user") String id_user

    );

    @FormUrlEncoded
    @POST("Tempat/notifditolak_pinjam.php")
    Call<ModelResponseAll> notifditolak_pinjam(
            @Field("id_user") String id_user

    );
    @GET("event_home.php")
    Call<EventHomeResponse> getEventHome();

}
