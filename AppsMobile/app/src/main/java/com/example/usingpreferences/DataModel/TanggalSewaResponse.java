package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;

public class TanggalSewaResponse {

    @Expose
    @SuppressWarnings("status")
    private String status;

    @Expose
    @SuppressWarnings("message")
    private String message;

    @Expose
    @SuppressWarnings("data")
    private ArrayList<TanggalSewaModel> data;

    public TanggalSewaResponse(String status, String message, ArrayList<TanggalSewaModel> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ArrayList<TanggalSewaModel> getData() {
        return data;
    }

    public void setData(ArrayList<TanggalSewaModel> data) {
        this.data = data;
    }
}
