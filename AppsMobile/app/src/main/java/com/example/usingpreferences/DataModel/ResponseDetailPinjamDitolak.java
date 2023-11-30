package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelDetailPinjamDitolak;

public class ResponseDetailPinjamDitolak {

    private int kode;
    private String pesan;
    private ModelDetailPinjamDitolak data;

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

    public ModelDetailPinjamDitolak getData() {
        return data;
    }

    public void setData(ModelDetailPinjamDitolak data) {
        this.data = data;
    }

}
