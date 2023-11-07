package com.example.usingpreferences.DataModel;

public class ResponseDetailAdvisDitolak {



    private int kode;
    private String pesan;
    private ModelDetailAdvisDitolak data;


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

    public ModelDetailAdvisDitolak getData() {
        return data;
    }

    public void setData(ModelDetailAdvisDitolak data) {
        this.data = data;
    }
}
