package com.example.usingpreferences.DataModel;

import java.util.List;

public class SenimanResponse {
    String message;
    String status;

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

    public List<SenimanModel> getData() {
        return data;
    }

    public void setData(List<SenimanModel> data) {
        this.data = data;
    }

    List<SenimanModel> data;
}
