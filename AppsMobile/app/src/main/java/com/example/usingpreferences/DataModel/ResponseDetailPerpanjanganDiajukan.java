package com.example.usingpreferences.DataModel;

public class ResponseDetailPerpanjanganDiajukan {

    private int kode;
    private String pesan;
    private ModelDetailPerpanjanganDiajukan data;
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

    public ModelDetailPerpanjanganDiajukan getData() {
        return data;
    }

    public void setData(ModelDetailPerpanjanganDiajukan data) {
        this.data = data;
    }
}
