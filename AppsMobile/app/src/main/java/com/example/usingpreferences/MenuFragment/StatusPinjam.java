package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.StatusPinjamDiajukanAdapter;
import com.example.usingpreferences.Adapter.StatusPinjamDiprosesAdapter;
import com.example.usingpreferences.Adapter.StatusPinjamDiterimaAdapter;
import com.example.usingpreferences.Adapter.StatusPinjamDitolakAdapter;
import com.example.usingpreferences.DataModel.ModelStatusPinjamDiajukan;
import com.example.usingpreferences.DataModel.ModelStatusPinjamDiproses;
import com.example.usingpreferences.DataModel.ModelStatusPinjamDiterima;
import com.example.usingpreferences.DataModel.ModelStatusPinjamDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusPinjamDitolak;
import com.example.usingpreferences.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPinjam extends Fragment {

    private RecyclerView recviewdiajukan,recviewdiproses,recviewditolak,recviewditerima;
    private StatusPinjamDiajukanAdapter adapterdiajukan;
    private StatusPinjamDiprosesAdapter adapterdiproses;
    private StatusPinjamDitolakAdapter adapterditolak;
    private StatusPinjamDiterimaAdapter adapterditerima;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_status_tempat, container, false);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_user = sharedPreferences.getString("id_user", "");

        //ini awal recview diajukan
        recviewdiajukan = view.findViewById(R.id.recyclerViewStatusPinjamDiajukan);
        recviewdiajukan.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusPinjamDiajukan> calldiajukan = ardData.getStatusPinjamDiajukan(id_user);
        calldiajukan.enqueue(new Callback<ResponseStatusPinjamDiajukan>() {
            @Override
            public void onResponse(Call<ResponseStatusPinjamDiajukan> call, Response<ResponseStatusPinjamDiajukan> response) {
                if (response.isSuccessful()) {
                    //                    if (isAdded()) {
                        ResponseStatusPinjamDiajukan responseModel = response.body();
                        List<ModelStatusPinjamDiajukan> data = responseModel.getData();
                        adapterdiajukan = new StatusPinjamDiajukanAdapter(requireContext(), data);
                        recviewdiajukan.setAdapter(adapterdiajukan);
//                    }

                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseStatusPinjamDiajukan> call, Throwable t) {
                Toast.makeText(getContext(), "gagal : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
//ini akhir recview diajukan

//ini awal recview diproses
        recviewdiproses = view.findViewById(R.id.recyclerViewStatusPinjamDiproses); // Ubah ID menjadi recyclerViewStatusPinjamDiproses
        recviewdiproses.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusPinjamDiproses> calldiproses = ardData.getStatusPinjamDiproses(id_user);
        calldiproses.enqueue(new Callback<ResponseStatusPinjamDiproses>() {
            @Override
            public void onResponse(Call<ResponseStatusPinjamDiproses> call, Response<ResponseStatusPinjamDiproses> response) {
                if (response.isSuccessful()) {
                    ResponseStatusPinjamDiproses responseModel = response.body();
                    List<ModelStatusPinjamDiproses> data = responseModel.getData();
                    adapterdiproses = new StatusPinjamDiprosesAdapter(data);
                    recviewdiproses.setAdapter(adapterdiproses);
                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusPinjamDiproses> call, Throwable t) {
                Toast.makeText(getContext(), "gagal : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
//ini akhir recview diproses

//ini awal recview ditolak
        recviewditolak = view.findViewById(R.id.recyclerViewStatusPinjamDitolak); // Ubah ID menjadi recyclerViewStatusPinjamDitolak
        recviewditolak.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusPinjamDitolak> callditolak = ardData.getStatusPinjamDitolak(id_user);
        callditolak.enqueue(new Callback<ResponseStatusPinjamDitolak>() {
            @Override
            public void onResponse(Call<ResponseStatusPinjamDitolak> call, Response<ResponseStatusPinjamDitolak> response) {
                                    ResponseStatusPinjamDitolak responseModel = response.body();
                                    List<ModelStatusPinjamDitolak> data = responseModel.getData();
                                    adapterditolak = new StatusPinjamDitolakAdapter(requireContext(), data);
                                    recviewditolak.setAdapter(adapterditolak);
            }
            @Override
            public void onFailure(Call<ResponseStatusPinjamDitolak> call, Throwable t) {
                t.printStackTrace();
//                Toast.makeText(getContext(), "gagal : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
//ini akhir recview ditolak

//ini awal recview diterima
        recviewditerima = view.findViewById(R.id.recyclerViewStatusPinjamDiterima); // Ubah ID menjadi recyclerViewStatusPinjamDiterima
        recviewditerima.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusPinjamDiterima> callditerima = ardData.getStatusPinjamDiterima(id_user);
        callditerima.enqueue(new Callback<ResponseStatusPinjamDiterima>() {
            @Override
            public void onResponse(Call<ResponseStatusPinjamDiterima> call, Response<ResponseStatusPinjamDiterima> response) {
                if (response.isSuccessful()) {
//                    if (isAdded()){
                        ResponseStatusPinjamDiterima responseModel = response.body();
                        List<ModelStatusPinjamDiterima> dataditerima = responseModel.getData();
                        adapterditerima = new StatusPinjamDiterimaAdapter(requireContext(), dataditerima);
                        recviewditerima.setAdapter(adapterditerima);
//                    }
                } else {
                    Toast.makeText(getContext(), "error " + response.message(), Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<ResponseStatusPinjamDiterima> call, Throwable t) {
                Toast.makeText(getContext(), "gagal : " + t.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
//ini akhir recview diterima
        return view;
    }


}


