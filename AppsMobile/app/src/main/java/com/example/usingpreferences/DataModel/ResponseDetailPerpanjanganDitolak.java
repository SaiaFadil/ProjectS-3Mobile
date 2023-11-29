package com.example.usingpreferences.DataModel;

public class ResponseDetailPerpanjanganDitolak {

    private int kode;
    private String pesan;
    private ModelDetailPerpanjanganDitolak data;
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

    public ModelDetailPerpanjanganDitolak getData() {
        return data;
    }

    public void setData(ModelDetailPerpanjanganDitolak data) {
        this.data = data;
    }
}
