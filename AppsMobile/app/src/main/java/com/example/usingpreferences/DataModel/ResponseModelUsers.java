package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseModelUsers {

    @Expose
    @SerializedName("kode")
    public int kode;

    @Expose
    @SerializedName("pesan")
    public String pesan;

    @Expose
    @SerializedName("data")
    private ModelUsers data;


    public int getKode() {
        return kode;
    }

    public void setKode(int kode) {
        this.kode = kode;
    }

    public String getPesan() {
        return pesan;
    }

    public void setPesan(String pesan) {
        this.pesan = pesan;
    }

    public ModelUsers getData() {
        return data;
    }

    public void setData(ModelUsers data) {
        this.data = data;
    }

}