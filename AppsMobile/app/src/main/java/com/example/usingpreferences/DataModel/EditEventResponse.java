package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EditEventResponse {

    @Expose
    @SerializedName("status")
    private String status;

    @Expose
    @SerializedName("message")
    private String message;

    @Expose
    @SerializedName("data")
    private ModelSimpanDataEvent data;

    public EditEventResponse(String status, String message, ModelSimpanDataEvent data) {
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

    public ModelSimpanDataEvent getData() {
        return data;
    }

    public void setData(ModelSimpanDataEvent data) {
        this.data = data;
    }
}
