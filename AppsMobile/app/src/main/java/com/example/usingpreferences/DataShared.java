package com.example.usingpreferences;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;

import java.util.HashMap;

public class DataShared {

    private final SharedPreferences sharedPrefs;

    private final SharedPreferences.Editor sharedEditor;

    public static final String NAME = "com.example.usingpreferences";

    public DataShared(Context context){
        // membuat object sharedpreferences
        this.sharedPrefs = context.getSharedPreferences(DataShared.NAME, Context.MODE_PRIVATE);
        this.sharedEditor = this.sharedPrefs.edit();
    }

    public boolean contains(@NonNull KEY key){

        return this.sharedPrefs.contains(key.name());
    }

    public String getData(@NonNull KEY key){
        return this.sharedPrefs.getString(key.name(), null);
    }

    public HashMap<KEY, String> getData(@NonNull KEY... keys){
        HashMap<KEY, String> data = new HashMap<>();
        for (KEY key : keys){
            if (contains(key)){
                data.put(key, getData(key));
            }else {
                data.put(key, "null");
            }
        }
        return data;
    }

    public void setData(@NonNull KEY key, @NonNull String value){

        this.sharedEditor.putString(key.name(), value).apply();
    }

    public void setNullData(@NonNull KEY key){
        this.setData(key, "");
    }

    @Deprecated
    public void remove(@NonNull KEY key){

        this.sharedEditor.remove(key.name()).apply();
    }

    public enum KEY {

        NIK,
        Nama_Lengkap,
        Jenis_Kelamin,
        Tempat_Lahir,
        Tanggal_Lahir,
        Kecamatan,
        Alamat_Lengkap,
        Nomor_Telepon,
        Tipe_Seniman,
        Nama_Organisasi,
        Jumlah_Anggota
    }

}

