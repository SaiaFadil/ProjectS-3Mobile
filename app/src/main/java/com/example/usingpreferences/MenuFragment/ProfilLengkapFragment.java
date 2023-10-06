package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.usingpreferences.R;
import com.example.usingpreferences.Activity.UploadGambarActivity;
import com.google.android.material.button.MaterialButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfilLengkapFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfilLengkapFragment extends Fragment {
    private ImageButton kembali;
    private TextView tvNama,tvEmail,tvNotelp,tvTTL;


    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfilLengkapFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfilLengkapFragment newInstance(String param1, String param2) {
        ProfilLengkapFragment fragment = new ProfilLengkapFragment();
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
        View view = inflater.inflate(R.layout.fragment_profil_lengkap, container, false);

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        String email = sharedPreferences.getString("email", "");
        String notelpon = sharedPreferences.getString("no_telpon","");
        String Tempat = sharedPreferences.getString("tempat_lahir","");
        String tanggal = sharedPreferences.getString("tanggal_lahir","");

        tvEmail = view.findViewById(R.id.tv_Emaillengkap);
        tvNama = view.findViewById(R.id.tv_namalengkap);
        tvNotelp = view.findViewById(R.id.tv_telponlengkap);
        tvTTL = view.findViewById(R.id.tv_ttllengkap);

        tvEmail.setText(email);
        tvNama.setText(namaLengkap);
        tvNotelp.setText(notelpon);
        tvTTL.setText(Tempat+","+tanggal);
        kembali = view.findViewById(R.id.kembaliprofil);
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment selectedFragment = new ProfilFragment();
                // Mengakses FragmentManager yang berkaitan dengan aktivitas
                FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.frame_layout, selectedFragment)
                        .commit();

            }
        });


        MaterialButton button_uploadfoto = view.findViewById(R.id.button_uploadfoto);
        button_uploadfoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), UploadGambarActivity.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        return view;
    }
}