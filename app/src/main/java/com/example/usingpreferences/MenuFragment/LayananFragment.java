package com.example.usingpreferences.MenuFragment;

import android.content.Intent;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.usingpreferences.Activity.NoInduk1;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiFormulirAdvis;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiKeAdvis;
import com.example.usingpreferences.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LayananFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LayananFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LayananFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LayananFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LayananFragment newInstance(String param1, String param2) {
        LayananFragment fragment = new LayananFragment();
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
        View rootView = inflater.inflate(R.layout.fragment_layanan, container, false);

        CardView btninduk = (CardView) rootView .findViewById(R.id.cardviewlayanan);
        btninduk.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),NoInduk1.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });

        CardView btnadvis = rootView .findViewById(R.id.cardviewlayananadvis);
        btnadvis.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), KonfirmasiKeAdvis.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        return rootView;
    }
}