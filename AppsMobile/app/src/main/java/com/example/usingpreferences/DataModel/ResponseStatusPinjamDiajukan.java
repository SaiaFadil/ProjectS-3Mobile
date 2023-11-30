package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelStatusPinjamDiajukan;

import java.util.List;

public class ResponseStatusPinjamDiajukan {

    private int kode;
    private String pesan;
    private List<ModelStatusPinjamDiajukan> data;

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

    public List<ModelStatusPinjamDiajukan> getData() {
        return data;
    }

    public void setData(List<ModelStatusPinjamDiajukan> data) {
        this.data = data;
    }

}
