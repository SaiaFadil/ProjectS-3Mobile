package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusSenimanDitolak {

    private int kode;
    private String pesan;
    private List<ModelStatusSenimanDitolak> data;

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

    public List<ModelStatusSenimanDitolak> getData() {
        return data;
    }

    public void setData(List<ModelStatusSenimanDitolak> data) {
        this.data = data;
    }
}
