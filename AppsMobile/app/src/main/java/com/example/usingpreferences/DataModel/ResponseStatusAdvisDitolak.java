package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseStatusAdvisDitolak {

    private int kode;
    private String pesan;
    private List<ModelStatusAdvisDitolak> data;

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

    public List<ModelStatusAdvisDitolak> getData() {
        return data;
    }

    public void setData(List<ModelStatusAdvisDitolak> data) {
        this.data = data;
    }
}
