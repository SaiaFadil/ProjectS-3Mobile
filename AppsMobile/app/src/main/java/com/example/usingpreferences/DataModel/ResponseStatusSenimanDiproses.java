package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusSenimanDiproses {


    private int kode;
    private String pesan;
    private List<ModelStatusSenimanDiproses> data;

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

    public List<ModelStatusSenimanDiproses> getData() {
        return data;
    }

    public void setData(List<ModelStatusSenimanDiproses> data) {
        this.data = data;
    }
}
