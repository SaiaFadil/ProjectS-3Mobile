package com.example.usingpreferences.DataModel;

import java.util.List;

public class getSingkatanResponse {
    int kode;
    String pesan;
    List<getSingkatanModel> data;

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

    public List<getSingkatanModel> getData() {
        return data;
    }

    public void setData(List<getSingkatanModel> data) {
        this.data = data;
    }
}