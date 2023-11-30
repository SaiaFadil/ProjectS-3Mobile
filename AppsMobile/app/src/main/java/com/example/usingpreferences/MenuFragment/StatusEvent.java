package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Adapter.StatusEventAdapter;
import com.example.usingpreferences.DataModel.ResponseStatusEvent;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatusEvent#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusEvent extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatusEvent() {
        // Required empty public constructor
    }


    public static StatusEvent newInstance(String param1, String param2) {
        StatusEvent fragment = new StatusEvent();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_status_event, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.se_recycler);
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String id_user = sharedPreferences.getString("id_user", "");


        RetroServer.getInstance().getStatus(id_user).enqueue(new Callback<ResponseStatusEvent>() {
            @Override
            public void onResponse(Call<ResponseStatusEvent> call, Response<ResponseStatusEvent> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){

                    if (isAdded()){
                        recyclerView.setAdapter(new StatusEventAdapter(
                                requireActivity(),
                                response.body().getData()
                        ));
                    }

                }else {
//                    Toast.makeText(requireContext(), response.body().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseStatusEvent> call, Throwable t) {
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }
}