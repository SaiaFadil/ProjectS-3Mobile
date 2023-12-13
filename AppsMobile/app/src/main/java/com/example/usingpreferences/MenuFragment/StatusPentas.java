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
import android.widget.RelativeLayout;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.StatusAdvisDiajukanAdapter;
import com.example.usingpreferences.Adapter.StatusAdvisDiprosesAdapter;
import com.example.usingpreferences.Adapter.StatusAdvisDiterimaAdapter;
import com.example.usingpreferences.Adapter.StatusAdvisDitolakAdapter;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDiajukan;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDiproses;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDiterima;
import com.example.usingpreferences.DataModel.ModelStatusAdvisDitolak;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiajukan;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiproses;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDiterima;
import com.example.usingpreferences.DataModel.ResponseStatusAdvisDitolak;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatusPentas extends Fragment {


    public static ShimmerFrameLayout mFrameLayout;
    public RelativeLayout mDataSemua;
    private RecyclerView recviewdiajukan, recviewdiproses, recviewditolak, recviewditerima;
    private StatusAdvisDiajukanAdapter adapterdiajukan;
    private StatusAdvisDiprosesAdapter adapterdiproses;
    private StatusAdvisDitolakAdapter adapterditolak;
    private StatusAdvisDiterimaAdapter adapterditerima;
    public static Animation fadeIn;
    private View view;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
         view = inflater.inflate(R.layout.fragment_status_pentas, container, false);
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
        recviewdiajukan = view.findViewById(R.id.recyclerViewStatusAdvisDiajukan);
        recviewdiajukan.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusAdvisDiajukan> calldiajukan = ardData.getStatusAdvisDiajukan(id_user);
        calldiajukan.enqueue(new Callback<ResponseStatusAdvisDiajukan>() {
            @Override
            public void onResponse(Call<ResponseStatusAdvisDiajukan> call, Response<ResponseStatusAdvisDiajukan> response) {
                if (response.isSuccessful()) {
                    ResponseStatusAdvisDiajukan responseModel = response.body();
                    List<ModelStatusAdvisDiajukan> data = responseModel.getData();
                    adapterdiajukan = new StatusAdvisDiajukanAdapter(data);
                    if (adapterdiajukan == null){
                        mFrameLayout.startShimmer();
                    }else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.startAnimation(fadeIn);
                    }
                    recviewdiajukan.setAdapter(adapterdiajukan);

                }
            }

            @Override
            public void onFailure(Call<ResponseStatusAdvisDiajukan> call, Throwable t) {

        }});
//ini akhir recview diajuka

//ini awal recview diproses
        recviewdiproses = view.findViewById(R.id.recyclerViewStatusAdvisDiproses); // Ubah ID menjadi recyclerViewStatusAdvisDiproses
        recviewdiproses.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusAdvisDiproses> calldiproses = ardData.getStatusAdvisDiproses(id_user);
        calldiproses.enqueue(new Callback<ResponseStatusAdvisDiproses>() {
            @Override
            public void onResponse(Call<ResponseStatusAdvisDiproses> call, Response<ResponseStatusAdvisDiproses> response) {
                if (response.isSuccessful()) {
                    ResponseStatusAdvisDiproses responseModel = response.body();
                    List<ModelStatusAdvisDiproses> data = responseModel.getData();
                    adapterdiproses = new StatusAdvisDiprosesAdapter(data);
                    recviewdiproses.setAdapter(adapterdiproses);
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusAdvisDiproses> call, Throwable t) {
            }
        });
//ini akhir recview diproses

//ini awal recview ditolak

        recviewditolak = view.findViewById(R.id.recyclerViewStatusAdvisDitolak); // Ubah ID menjadi recyclerViewStatusAdvisDitolak
        recviewditolak.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusAdvisDitolak> callditolak = ardData.getStatusAdvisDitolak(id_user);
        callditolak.enqueue(new Callback<ResponseStatusAdvisDitolak>() {
            @Override
            public void onResponse(Call<ResponseStatusAdvisDitolak> call, Response<ResponseStatusAdvisDitolak> response) {
                if (response.isSuccessful()) {
                    ResponseStatusAdvisDitolak responseModel = response.body();
                    List<ModelStatusAdvisDitolak> data = responseModel.getData();
                    adapterditolak = new StatusAdvisDitolakAdapter(data);
                    recviewditolak.setAdapter(adapterditolak);
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusAdvisDitolak> call, Throwable t) {
            }
        });
//ini akhir recview ditolak


//ini awal recview diterima
        recviewditerima = view.findViewById(R.id.recyclerViewStatusAdvisDiterima); // Ubah ID menjadi recyclerViewStatusAdvisDiterima
        recviewditerima.setLayoutManager(new LinearLayoutManager(requireContext()));
        Call<ResponseStatusAdvisDiterima> callditerima = ardData.getStatusAdvisDiterima(id_user);
        callditerima.enqueue(new Callback<ResponseStatusAdvisDiterima>() {
            @Override
            public void onResponse(Call<ResponseStatusAdvisDiterima> call, Response<ResponseStatusAdvisDiterima> response) {
                if (response.isSuccessful()) {
                    ResponseStatusAdvisDiterima responseModel = response.body();
                    List<ModelStatusAdvisDiterima> dataditerima = responseModel.getData();
                    adapterditerima = new StatusAdvisDiterimaAdapter(dataditerima);
                    recviewditerima.setAdapter(adapterditerima);
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusAdvisDiterima> call, Throwable t) {
            }
        });
        //ini akhir recview diterima
    }
}
