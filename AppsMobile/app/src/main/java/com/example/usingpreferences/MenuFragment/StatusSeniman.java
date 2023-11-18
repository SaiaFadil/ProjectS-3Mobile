package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.StatusSenimanDiajukanAdapter;
import com.example.usingpreferences.Adapter.StatusSenimanDiprosesAdapter;
import com.example.usingpreferences.Adapter.StatusSenimanDiterimaAdapter;
import com.example.usingpreferences.Adapter.StatusSenimanDitolakAdapter;
import com.example.usingpreferences.DataModel.ModelStatusSenimanDiajukan;
import com.example.usingpreferences.DataModel.ModelStatusSenimanDiproses;
import com.example.usingpreferences.DataModel.ModelStatusSenimanDiterima;
import com.example.usingpreferences.DataModel.ModelStatusSenimanDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusSenimanDitolak;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusSeniman extends Fragment {


    public static ShimmerFrameLayout mFrameLayout;
    public LinearLayout mDataSemua;
    private RecyclerView recviewdiajukan, recviewdiproses, recviewditolak, recviewditerima;
    private StatusSenimanDiajukanAdapter adapterdiajukan;
    private StatusSenimanDiprosesAdapter adapterdiproses;
    private StatusSenimanDitolakAdapter adapterditolak;
    private StatusSenimanDiterimaAdapter adapterditerima;
    public static Animation fadeIn;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_status_seniman, container, false);
        mDataSemua = view.findViewById(R.id.data_view);
        mFrameLayout = view.findViewById(R.id.shimmer_view);
        fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.tampil_data_sshimer);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        TampilStatus();
    }

    public static Handler handler = new Handler();



    public void TampilStatus(){

        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_user  = sharedPreferences.getString("id_user", "");



//ini awal recview diajukan
        recviewdiajukan = view.findViewById(R.id.recyclerViewStatusSenimanDiajukan);
        recviewdiajukan.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusSenimanDiajukan> calldiajukan = ardData.getStatusSenimanDiajukan(id_user);
        calldiajukan.enqueue(new Callback<ResponseStatusSenimanDiajukan>() {
            @Override
            public void onResponse(Call<ResponseStatusSenimanDiajukan> call, Response<ResponseStatusSenimanDiajukan> response) {
                if (response.isSuccessful()) {
                    ResponseStatusSenimanDiajukan responseModel = response.body();
                    List<ModelStatusSenimanDiajukan> data = responseModel.getData();
                    adapterdiajukan = new StatusSenimanDiajukanAdapter(data);
                    if (adapterdiajukan == null){
                        mFrameLayout.startShimmer();
                    }else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.startAnimation(fadeIn);
                    }
                    recviewdiajukan.setAdapter(adapterdiajukan);

                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusSenimanDiajukan> call, Throwable t) {
                Toast.makeText(getContext(), "Kesalahan Pada Server\nHarap Periksa Koneksi Anda", Toast.LENGTH_SHORT).show();
            }
        });
//ini akhir recview diajukan


//ini awal recview diproses
        recviewdiproses = view.findViewById(R.id.recyclerViewStatusSenimanDiproses); // Ubah ID menjadi recyclerViewStatusSenimanDiproses
        recviewdiproses.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusSenimanDiproses> calldiproses = ardData.getStatusSenimanDiproses(id_user);
        calldiproses.enqueue(new Callback<ResponseStatusSenimanDiproses>() {
            @Override
            public void onResponse(Call<ResponseStatusSenimanDiproses> call, Response<ResponseStatusSenimanDiproses> response) {
                if (response.isSuccessful()) {
                    ResponseStatusSenimanDiproses responseModel = response.body();
                    List<ModelStatusSenimanDiproses> data = responseModel.getData();
                    adapterdiproses = new StatusSenimanDiprosesAdapter(data);
                    recviewdiproses.setAdapter(adapterdiproses);
                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusSenimanDiproses> call, Throwable t) {
            }
        });
//ini akhir recview diproses

//ini awal recview ditolak

        recviewditolak = view.findViewById(R.id.recyclerViewStatusSenimanDitolak); // Ubah ID menjadi recyclerViewStatusSenimanDitolak
        recviewditolak.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusSenimanDitolak> callditolak = ardData.getStatusSenimanDitolak(id_user);
        callditolak.enqueue(new Callback<ResponseStatusSenimanDitolak>() {
            @Override
            public void onResponse(Call<ResponseStatusSenimanDitolak> call, Response<ResponseStatusSenimanDitolak> response) {
                if (response.isSuccessful()) {
                    ResponseStatusSenimanDitolak responseModel = response.body();
                    List<ModelStatusSenimanDitolak> data = responseModel.getData();
                    adapterditolak = new StatusSenimanDitolakAdapter(data);
                    recviewditolak.setAdapter(adapterditolak);
                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusSenimanDitolak> call, Throwable t) {
            }
        });
//ini akhir recview ditolak


//ini awal recview diterima
        recviewditerima = view.findViewById(R.id.recyclerViewStatusSenimanDiterima); // Ubah ID menjadi recyclerViewStatusSenimanDiterima
        recviewditerima.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusSenimanDiterima> callditerima = ardData.getStatusSenimanDiterima(id_user);
        callditerima.enqueue(new Callback<ResponseStatusSenimanDiterima>() {
            @Override
            public void onResponse(Call<ResponseStatusSenimanDiterima> call, Response<ResponseStatusSenimanDiterima> response) {
                if (response.isSuccessful()) {
                    ResponseStatusSenimanDiterima responseModel = response.body();
                    List<ModelStatusSenimanDiterima> dataditerima = responseModel.getData();
                    adapterditerima = new StatusSenimanDiterimaAdapter(dataditerima);
                    recviewditerima.setAdapter(adapterditerima);
                } else {
                    Toast.makeText(getContext(), response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusSenimanDiterima> call, Throwable t) {
            }
        });
//ini akhir recview diterima


    }



}
