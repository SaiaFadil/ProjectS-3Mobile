package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ListTempatResponse {

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private ArrayList<ListTempatModel> data;
    public ListTempatResponse(String status, String message, ArrayList<ListTempatModel> data) {
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
    public ArrayList<ListTempatModel> getData() {
        return data;
    }
    public void setData(ArrayList<ListTempatModel> data) {
        this.data = data;
    }
}
