package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TanggalSewaModel {

    @Expose
    @SerializedName("tgl_awal")
    private String tglAwal;

    @Expose
    @SerializedName("tgl_akhir")
    private String tglAkhir;

    public TanggalSewaModel(String tglAwal, String tglAkhir) {
        this.tglAwal = tglAwal;
        this.tglAkhir = tglAkhir;
    }

    public String getTglAwal() {
        return tglAwal;
    }

    public void setTglAwal(String tglAwal) {
        this.tglAwal = tglAwal;
    }

    public String getTglAkhir() {
        return tglAkhir;
    }

    public void setTglAkhir(String tglAkhir) {
        this.tglAkhir = tglAkhir;
    }
}
