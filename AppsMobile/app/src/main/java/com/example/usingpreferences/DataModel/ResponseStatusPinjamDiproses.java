package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelStatusPinjamDiproses;

import java.util.List;

public class ResponseStatusPinjamDiproses {

    private int kode;
    private String pesan;
    private List<ModelStatusPinjamDiproses> data;

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

    public List<ModelStatusPinjamDiproses> getData() {
        return data;
    }

    public void setData(List<ModelStatusPinjamDiproses> data) {
        this.data = data;
    }

}
