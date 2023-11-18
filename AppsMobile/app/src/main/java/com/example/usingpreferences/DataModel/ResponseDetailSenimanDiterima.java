package com.example.usingpreferences.DataModel;

public class ResponseDetailSenimanDiterima {

    private int kode;
    private String pesan;
    private ModelDetailSenimanDiterima data;


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

    public ModelDetailSenimanDiterima getData() {
        return data;
    }

    public void setData(ModelDetailSenimanDiterima data) {
        this.data = data;
    }
}
