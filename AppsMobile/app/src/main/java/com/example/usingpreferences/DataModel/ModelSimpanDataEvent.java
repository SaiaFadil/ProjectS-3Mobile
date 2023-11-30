package com.example.usingpreferences.DataModel;


import retrofit2.http.Multipart;

public class ModelSimpanDataEvent {
    private String id_event,nama_event, deskripsi, kategori, tempat_event, tanggal_awal, tanggal_akhir, link_pendaftaran, nama_pengirim;
    private Multipart poster_event;
    public String getId_event() {
        return id_event;
    }

    public void setId_event(String id_event) {
        this.id_event = id_event;
    }

    public String getNama_event() {
        return nama_event;
    }

    public void setNama_event(String nama_event) {
        this.nama_event = nama_event;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }
    public String getKategorit() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
    public String getTempat_event() {
        return tempat_event;
    }

    public void setTempat_event(String tempat_event) {
        this.tempat_event = tempat_event;
    }
    public String getTanggal_awal() {
        return tanggal_awal;
    }

    public void setTanggal_awal(String tanggal_awal) {
        this.tanggal_awal= tanggal_awal;
    }
    public String getTanggal_akhir() {
        return tanggal_akhir;
    }

    public void setTanggal_akhir(String tanggal_akhir) {
        this.tanggal_akhir = tanggal_akhir;
    }
    public String getLink_pendaftaran() {
        return link_pendaftaran;
    }

    public void setLink_pendaftaran(String link_pendaftaran) {
        this.link_pendaftaran = link_pendaftaran;
    }
    public Multipart getPoster_event() {
        return poster_event;
    }

    public void setPoster_event(Multipart poster_event) {
        this.poster_event = poster_event;
    }

    public String getNama_pengirim() {
        return nama_pengirim;
    }

    public void setNama_pengirim(String namaPengirim) { this.nama_pengirim = namaPengirim;
    }
//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) { this.status = status;
//    }
//
//    public String getCatatan() {
//        return catatan;
//    }
//
//    public void setCatatan(String catatan) { this.status = status;
//    }

}


