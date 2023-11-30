package com.example.usingpreferences.DataModel;
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
            ID_NAMA_TEMPAT,
            NAMA_TEMPAT,
            TANGGAL_MULAI,
            APP_LAUNCH_FROM,
            ACC_ID_USER,
            ACC_USERNAME,
            ACC_EMAIL,
            ACC_FULL_NAME,
            ACC_LEVEL,
            ACC_EMAIL_VERIFY,
            ACC_CREATED,
            ACC_PHOTO,
            VERIFY_EMAIL,
            VERIFY_OTP_CODE,
            VERIFY_START_MILLIS,
            VERIFY_END_MILLIS,
            VERIFY_TYPE,
            VERIFY_RESEND,
            VERIFY_CREATED,
            VERIFY_DEVICE,
            IS_FIRST_TIME_INSTALL,
            APP_LANGUAGE,
        }

    }

