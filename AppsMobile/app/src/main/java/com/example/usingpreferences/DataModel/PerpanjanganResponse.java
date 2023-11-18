package com.example.usingpreferences.DataModel;

import java.util.List;

public class PerpanjanganResponse {
    String message;
    String status;
    List<PerpanjanganModel> data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<PerpanjanganModel> getData() {
        return data;
    }

    public void setData(List<PerpanjanganModel> data) {
        this.data = data;
    }
}
