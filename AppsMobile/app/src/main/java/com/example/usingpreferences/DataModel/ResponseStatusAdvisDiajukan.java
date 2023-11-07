package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusAdvisDiajukan {


    private int kode;
    private String pesan;
    private List<ModelStatusAdvisDiajukan> data;

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

    public List<ModelStatusAdvisDiajukan> getData() {
        return data;
    }

    public void setData(List<ModelStatusAdvisDiajukan> data) {
        this.data = data;
    }
}
