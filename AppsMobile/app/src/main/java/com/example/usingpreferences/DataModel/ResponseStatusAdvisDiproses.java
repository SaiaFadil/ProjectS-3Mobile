package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusAdvisDiproses {


    private int kode;
    private String pesan;
    private List<ModelStatusAdvisDiproses> data;

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

    public List<ModelStatusAdvisDiproses> getData() {
        return data;
    }

    public void setData(List<ModelStatusAdvisDiproses> data) {
        this.data = data;
    }
}
