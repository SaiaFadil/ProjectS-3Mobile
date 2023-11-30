package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelStatusPinjamDitolak;

import java.util.List;

public class ResponseStatusPinjamDitolak {

    private int kode;
    private String pesan;
    private List<ModelStatusPinjamDitolak> data;

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

    public List<ModelStatusPinjamDitolak> getData() {
        return data;
    }

    public void setData(List<ModelStatusPinjamDitolak> data) {
        this.data = data;
    }

}
