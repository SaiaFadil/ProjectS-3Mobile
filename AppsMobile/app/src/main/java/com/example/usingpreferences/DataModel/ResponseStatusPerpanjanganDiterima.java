package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusPerpanjanganDiterima {


    private int kode;
    private String pesan;
    private List<ModelStatusPerpanjanganDiterima> data;

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

    public List<ModelStatusPerpanjanganDiterima> getData() {
        return data;
    }

    public void setData(List<ModelStatusPerpanjanganDiterima> data) {
        this.data = data;
    }
}
