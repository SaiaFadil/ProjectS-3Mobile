package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDiproses;
import com.example.usingpreferences.DataModel.ModelDetailPinjamDiterima;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiproses;
import com.example.usingpreferences.DataModel.ResponseDetailPinjamDiterima;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.Base64;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormPinjamDiterima extends AppCompatActivity {

    EditText namaLengkap , tanggalAwal , tanggalAkhir , waktuAwal , waktuAkhir , deskripsiPinjam ;
    TextView ktpPinjam , instansiPinjam , namaKegiatanPinjam , JumlahPesertaPinjam , tempatPinjam , pathFile;
    Button viewKodeSurat;
    LinearLayout mData;
    ImageButton back;
    private ShimmerFrameLayout mFrameLayout;
    private Animation fadeIn;
    String idSewa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_pinjam_diterima);

        namaLengkap =findViewById(R.id.et_namalengkappinjamDiterima);
        ktpPinjam =findViewById(R.id.et_ktppinjam);
        instansiPinjam =findViewById(R.id.et_instansipinjam);
        namaKegiatanPinjam =findViewById(R.id.et_namakegiatanpinjam);
        JumlahPesertaPinjam =findViewById(R.id.et_jumlahpesertapinjam);
        tempatPinjam=findViewById(R.id.et_tempatpinjam);
        tanggalAwal=findViewById(R.id.inputTglAwal);
        tanggalAkhir=findViewById(R.id.inputTglAkhir);
        waktuAwal=findViewById(R.id.inputWaktuAwal);
        waktuAkhir=findViewById(R.id.inputWaktuAkhir);
        deskripsiPinjam=findViewById(R.id.et_deskripsipinjam);
        pathFile=findViewById(R.id.pilihfile);
        viewKodeSurat=findViewById(R.id.lihatkodesurat);
        mData=findViewById(R.id.layoutData);
        mFrameLayout=findViewById(R.id.shimmer_view_detail);
        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);

        back = findViewById(R.id.statusback);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FormPinjamDiterima.super.onBackPressed();
            }
        });
//
        System.out.println("ID SEWA" + getIntent().getStringExtra("id_sewa"));
        namaLengkap.setFocusable(false);
        namaLengkap.setClickable(false);

        waktuAwal.setFocusable(false);
        waktuAwal.setClickable(false);

        waktuAkhir.setFocusable(false);
        waktuAkhir.setClickable(false);

        tanggalAkhir.setFocusable(false);
        tanggalAkhir.setClickable(false);

        deskripsiPinjam.setFocusable(false);
        deskripsiPinjam.setClickable(false);

        tanggalAwal.setFocusable(false);
        tanggalAwal.setClickable(false);

        detailShowDataDiterima();
        lihatSuratPeminjaman();

    }

    public void detailShowDataDiterima(){
        mFrameLayout.startShimmer();
        mData.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailPinjamDiterima> getDetail = ardData.getDetailPinjamDiterima(getIntent().getStringExtra("id_sewa"));
        getDetail.enqueue(new Callback<ResponseDetailPinjamDiterima>() {
            @Override
            public void onResponse(Call<ResponseDetailPinjamDiterima> call, Response<ResponseDetailPinjamDiterima> response) {
                if (response.body().getKode() == 1) {
                    ModelDetailPinjamDiterima ambildata = response.body().getData();
                    if (ambildata.getId_sewa().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mData.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mData.setVisibility(View.VISIBLE);
                        mData.startAnimation(fadeIn);
                    }
                    String encodedString = ambildata.getNik_sewa();

                    // Mendecode string Base64 menjadi array byte
                    byte[] decodedBytes = new byte[0];
                    if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                        decodedBytes = Base64.getDecoder().decode(encodedString);
                    }

                    // Mengonversi array byte menjadi string
                    String decodedString = new String(decodedBytes);

                    idSewa = ambildata.getId_sewa();
                    namaLengkap.setText(ambildata.getNama_peminjam());
                    ktpPinjam.setText(decodedString);
                    instansiPinjam.setText(ambildata.getInstansi());
                    namaKegiatanPinjam.setText(ambildata.getNama_kegiatan_sewa());
                    JumlahPesertaPinjam.setText(ambildata.getJumlah_peserta());
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
                    deskripsiPinjam.setText(ambildata.getDeskripsi_sewa_tempat());
                    pathFile.setText(ambildata.getSurat_ket_sewa());

                    System.out.println("tanggal awal =" + ambildata.getTgl_awal_peminjaman());
                    System.out.println("ID TEMPAT = " + ambildata.getId_tempat());


                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormPinjamDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormPinjamDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseDetailPinjamDiterima> call, Throwable t) {
                showAlertDialog();
            }
        });
    }

    public void lihatSuratPeminjaman(){
        viewKodeSurat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!idSewa.isEmpty()){Intent intent = new Intent(view.getContext() , LihatKode_Pinjam.class);
                    intent.putExtra("id_sewa" , idSewa);
                    view.getContext().startActivity(intent);}

            }
        });
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormPinjamDiterima.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        detailShowDataDiterima();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }
}
