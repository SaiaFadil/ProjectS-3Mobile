package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResponseStatusEvent {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private ArrayList<ModelStatusEvent> data;

    public ResponseStatusEvent(String status, String message, ArrayList<ModelStatusEvent> data) {
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

    public ArrayList<ModelStatusEvent> getData() {
        return data;
    }

    public void setData(ArrayList<ModelStatusEvent> data) {
        this.data = data;
    }
}
