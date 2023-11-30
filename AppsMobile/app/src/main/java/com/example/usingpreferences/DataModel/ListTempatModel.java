// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ListTempatModel {

    @Expose
    @SerializedName("id_tempat")
    private String idTempat;
    @Expose
    @SerializedName("foto_tempat")
    private String fotoTempat;
    @Expose
    @SerializedName("alamat_tempat")
    private String alamatTempat;
    @Expose
    @SerializedName("contact_person")
    private String contactPerson;
    @Expose
    @SerializedName("pengelola")
    private String pengelola;
    @Expose
    @SerializedName("deskripsi_tempat")
    private String deskripsiTempat;
    @Expose
    @SerializedName("nama_tempat")
    private String namaTempat;

    public String getidTempat() {
        return idTempat;
    }

    public void setidTempat(String value) {
        this.idTempat = value;
    }

    public String getFotoTempat() {
        return fotoTempat;
    }

    public void setFotoTempat(String value) {
        this.fotoTempat = value;
    }

    public String getAlamatTempat() {
        return alamatTempat;
    }

    public void setAlamatTempat(String value) {
        this.alamatTempat = value;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String value) {
        this.contactPerson = value;
    }

    public String getPengelola() {
        return pengelola;
    }

    public void setPengelola(String value) {
        this.pengelola = value;
    }

    public String getDeskripsiTempat() {
        return deskripsiTempat;
    }

    public void setDeskripsiTempat(String value) {
        this.deskripsiTempat = value;
    }

    public String getNamaTempat() {
        return namaTempat;
    }

    public void setNamaTempat(String value) {
        this.namaTempat = value;
    }
}
