package com.example.usingpreferences.MenuFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.usingpreferences.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BannerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BannerFragment extends Fragment {
    private static final String ARG_IMAGE_RES = "imageRes";

    public BannerFragment() {
    }

    public static BannerFragment newInstance(int imageRes) {
        BannerFragment fragment = new BannerFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_IMAGE_RES, imageRes);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_banner, container, false);
        ImageView imageView = view.findViewById(R.id.imageView);
        int imageRes = getArguments().getInt(ARG_IMAGE_RES);
        imageView.setImageResource(imageRes);
        return view;
    }
}