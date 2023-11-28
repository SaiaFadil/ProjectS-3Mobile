package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusPerpanjanganDiajukan {


    private int kode;
    private String pesan;
    private List<ModelStatusPerpanjanganDiajukan> data;

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

    public List<ModelStatusPerpanjanganDiajukan> getData() {
        return data;
    }

    public void setData(List<ModelStatusPerpanjanganDiajukan> data) {
        this.data = data;
    }
}
