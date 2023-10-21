package com.example.usingpreferences.MenuFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usingpreferences.Activity.FormStatusDiproses;
import com.example.usingpreferences.Activity.FormStatusDiterima;
import com.example.usingpreferences.Activity.FormStatusDitolak;
import com.example.usingpreferences.Activity.FormStatusDiajukan;
import com.google.android.material.card.MaterialCardView;
import com.example.usingpreferences.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatusSemua#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatusSemua extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatusSemua() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StatusSemua.
     */
    // TODO: Rename and change types and number of parameters
    public static StatusSemua newInstance(String param1, String param2) {
        StatusSemua fragment = new StatusSemua();
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
        View view = inflater.inflate(R.layout.fragment_status_semua, container, false);

        MaterialCardView cardditerima = view.findViewById(R.id.cardDiterima);
        MaterialCardView cardditolak = view.findViewById(R.id.cardditolak);
        MaterialCardView carddiajukan = view.findViewById(R.id.cardDiajukan);
        MaterialCardView carddiproses = view.findViewById(R.id.cardproses);
        carddiproses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(getActivity(), FormStatusDiproses.class);
                startActivity(pindah);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        cardditerima.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(getActivity(), FormStatusDiterima.class);
                startActivity(pindah);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
         cardditolak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(getActivity(), FormStatusDitolak.class);
                startActivity(pindah);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });


        carddiajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(getActivity(), FormStatusDitolak.class);
                startActivity(pindah);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        carddiajukan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent pindah = new Intent(getActivity(), FormStatusDiajukan.class);
                startActivity(pindah);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        return view;
    }
}