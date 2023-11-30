package com.example.usingpreferences.DataModel;

public class ModelResponseSimpanEvent {

    public int kode;
    public String pesan;
    private ModelSimpanDataEvent data;

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

    public ModelSimpanDataEvent getData() {
        return data;
    }

    public void setData(ModelSimpanDataEvent data) {
        this.data = data;
    }
}

