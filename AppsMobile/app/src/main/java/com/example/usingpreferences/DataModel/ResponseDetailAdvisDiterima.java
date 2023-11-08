package com.example.usingpreferences.DataModel;

public class ResponseDetailAdvisDiterima {

    private int kode;
    private String pesan;
    private ModelDetailAdvisDiterima data;


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

    public ModelDetailAdvisDiterima getData() {
        return data;
    }

    public void setData(ModelDetailAdvisDiterima data) {
        this.data = data;
    }
}
