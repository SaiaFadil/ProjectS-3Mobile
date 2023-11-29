package com.example.usingpreferences.DataModel;

public class ModelDetailPerpanjanganDiterima {
    private String id_seniman;
    private String nik;
    private String nomor_induk;
    private String nama_seniman;
    private String id_perpanjangan;

    public String getId_perpanjangan() {
        return id_perpanjangan;
    }

    public void setId_perpanjangan(String id_perpanjangan) {
        this.id_perpanjangan = id_perpanjangan;
    }



    public String getId_seniman() {
        return id_seniman;
    }

    public void setId_seniman(String id_seniman) {
        this.id_seniman = id_seniman;
    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getNomor_induk() {
        return nomor_induk;
    }

    public void setNomor_induk(String nomor_induk) {
        this.nomor_induk = nomor_induk;
    }

    public String getNama_seniman() {
        return nama_seniman;
    }

    public void setNama_seniman(String nama_seniman) {
        this.nama_seniman = nama_seniman;
    }

    public String getKtp_seniman() {
        return ktp_seniman;
    }

    public void setKtp_seniman(String ktp_seniman) {
        this.ktp_seniman = ktp_seniman;
    }

    public String getPass_foto() {
        return pass_foto;
    }

    public void setPass_foto(String pass_foto) {
        this.pass_foto = pass_foto;
    }

    public String getSurat_keterangan() {
        return surat_keterangan;
    }

    public void setSurat_keterangan(String surat_keterangan) {
        this.surat_keterangan = surat_keterangan;
    }

    public String getTgl_pembuatan() {
        return tgl_pembuatan;
    }

    public void setTgl_pembuatan(String tgl_pembuatan) {
        this.tgl_pembuatan = tgl_pembuatan;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCatatan() {
        return catatan;
    }

    public void setCatatan(String catatan) {
        this.catatan = catatan;
    }

    private String ktp_seniman;
    private String  pass_foto;
    private String surat_keterangan;
    private String tgl_pembuatan;
    private String status;
    private String catatan;
}
