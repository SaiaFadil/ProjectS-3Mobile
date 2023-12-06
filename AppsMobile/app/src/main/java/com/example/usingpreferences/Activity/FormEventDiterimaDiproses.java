package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailEvent;
import com.example.usingpreferences.DataModel.ResponseDetailEvent;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormEventDiterimaDiproses extends AppCompatActivity {

    public static String ID = "id";
    public static String TYPE = "type";

    public static String TYPE_DITERIMA = "1", TYPE_DIPROSES = "2";

    private String dataId;

    private ImageButton imgBack;
    private CardView cardView, cardTop;
    private TextView inpPengirim, inpTglAwal, inpTglAkhir, inpPilihFile, txtStatus, txtStatusBawah;
    private EditText  inpNamaEvent, inpTempat, inpDeskripsi, inpLink;
    private ImageView imageView;

    private ShimmerFrameLayout mFrameLayout;
    private LinearLayout mDataSemua;
    private Button lihatkodesurat;
    private Animation fadeIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_event_diterima);

        dataId = getIntent().getStringExtra(ID);

        imgBack = findViewById(R.id.statusback);
        inpPengirim = findViewById(R.id.et_namapengirimevent);
        inpNamaEvent = findViewById(R.id.et_namaeventevent);
        inpTempat = findViewById(R.id.et_alamateventevent);
        inpDeskripsi = findViewById(R.id.et_deskripsievent);
        inpLink = findViewById(R.id.et_linkpendaftaranevent);
        inpTglAkhir = findViewById(R.id.et_tanggalakhirevent);
        inpTglAwal = findViewById(R.id.et_tanggalawalevent);
        inpPilihFile = findViewById(R.id.pilihfile);
        cardView = findViewById(R.id.btn_card_status);
        cardTop = findViewById(R.id.card_top);
        txtStatus = findViewById(R.id.card_status);
        imageView = findViewById(R.id.gambar_diterima);
        txtStatusBawah = findViewById(R.id.text_status_diterima);

        inpNamaEvent.setFocusable(false);
        inpNamaEvent.setClickable(false);

        inpTempat.setFocusable(false);
        inpTempat.setClickable(false);

        inpDeskripsi.setFocusable(false);
        inpDeskripsi.setClickable(false);

        inpLink.setFocusable(false);
        inpLink.setClickable(false);

        RetroServer.getConnection().create(APIRequestData.class).getModelDetailEvent(dataId)
                .enqueue(new Callback<ResponseDetailEvent>() {
                    @Override
                    public void onResponse(Call<ResponseDetailEvent> call, Response<ResponseDetailEvent> response) {
                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){

                            ModelDetailEvent model = response.body().getData();

                            inpPengirim.setText(model.getNama_pengirim());
                            inpNamaEvent.setText(model.getNama_event());
                            inpTempat.setText(model.getTempat_event());
                            inpDeskripsi.setText(model.getDeskripsi());
                            inpLink.setText(model.getLink_pendaftaran());
                            inpTglAwal.setText(model.getTanggal_awal());
                            inpTglAkhir.setText(model.getTanggal_akhir());
                            inpPilihFile.setText(model.getPoster_event());

                        }else {

                            Toast.makeText(FormEventDiterimaDiproses.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseDetailEvent> call, Throwable t) {
                        t.printStackTrace();
                        Toast.makeText(FormEventDiterimaDiproses.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });


        Toast.makeText(this, getIntent().getStringExtra(TYPE), Toast.LENGTH_SHORT).show();
        switch (getIntent().getStringExtra(TYPE)){
            case "1": {
                cardTop.setCardBackgroundColor(ContextCompat.getColor(this, R.color.greenold));
                cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.greenold));
                txtStatus.setText("Diterima");
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.animstatus));
                txtStatusBawah.setText("Selamat! Permintaan Anda telah memenuhi persyaratan kami");
                break;
            }
            case "2" : {
                cardTop.setCardBackgroundColor(ContextCompat.getColor(this, R.color.greenold));
                cardView.setCardBackgroundColor(ContextCompat.getColor(this, R.color.yellow));
                txtStatus.setText("Diproses");
                imageView.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.animproses));
                txtStatusBawah.setText("Permintaan anda sedang diproses\nHarap tunggu notifikasi selanjutnya!");
                break;
            }
        }

        imgBack.setOnClickListener(v -> {
            onBackPressed();
        });

    }

    private void showData() {
//        mFrameLayout.startShimmer();
//        mDataSemua.setVisibility(View.GONE);
//        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
//        Call<ResponseDetailAdvisDiterima> getDetail = ardData
//                .getDetailAdvisDiterima(getIntent().getStringExtra("id_advis"));
//        getDetail.enqueue(new Callback<ResponseDetailAdvisDiterima>() {
//            @Override
//            public void onResponse(Call<ResponseDetailAdvisDiterima> call, Response<ResponseDetailAdvisDiterima> response) {
//                if (response.body().getKode() == 1) {
//                    ModelDetailAdvisDiterima ambildata = response.body().getData();
//                    if (ambildata.getId_advis().isEmpty()) {
//                        mFrameLayout.startShimmer();
//                        mDataSemua.setVisibility(View.GONE);
//                    } else {
//                        mFrameLayout.setVisibility(View.GONE);
//                        mFrameLayout.stopShimmer();
//                        mDataSemua.setVisibility(View.VISIBLE);
//                        mDataSemua.startAnimation(fadeIn);
//                    }
//                    et_namalengkapadvis.setText(ambildata.getNama_advis());
//                    et_tanggalpentasadvis.setText(ambildata.getTgl_advis());
//                    et_alamatadvis.setText(ambildata.getAlamat_advis());
//                    et_namapentasadvis.setText(ambildata.getDeskripsi_advis());
//                    et_lokasiadvis.setText(ambildata.getTempat_advis());
//                } else if (response.body().getKode() == 0) {
//                    Toast.makeText(FormEventDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                } else if (response.body().getKode() == 2) {
//                    Toast.makeText(FormEventDiterima.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<ResponseDetailAdvisDiterima> call, Throwable t) {
//                showAlertDialog();
//            }});
    }
    @Override
    public void onResume() {
        super.onResume();
        showData();
    }
    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormEventDiterimaDiproses.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }});
        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }
}