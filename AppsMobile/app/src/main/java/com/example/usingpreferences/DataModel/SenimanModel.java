// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.usingpreferences.DataModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import retrofit2.http.Multipart;

public class SenimanModel {
    @Expose
    @SerializedName("anggota_organisasi")
    private String anggotaOrganisasi;
    @Expose
    @SerializedName("pass_foto")
    private Multipart passFoto;
    @Expose
    @SerializedName("id_seniman")
    private long idSeniman;
    @Expose
    @SerializedName("foto_ktp")
    private Multipart fotoKtp;
    @Expose
    @SerializedName("id_user")
    private long idUser;
    @Expose
    @SerializedName("surat_keterangan")
    private Multipart suratKeterangan;
    @Expose
    @SerializedName("nik_seniman")
    private String nikSeniman;
    @Expose
    @SerializedName("alamat")
    private String alamat;
    @Expose
    @SerializedName("nama_seniman")
    private String namaSeniman;
    @Expose
    @SerializedName("tanggal_lahir")
    private String tempatLahir;
    @Expose
    @SerializedName("nama_organisasi")
    private String namaOrganisasi;
    @Expose
    @SerializedName("no_telpon")
    private String noTelpon;
    @Expose
    @SerializedName("jenis_kelamin_seniman")
    private String jenisKelaminSeniman;
    @Expose
    @SerializedName("tanggal_lahir")
    private String tanggalLahir;

    public String getAnggotaOrganisasi() { return anggotaOrganisasi; }
    public void setAnggotaOrganisasi(String value) { this.anggotaOrganisasi = value; }

    public Multipart getPassFoto() { return passFoto; }
    public void setPassFoto(Multipart value) { this.passFoto = value; }

    public long getidSeniman() { return idSeniman; }
    public void setidSeniman(long value) { this.idSeniman = value; }

    public Multipart getFotoKtp() { return fotoKtp; }
    public void setFotoKtp(Multipart value) { this.fotoKtp = value; }

    public long getidUser() { return idUser; }
    public void setidUser(long value) { this.idUser = value; }

    public Multipart getSuratKeterangan() { return suratKeterangan; }
    public void setSuratKeterangan(Multipart value) { this.suratKeterangan = value; }

    public String getNikSeniman() { return nikSeniman; }
    public void setNikSeniman(String value) { this.nikSeniman = value; }

    public String getAlamat() { return alamat; }
    public void setAlamat(String value) { this.alamat = value; }

    public String getNamaSeniman() { return namaSeniman; }
    public void setNamaSeniman(String value) { this.namaSeniman = value; }

    public String getTempatLahir() { return tempatLahir; }
    public void setTempatLahir(String value) { this.tempatLahir = value; }

    public String getNamaOrganisasi() { return namaOrganisasi; }
    public void setNamaOrganisasi(String value) { this.namaOrganisasi = value; }

    public String getNoTelpon() { return noTelpon; }
    public void setNoTelpon(String value) { this.noTelpon = value; }

    public String getJenisKelaminSeniman() { return jenisKelaminSeniman; }
    public void setJenisKelaminSeniman(String value) { this.jenisKelaminSeniman = value; }

    public String getTanggalLahir() { return tanggalLahir; }
    public void setTanggalLahir(String value) { this.tanggalLahir = value; }
}
