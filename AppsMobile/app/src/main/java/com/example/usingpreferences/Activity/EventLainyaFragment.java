package com.example.usingpreferences.Activity;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.EventHomeLainyaAdapter;
import com.example.usingpreferences.DataModel.EventHomeResponse;
import com.example.usingpreferences.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EventLainyaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventLainyaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EventLainyaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventLainyaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventLainyaFragment newInstance(String param1, String param2) {
        EventLainyaFragment fragment = new EventLainyaFragment();
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
        return inflater.inflate(R.layout.fragment_event_lainya, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView recyclerView = view.findViewById(R.id.event_home_lainya);

        RetroServer.getInstance().getEventHome().enqueue(new Callback<EventHomeResponse>() {
            @Override
            public void onResponse(Call<EventHomeResponse> call, Response<EventHomeResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){

                    recyclerView.setAdapter(new EventHomeLainyaAdapter(
                            response.body().getData()
                    ));

                }else {
                    Toast.makeText(requireContext(), "Error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<EventHomeResponse> call, Throwable t) {
                t.printStackTrace();
                Toast.makeText(requireContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}