package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ModelStatusEvent {

    @Expose
    @SerializedName("id_event")
    private String idEvent;

    @Expose
    @SerializedName("nama_pengirim")
    private String namaPengirim;

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("tanggal_awal")
    private String tglAwal;

    public ModelStatusEvent(String idEvent, String namaPengirim, String status, String tglAwal) {
        this.idEvent = idEvent;
        this.namaPengirim = namaPengirim;
        this.status = status;
        this.tglAwal = tglAwal;
    }

    public String getIdEvent() {
        return idEvent;
    }

    public void setIdEvent(String idEvent) {
        this.idEvent = idEvent;
    }

    public String getNamaPengirim() {
        return namaPengirim;
    }

    public void setNamaPengirim(String namaPengirim) {
        this.namaPengirim = namaPengirim;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getTglAwal() {
        return tglAwal;
    }

    public void setTglAwal(String tglAwal) {
        this.tglAwal = tglAwal;
    }
}
