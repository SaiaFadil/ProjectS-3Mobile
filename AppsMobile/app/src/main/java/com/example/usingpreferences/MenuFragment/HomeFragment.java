package com.example.usingpreferences.MenuFragment;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager.widget.ViewPager;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.Activity.EventLainyaFragment;
import com.example.usingpreferences.Activity.NoInduk1;
import com.example.usingpreferences.Activity.PinjamTempatList;
import com.example.usingpreferences.Activity.ProfilActivity;
import com.example.usingpreferences.Adapter.DashboardAdapter;
import com.example.usingpreferences.DataModel.EventHomeAdapter;
import com.example.usingpreferences.DataModel.EventHomeResponse;
import com.example.usingpreferences.DataModel.ModelResponseSimpanDataSeniman;
import com.example.usingpreferences.DataModel.ModelSimpanDataSeniman;
import com.example.usingpreferences.Eksternal.NotifService;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiAwalEvent;
import com.example.usingpreferences.KonfirmMenu.KonfirmasiKeAdvis;
import com.example.usingpreferences.R;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.imageview.ShapeableImageView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {
    private SwipeRefreshLayout swipeRefreshLayout;
    private boolean isAlertDialogShown = false;
    private ProgressBar progressBar;
    TextView tv_namauser, tv_namausertengah, textpemberitahuanlayanan, tv_eventTerkini;
    private Animation fadeIn, fadeIndown, layoutdown, layoutin;
    CardView cardviewatas, cardviewtengah, cardizin, cardevent, cardpinjam, cardinduk;
    ScrollView scrollView;
    private TextView petanganjukteks;
    MaterialCardView card1;
    LinearLayout layoutevent, linearpager;
    MaterialCardView petanganjukgambar;
    private MotionEvent event;
    private float startY;
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private ViewPager viewPager;
    private Handler handler;
    private final int CHECK_INTERVAL = 5000;

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


        Intent workIntentditerima = new Intent(getActivity(), NotifService.class);
        NotifService.enqueueWork(getContext(), workIntentditerima);


        swipeRefreshLayout = view.findViewById(R.id.swipeRefreshLayout);
        progressBar = view.findViewById(R.id.progressBar);
        fadeIndown = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in_down);
        handler = new Handler();
        checkInternetConnection();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                simpanDataSeniman();
                MulaiAnimasi();
                checkInternetConnection();
                ShowData();
                progressBar.setVisibility(View.GONE);
                swipeRefreshLayout.setRefreshing(false);
            }
        });

        viewPager = view.findViewById(R.id.viewPager);
        DashboardAdapter adapter = new DashboardAdapter(getChildFragmentManager());
        viewPager.setAdapter(adapter);
        viewPager.setCurrentItem(3);
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
        petanganjukteks = view.findViewById(R.id.petanganjukteks);
        petanganjukgambar = view.findViewById(R.id.petanganjukgambar);
        layoutevent = view.findViewById(R.id.layoutevent);
        layoutdown = AnimationUtils.loadAnimation(requireContext(), R.anim.layout_in);
        fadeIn = AnimationUtils.loadAnimation(requireContext(), R.anim.fade_in);
//        simpanDataSeniman();

        scrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY == 0) {
                    swipeRefreshLayout.setEnabled(true);
                } else {
                    swipeRefreshLayout.setEnabled(false);
                }
            }
        });

        petanganjukgambar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String linkmap = "https://www.google.com/maps/d/embed?mid=1IYUlu0sTwMb5QQ10uwPV8sjADGdAvBEk&ehbc=2E312F";
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(linkmap));
                getActivity().overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                startActivity(intent);
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
                startActivity(new Intent(getActivity(), NoInduk1.class));
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

        ShowData();
        ShapeableImageView keprofil = view.findViewById(R.id.keprofil);
        ShapeableImageView keprofiltengah = view.findViewById(R.id.keprofiltengah);
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        view.findViewById(R.id.txt_lainya).setOnClickListener(v -> {
            requireActivity().getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new EventLainyaFragment())
                    .commit();
        });
        RecyclerView recyclerView = view.findViewById(R.id.event_home);
        RetroServer.getInstance().getEventHome().enqueue(new Callback<EventHomeResponse>() {
            @Override
            public void onResponse(Call<EventHomeResponse> call, Response<EventHomeResponse> response) {
                if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")){

                    recyclerView.setAdapter(new EventHomeAdapter(
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
                    petanganjukgambar.startAnimation(fadeIndown);
                }
                if (posisiY < 100) {
                    layoutevent.startAnimation(fadeIndown);
                }
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        ShowData();

    }

    private void MulaiAnimasi() {
        tv_eventTerkini.startAnimation(fadeIndown);
        textpemberitahuanlayanan.startAnimation(fadeIndown);
        cardpinjam.startAnimation(fadeIndown);
        cardevent.startAnimation(fadeIndown);
        cardinduk.startAnimation(fadeIndown);
        cardizin.startAnimation(fadeIndown);
        cardviewtengah.startAnimation(fadeIn);
        cardviewtengah.setVisibility(View.VISIBLE);
        petanganjukteks.startAnimation(fadeIndown);
        petanganjukgambar.startAnimation(fadeIndown);
    }

    public void simpanDataSeniman() {
        SharedPreferences sharedPreferences = getActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String idUserShared = sharedPreferences.getString("id_user", "");
        SharedPreferences sharedPreferencesseniman = getActivity().getSharedPreferences("prefDataSeniman", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferencesseniman.edit();
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ModelResponseSimpanDataSeniman> getLoginResponse = ardData.SimpanDataSeniman(idUserShared);
        getLoginResponse.enqueue(new Callback<ModelResponseSimpanDataSeniman>() {
            @Override
            public void onResponse(Call<ModelResponseSimpanDataSeniman> call, Response<ModelResponseSimpanDataSeniman> response) {
                if (response.body().kode == 1) {
                    ModelSimpanDataSeniman Seniman = response.body().getData();
                    editor.putString("id_seniman", Seniman.getId_seniman());
                    editor.putString("nik", Seniman.getNik());
                    editor.putString("nomor_induk", Seniman.getNomor_induk());
                    editor.putString("nama_seniman", Seniman.getNama_seniman());
                    editor.putString("jenis_kelamin", Seniman.getJenis_kelamin());
                    editor.putString("singkatan_kategori", Seniman.getsingkatan_kategori());
                    editor.putString("kecamatan", Seniman.getKecamatan());
                    editor.putString("tempat_lahir", Seniman.getTempat_lahir());
                    editor.putString("tanggal_lahir", Seniman.getTanggal_lahir());
                    editor.putString("alamat_seniman", Seniman.getAlamat_seniman());
                    editor.putString("no_telpon", Seniman.getNo_telpon());
                    editor.putString("nama_organisasi", Seniman.getNama_organisasi());
                    editor.putString("jumlah_anggota", Seniman.getJumlah_anggota());
                    editor.putString("ktp_seniman", Seniman.getKtp_seniman());
                    editor.putString("pass_foto", Seniman.getPass_foto());
                    editor.putString("surat_keterangan", Seniman.getSurat_keterangan());
                    editor.putString("tgl_pembuatan", Seniman.getTgl_pembuatan());
                    editor.putString("tgl_berlaku", Seniman.getTgl_berlaku());
                    editor.putString("status", Seniman.getStatus());
                    editor.putString("catatan", Seniman.getCatatan());
                    editor.putString("id_user", Seniman.getId_user());
                    editor.apply();
                } else if (response.body().kode == 0) {
                    // Penanganan jika data tidak ditemukan
                }
            }

            @Override
            public void onFailure(Call<ModelResponseSimpanDataSeniman> call, Throwable t) {
//No respon
            }
        });
    }

    private void ShowData() {
        SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String namaLengkap = sharedPreferences.getString("nama_lengkap", "");

        tv_namauser.setText(namaLengkap);
        tv_namausertengah.setText(namaLengkap);
    }


    private boolean isConnectedToInternet() {
        ConnectivityManager connectivityManager = (ConnectivityManager) requireContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    private void checkInternetConnection() {
        if (!isConnectedToInternet() && !isAlertDialogShown) {
            showAlertDialog();
            this.isAlertDialogShown = true;
        }
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        isAlertDialogShown = false;
                        checkInternetConnection();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        handler.removeCallbacksAndMessages(null);
    }
}
