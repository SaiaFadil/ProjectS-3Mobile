package com.example.usingpreferences.DataModel;

public class ResponseDetailPerpanjanganDiproses {

    private int kode;
    private String pesan;
    private ModelDetailPerpanjanganDiproses data;
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

    public ModelDetailPerpanjanganDiproses getData() {
        return data;
    }

    public void setData(ModelDetailPerpanjanganDiproses data) {
        this.data = data;
    }
}
