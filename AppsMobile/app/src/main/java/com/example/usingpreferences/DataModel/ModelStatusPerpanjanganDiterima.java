package com.example.usingpreferences.DataModel;

public class ModelStatusPerpanjanganDiterima {

    private String id_perpanjangan;
    private String tgl_pembuatan;
    private String id_seniman;
    private String id_user;
    private String nama_seniman;

    public String getNama_seniman() {
        return nama_seniman;
    }

    public void setNama_seniman(String nama_seniman) {
        this.nama_seniman = nama_seniman;
    }

    public String getId_perpanjangan() {
        return id_perpanjangan;
    }

    public void setId_perpanjangan(String id_perpanjangan) {
        this.id_perpanjangan = id_perpanjangan;
    }

    public String getTgl_pembuatan() {
        return tgl_pembuatan;
    }

    public void setTgl_pembuatan(String tgl_pembuatan) {
        this.tgl_pembuatan = tgl_pembuatan;
    }

    public String getId_seniman() {
        return id_seniman;
    }

    public void setId_seniman(String id_seniman) {
        this.id_seniman = id_seniman;
    }


    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }
}
