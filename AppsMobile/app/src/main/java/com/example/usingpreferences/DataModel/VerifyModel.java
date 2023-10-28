package com.example.usingpreferences.DataModel;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class VerifyModel {
    @Expose
    @SerializedName("email")
    private String email;

    @Expose
    @SerializedName("kode_otp")
    private String otp;

    @Expose
    @SerializedName("link")
    private String link;

    @Expose
    @SerializedName("deskripsi")
    private String deskripsi;

    @Expose
    @SerializedName("created_at")
    private String created_at;

    @Expose
    @SerializedName("updated_at")
    private String updated_at;


    @Expose
    @SerializedName("resend")
    private String resend;

    public VerifyModel(String email, String otp, String device, String resend) {
        this.email = email;
        this.otp = otp;
        this.deskripsi = deskripsi;
        this.link = link;
        this.updated_at = device;
        this.resend = resend;

    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOtp() {
        return otp;
    }

    public String getDevice() {
        return updated_at;
    }

    public void setDevice(String device) {
        this.updated_at = device;
    }

    public String getResend() {
        return resend;
    }

    public void setResend(String resend) {
        this.resend = resend;
    }

}
