package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelStatusPinjamDiterima;

import java.util.List;

public class ResponseStatusPinjamDiterima {

    private int kode;
    private String pesan;
    private List<ModelStatusPinjamDiterima> data;

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

    public List<ModelStatusPinjamDiterima> getData() {
        return data;
    }

    public void setData(List<ModelStatusPinjamDiterima> data) {
        this.data = data;
    }

}
