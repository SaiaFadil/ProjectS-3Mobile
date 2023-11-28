package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusPerpanjanganDiproses {


    private int kode;
    private String pesan;
    private List<ModelStatusPerpanjanganDiproses> data;

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

    public List<ModelStatusPerpanjanganDiproses> getData() {
        return data;
    }

    public void setData(List<ModelStatusPerpanjanganDiproses> data) {
        this.data = data;
    }
}
