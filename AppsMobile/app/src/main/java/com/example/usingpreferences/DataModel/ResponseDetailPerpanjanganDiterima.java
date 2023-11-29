package com.example.usingpreferences.DataModel;

public class ResponseDetailPerpanjanganDiterima {

    private int kode;
    private String pesan;
    private ModelDetailPerpanjanganDiterima data;
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

    public ModelDetailPerpanjanganDiterima getData() {
        return data;
    }

    public void setData(ModelDetailPerpanjanganDiterima data) {
        this.data = data;
    }
}
