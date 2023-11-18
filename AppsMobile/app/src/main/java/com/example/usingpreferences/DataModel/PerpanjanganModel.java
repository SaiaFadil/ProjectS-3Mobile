package com.example.usingpreferences.DataModel;

import retrofit2.http.Multipart;

public class PerpanjanganModel {
 private String nik, nama_lengkap, nomor_induk, id_user, id_seniman, status;

    private Multipart ktp_seniman, surat_keterangan, pass_foto;

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNama_lengkap() {
        return nama_lengkap;
    }

    public void setNama_lengkap(String nama_lengkap) {
        this.nama_lengkap = nama_lengkap;
    }

    public String getNomor_induk() {
        return nomor_induk;
    }

    public void setNomor_induk(String nomor_induk) {
        this.nomor_induk = nomor_induk;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_seniman() {
        return id_seniman;
    }

    public void setId_seniman(String id_seniman) {
        this.id_seniman = id_seniman;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Multipart getKtp_seniman() {
        return ktp_seniman;
    }

    public void setKtp_seniman(Multipart ktp_seniman) {
        this.ktp_seniman = ktp_seniman;
    }

    public Multipart getSurat_keterangan() {
        return surat_keterangan;
    }

    public void setSurat_keterangan(Multipart surat_keterangan) {
        this.surat_keterangan = surat_keterangan;
    }

    public Multipart getPass_foto() {
        return pass_foto;
    }

    public void setPass_foto(Multipart pass_foto) {
        this.pass_foto = pass_foto;
    }

}
