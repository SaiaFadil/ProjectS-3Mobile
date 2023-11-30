package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelDetailPinjamDiterima;

public class ResponseDetailPinjamDiterima {

    private int kode;
    private String pesan;
    private ModelDetailPinjamDiterima data;

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

    public ModelDetailPinjamDiterima getData() {
        return data;
    }

    public void setData(ModelDetailPinjamDiterima data) {
        this.data = data;
    }

}
