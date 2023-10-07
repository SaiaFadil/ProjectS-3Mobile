package com.example.usingpreferences.DataModel;

import java.util.List;

public class ResponseModelUsers {

    public int kode;

    public String pesan;
    private List<ModelUsers> data;


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

    public List<ModelUsers> getData() {
        return data;
    }

    public void setData(List<ModelUsers> data) {
        this.data = data;
    }

}