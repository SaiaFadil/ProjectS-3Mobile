package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EventHomeModel {

    @Expose
    @SerializedName("")
    private int idEvent;

    @Expose
    @SerializedName("nama_event")
    private String namaEvent;

    @Expose
    @SerializedName("tanggal_awal")
    private String tanggal;

    @Expose
    @SerializedName("tempat_event")
    private String alamat;

    @Expose
    @SerializedName("poster_event")
    private String imgEvent;

    public EventHomeModel(int idEvent, String namaEvent, String tanggal, String alamat, String imgEvent) {
        this.idEvent = idEvent;
        this.namaEvent = namaEvent;
        this.tanggal = tanggal;
        this.alamat = alamat;
        this.imgEvent = imgEvent;
    }

    public int getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(int idEvent) {
        this.idEvent = idEvent;
    }

    public String getNamaEvent() {
        return namaEvent;
    }

    public void setNamaEvent(String namaEvent) {
        this.namaEvent = namaEvent;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getImgEvent() {
        return imgEvent;
    }

    public void setImgEvent(String imgEvent) {
        this.imgEvent = imgEvent;
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }
}
