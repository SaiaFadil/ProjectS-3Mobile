package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDiproses;
import com.example.usingpreferences.DataModel.ModelDetailSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiproses;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Arrays;
import java.util.Base64;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPinjamDiproses extends AppCompatActivity {
    EditText namaLengkap , tanggalAwal , tanggalAkhir , waktuAwal , waktuAkhir , deskripsi;
    TextView noKtp , instansiPinjam, namaKegiatan , jumlahPeserta , tempatPinjam , pathFile;

    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Animation fadeIn;
    ImageButton back;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pinjam_diproses);

        namaLengkap = findViewById(R.id.et_namalengkappinjam);
        noKtp = findViewById(R.id.et_ktppinjam);
        instansiPinjam = findViewById(R.id.et_instansipinjam);
        namaKegiatan = findViewById(R.id.et_namakegiatanpinjam);
        jumlahPeserta = findViewById(R.id.et_jumlahpesertapinjam);
        tempatPinjam = findViewById(R.id.et_tempatpinjam);
        tanggalAwal = findViewById(R.id.inputTglAwal);
        tanggalAkhir = findViewById(R.id.inputTglAkhir);
        waktuAwal = findViewById(R.id.inputWaktuAwal);
        waktuAkhir = findViewById(R.id.inputWaktuAkhir);
        deskripsi = findViewById(R.id.et_deskripsipinjam);
        pathFile = findViewById(R.id.pilihfile);
        mFrameLayout = findViewById(R.id.shimmer_view_detail);
        mDataSemua = findViewById(R.id.layoutData);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);

        back = findViewById(R.id.statusback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormPinjamDiproses.super.onBackPressed();
            }
        });

        namaLengkap.setFocusable(false);
        namaLengkap.setClickable(false);

        waktuAwal.setFocusable(false);
        waktuAwal.setClickable(false);

        waktuAkhir.setFocusable(false);
        waktuAkhir.setClickable(false);

        tanggalAkhir.setFocusable(false);
        tanggalAkhir.setClickable(false);

        deskripsi.setFocusable(false);
        deskripsi.setClickable(false);

        tanggalAwal.setFocusable(false);
        tanggalAwal.setClickable(false);

        showData();
    }

    public void showData(){
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailPinjamDiproses> getDetail = ardData.getDetailPinjamDiproses(getIntent().getStringExtra("id_sewa"));
        getDetail.enqueue(new Callback<ResponseDetailPinjamDiproses>() {
            @Override
            public void onResponse(Call<ResponseDetailPinjamDiproses> call, Response<ResponseDetailPinjamDiproses> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailPinjamDiproses ambildata = response.body().getData();
                    if (ambildata.getId_sewa().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.setVisibility(View.VISIBLE);
                        mDataSemua.startAnimation(fadeIn);
                    }

                    String encodedString = ambildata.getNik_sewa();

                    // Mendecode string Base64 menjadi array byte
                    byte[] decodedBytes = new byte[0];
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        decodedBytes = Base64.getDecoder().decode(encodedString);
                    }

                    // Mengonversi array byte menjadi string
                    String decodedString = new String(decodedBytes);
                    namaLengkap.setText(ambildata.getNama_peminjam());
                    noKtp.setText(decodedString);
                    instansiPinjam.setText(ambildata.getInstansi());
                    namaKegiatan.setText(ambildata.getNama_kegiatan_sewa());
                    jumlahPeserta.setText(ambildata.getJumlah_peserta());
                    tempatPinjam.setText(ambildata.getNama_tempat());

                    String[] parts = ambildata.getTgl_awal_peminjaman().split(" ");
                    String tglAwal = parts[0];
                    String JamAwal = parts[1];
                    tanggalAwal.setText(tglAwal);
                    waktuAwal.setText(JamAwal);

                    String[] parts2 = ambildata.getTgl_akhir_peminjaman().split(" ");
                    String tglAkhir = parts2[0];
                    String jamAkhri = parts2[1];
                    tanggalAkhir.setText(tglAkhir);
                    waktuAkhir.setText(jamAkhri);
                    deskripsi.setText(ambildata.getDeskripsi_sewa_tempat());
                    pathFile.setText(ambildata.getSurat_ket_sewa());

                    System.out.println("tanggal awal =" + ambildata.getTgl_awal_peminjaman());
                    System.out.println("ID TEMPAT = " + ambildata.getId_tempat());
                    System.out.println("ID Sewa = " + ambildata.getId_sewa());
                    SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                    String idUserShared = sharedPreferences.getString("id_user", "");
                    System.out.println("ID USER = " + idUserShared);


                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormPinjamDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormPinjamDiproses.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPinjamDiproses> call, Throwable t) {
                showAlertDialog();
            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormPinjamDiproses.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
