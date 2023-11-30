package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ResponseDetailEvent {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private ModelDetailEvent data;

    public ResponseDetailEvent(String status, String message, ModelDetailEvent data) {
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

    public ModelDetailEvent getData() {
        return data;
    }

    public void setData(ModelDetailEvent data) {
        this.data = data;
    }
}
