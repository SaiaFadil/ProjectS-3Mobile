package com.example.usingpreferences.DataModel;

import com.example.usingpreferences.DataModel.ModelDetailPinjamDiajukan;

public class ResponseDetailPinjamDiajukan {

    private int kode;
    private String pesan;
    private ModelDetailPinjamDiajukan data;

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

    public ModelDetailPinjamDiajukan getData() {
        return data;
    }

    public void setData(ModelDetailPinjamDiajukan data) {
        this.data = data;
    }

}
