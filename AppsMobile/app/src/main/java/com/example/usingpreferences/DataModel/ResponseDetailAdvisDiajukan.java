package com.example.usingpreferences.DataModel;

public class ResponseDetailAdvisDiajukan {

    private int kode;
    private String pesan;
    private ModelDetailAdvisDiajukan data;


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

    public ModelDetailAdvisDiajukan getData() {
        return data;
    }

    public void setData(ModelDetailAdvisDiajukan data) {
        this.data = data;
    }
}
