package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusSenimanDiterima {


    private int kode;
    private String pesan;
    private List<ModelStatusSenimanDiterima> data;

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

    public List<ModelStatusSenimanDiterima> getData() {
        return data;
    }

    public void setData(List<ModelStatusSenimanDiterima> data) {
        this.data = data;
    }
}
