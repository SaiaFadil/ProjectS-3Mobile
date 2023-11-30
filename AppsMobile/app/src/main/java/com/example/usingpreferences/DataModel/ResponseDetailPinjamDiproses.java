package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelDetailPinjamDiproses;

public class ResponseDetailPinjamDiproses {

    private int kode;
    private String pesan;
    private ModelDetailPinjamDiproses data;

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

    public ModelDetailPinjamDiproses getData() {
        return data;
    }

    public void setData(ModelDetailPinjamDiproses data) {
        this.data = data;
    }

}
