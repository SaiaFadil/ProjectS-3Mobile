package com.example.usingpreferences.DataModel;

public class ResponseDetailAdvisDiproses {

    private int kode;
    private String pesan;
    private ModelDetailAdvisDiproses data;


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

    public ModelDetailAdvisDiproses getData() {
        return data;
    }

    public void setData(ModelDetailAdvisDiproses data) {
        this.data = data;
    }
}
