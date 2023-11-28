package com.example.usingpreferences.DataModel;

public class ResponseDetailSenimanDiajukan {

    private int kode;
    private String pesan;
    private ModelDetailSenimanDiajukan data;
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

    public ModelDetailSenimanDiajukan getData() {
        return data;
    }

    public void setData(ModelDetailSenimanDiajukan data) {
        this.data = data;
    }
}
