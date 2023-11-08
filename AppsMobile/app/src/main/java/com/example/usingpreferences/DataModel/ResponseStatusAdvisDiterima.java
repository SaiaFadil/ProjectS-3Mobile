package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusAdvisDiterima {


    private int kode;
    private String pesan;
    private List<ModelStatusAdvisDiterima> data;

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

    public List<ModelStatusAdvisDiterima> getData() {
        return data;
    }

    public void setData(List<ModelStatusAdvisDiterima> data) {
        this.data = data;
    }
}
