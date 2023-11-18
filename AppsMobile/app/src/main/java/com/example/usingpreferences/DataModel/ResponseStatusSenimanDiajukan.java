package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusSenimanDiajukan {


    private int kode;
    private String pesan;
    private List<ModelStatusSenimanDiajukan> data;

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

    public List<ModelStatusSenimanDiajukan> getData() {
        return data;
    }

    public void setData(List<ModelStatusSenimanDiajukan> data) {
        this.data = data;
    }
}
