package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.usingpreferences.Activity.DetailEventDashboard;
import com.example.usingpreferences.Activity.NoInduk2;
import com.example.usingpreferences.Activity.PinjamTempatList;
import com.example.usingpreferences.Activity.ProfilActivity;
import com.example.usingpreferences.Adapter.DashboardAdapter;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiAwalEvent;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiKeAdvis;
import com.example.usingpreferences.R;
import com.google.android.material.card.MaterialCardView;

public class HomeFragment extends Fragment {

    TextView tv_namauser, tv_namausertengah,textpemberitahuanlayanan,tv_eventTerkini;
    private Animation fadeIn,fadeIndown,layoutdown;
    CardView cardviewatas, cardviewtengah, cardizin, cardevent, cardpinjam, cardinduk;
    ScrollView scrollView;
    MaterialCardView card1;
    LinearLayout layoutevent,linearpager;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        viewPager = view.findViewById(R.id.viewPager);
        DashboardAdapter adapter = new DashboardAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        linearpager = view.findViewById(R.id.linear1);
        tv_eventTerkini = view.findViewById(R.id.tv_eventTerkini);
        textpemberitahuanlayanan = view.findViewById(R.id.textpemberitahuanlayanan);
        scrollView = view.findViewById(R.id.scrollviewid);
        tv_namauser = view.findViewById(R.id.namauserhome);
        tv_namausertengah = view.findViewById(R.id.namauserhometengah);
        cardviewatas = view.findViewById(R.id.cardviewatas);
        cardviewtengah = view.findViewById(R.id.cardviewtengah);
        cardpinjam = view.findViewById(R.id.cardpinjam);
        cardevent = view.findViewById(R.id.cardevent);
        cardinduk = view.findViewById(R.id.cardinduk);
        cardizin = view.findViewById(R.id.cardizin);
        card1 = view.findViewById(R.id.card1);
        layoutevent = view.findViewById(R.id.layoutevent);
        layoutdown = AnimationUtils.loadAnimation(requireContext(), R.anim.layout_in);
        fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
        fadeIndown = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_down);
        tv_eventTerkini.startAnimation(fadeIndown);
        textpemberitahuanlayanan.startAnimation(fadeIndown);
        cardpinjam.startAnimation(fadeIndown);
        cardevent.startAnimation(fadeIndown);
        cardinduk.startAnimation(fadeIndown);
        cardizin.startAnimation(fadeIndown);
        cardviewtengah.startAnimation(fadeIndown);
        card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), DetailEventDashboard.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        cardpinjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), PinjamTempatList.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        cardevent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KonfirmasiAwalEvent.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        cardinduk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), NoInduk2.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        cardizin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), KonfirmasiKeAdvis.class));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");
        tv_namauser.setText(namaLengkap);
        tv_namausertengah.setText(namaLengkap);
        ImageButton keprofil = view.findViewById(R.id.keprofil);
        ImageButton keprofiltengah = view.findViewById(R.id.keprofiltengah);
        keprofil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        keprofiltengah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), ProfilActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                int posisiY = scrollView.getScrollY();
                if (posisiY > 470) {
                    cardviewatas.setVisibility(View.VISIBLE);
                    cardviewtengah.setVisibility(View.INVISIBLE);
                    cardviewtengah.startAnimation(fadeIn);
                } else if (posisiY < 470) {
                    cardviewatas.setVisibility(View.GONE);
                    cardviewtengah.setVisibility(View.VISIBLE);
                }
                if (posisiY < 70) {
                    layoutevent.startAnimation(fadeIndown);
                }
            }
        });
    }
}
