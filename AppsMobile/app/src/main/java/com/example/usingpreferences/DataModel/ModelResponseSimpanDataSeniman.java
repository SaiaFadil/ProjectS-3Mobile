package com.example.usingpreferences.DataModel;

public class ModelResponseSimpanDataSeniman {

    public int kode;
    public String pesan;
    private ModelSimpanDataSeniman data;

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

    public ModelSimpanDataSeniman getData() {
        return data;
    }

    public void setData(ModelSimpanDataSeniman data) {
        this.data = data;
    }
}
