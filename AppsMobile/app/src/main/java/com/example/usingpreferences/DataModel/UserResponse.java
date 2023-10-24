package com.example.usingpreferences.DataModel;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UserResponse {

    @Expose
    @SerializedName("status")
    private String status;
    @Expose
    @SerializedName("message")
    private String message;
    @Expose
    @SerializedName("data")
    private ModelUsers data;

    public UserResponse(String status, String message, ModelUsers data){
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

    public ModelUsers getData() {
        return data;
    }

    public void setData(ModelUsers data) {
        this.data = data;
    }

}