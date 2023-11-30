package com.example.usingpreferences.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.PinjamTempatAdapter;
import com.example.usingpreferences.Adapter.AdapterListener;
import com.example.usingpreferences.DataModel.DataShared;
import com.example.usingpreferences.DataModel.ListTempatModel;
import com.example.usingpreferences.DataModel.ListTempatResponse;
import com.example.usingpreferences.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PinjamTempatList extends AppCompatActivity {
    private CardView cardanjukladang, cardbalaibudaya, cardsoetomo, cardsedudo, cardgoamargotresno, cardrorokuning;

    private DataShared dataShared;

    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pinjam_tempat_list);

        dataShared = new DataShared(this);

        cardanjukladang = findViewById(R.id.cardanjukladang);
        cardbalaibudaya = findViewById(R.id.cardbalaibudaya);
        cardsoetomo = findViewById(R.id.cardsoetomo);
        cardsedudo = findViewById(R.id.cardsedudo);
        cardgoamargotresno = findViewById(R.id.cardgoamargotresno);
        cardrorokuning = findViewById(R.id.cardrorokuning);
        recyclerView = findViewById(R.id.ptl_recycler);

        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ListTempatResponse> listResponse = ardData.lisTempatttt();

        listResponse.enqueue(new Callback<ListTempatResponse>() {
            @Override
            public void onResponse(Call<ListTempatResponse> call, Response<ListTempatResponse> response) {

            }

            @Override
            public void onFailure(Call<ListTempatResponse> call, Throwable t) {

            }
        });

        RetroServer.getConnection().create(APIRequestData.class).lisTempatttt().enqueue(new Callback<ListTempatResponse>() {
            @Override
            public void onResponse(Call<ListTempatResponse> call, Response<ListTempatResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {

                    ArrayList<ListTempatModel> data = response.body().getData();

                    recyclerView.setAdapter(
                            new PinjamTempatAdapter(
                                    PinjamTempatList.this, response.body().getData(), new AdapterListener() {
                                @Override
                                public void onClickListener(int poisisi) {
                                    dataShared.setData(DataShared.KEY.ID_NAMA_TEMPAT, data.get(poisisi).getidTempat());
                                    dataShared.setData(DataShared.KEY.NAMA_TEMPAT, data.get(poisisi).getNamaTempat());
                                    startActivity(new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class));
                                    Toast.makeText(PinjamTempatList.this, dataShared.getData(DataShared.KEY.ID_NAMA_TEMPAT), Toast.LENGTH_SHORT).show();
                                }
                            }
                            )
                    );

                } else {
                    Toast.makeText(PinjamTempatList.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ListTempatResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(PinjamTempatList.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

//        cardanjukladang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//               dataShared.setData(DataShared.KEY.NAMA_TEMPAT, "Museum Anjuk Ladang");
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });
//        cardbalaibudaya.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
//                pindah.putExtra("nama_tempat","Balai Budaya");
//                startActivity(pindah);
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });
//        cardsoetomo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
//                pindah.putExtra("nama_tempat","Museum Dr.Soetomo");
//                startActivity(pindah);
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });
//        cardsedudo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
//                pindah.putExtra("nama_tempat","Air Terjun Sedudo");
//                startActivity(pindah);
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });
//        cardgoamargotresno.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
//                pindah.putExtra("nama_tempat","Goa Margo Tresno");
//                startActivity(pindah);
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });
//        cardrorokuning.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent pindah = new Intent(PinjamTempatList.this, PilihTanggalAwalPeminjaman.class);
//                pindah.putExtra("nama_tempat","Air Terjun Roro Kuning");
//                startActivity(pindah);
//                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//
//            }
//        });


        ImageButton pinjamback = findViewById(R.id.pinjamback);
        pinjamback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PinjamTempatList.this, MainActivity.class).putExtra(MainActivity.FRAGMENT, R.layout.fragment_home);
                startActivity(intent);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
    }


    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

    }
}