package com.example.usingpreferences.DataModel;

public class ResponseDetailSenimanDiproses {

    private int kode;
    private String pesan;
    private ModelDetailSenimanDiproses data;
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

    public ModelDetailSenimanDiproses getData() {
        return data;
    }

    public void setData(ModelDetailSenimanDiproses data) {
        this.data = data;
    }
}
