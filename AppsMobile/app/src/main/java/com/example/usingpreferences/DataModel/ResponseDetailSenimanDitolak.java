package com.example.usingpreferences.DataModel;

public class ResponseDetailSenimanDitolak {

    private int kode;
    private String pesan;
    private ModelDetailSenimanDitolak data;


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

    public ModelDetailSenimanDitolak getData() {
        return data;
    }

    public void setData(ModelDetailSenimanDitolak data) {
        this.data = data;
    }
}
