package com.example.usingpreferences.Activity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelDetailSenimanDiajukan;
import com.example.usingpreferences.DataModel.ResponseDetailSenimanDiajukan;
import com.example.usingpreferences.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormSenimanDiajukan extends AppCompatActivity {
    private TextView tanggalinduk;
    private EditText editTextNIK,editTextNamaLengkap,editTextTL,editTextAlamat,editTextNOHP,editTextNamaOrganisasi,editTextJmlAnggota;
    private Spinner gender_spinner,kecamatan_spinner,kategoriSenimanSpinner,tipeSeniman_spinner;
    private Button editdiajukan, batalkandiajukan;
    private ProgressDialog progressDialog;

    private DatePickerDialog picker;
    public static ShimmerFrameLayout mFrameLayout;
    public LinearLayout mDataSemua;
    public static Animation fadeIn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_seniman_diajukan);
//        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//        et_namalengkapseniman = findViewById(R.id.et_namalengkapseniman);
//        et_tanggalpentasseniman = findViewById(R.id.et_tanggalpentasseniman);
//        et_alamatseniman = findViewById(R.id.et_alamatseniman);
//        et_namapentasseniman = findViewById(R.id.et_namapentasseniman);
//        et_lokasiseniman = findViewById(R.id.et_lokasiseniman);
//        mDataSemua = findViewById(R.id.layoutData);
//        mFrameLayout = findViewById(R.id.shimmer_view_detail);

        fadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.tampil_data_sshimer);


        progressDialog = new ProgressDialog(FormSenimanDiajukan.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setIcon(R.drawable.logonganjuk);
        progressDialog.setCancelable(false);
            InputFilter filter = new InputFilter() {
                @Override
                public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                    String regex = "^[a-zA-Z0-9']*";
                    if (source.toString().matches(regex)) {
                        return source;
                    } else {
                        return "";
                    }
                }
            };
//        et_namapentasseniman.setFilters(new InputFilter[]{filter});
//        et_lokasiseniman.setFilters(new InputFilter[]{filter});

//        batalkandiajukan = findViewById(R.id.batalkandiajukan);
//        batalkandiajukan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(FormSenimanDiajukan.this);
//                builder.setMessage("Apakah Anda Yakin Ingin Membatalkan Pengajuan?");
//                builder.setCancelable(false);
//                builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                        progressDialog.show();
//                        String id_seniman = getIntent().getStringExtra("id_seniman");
//                        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
////                        Call<ModelResponseAll> getResponse = ardData.hapussenimanDiajukan(id_seniman);
//                        getResponse.enqueue(new Callback<ModelResponseAll>() {
//                            @Override
//                            public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
//                                if (response.body().getKode() == 1) {
//
//                                    new Handler().postDelayed(new Runnable() {
//                                        @Override
//                                        public void run() {
//                                            Intent intent = new Intent(FormSenimanDiajukan.this, MainActivity.class)
//                                                    .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
//                                            startActivity(intent);
//                                            overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//                                        }
//                                    }, 3000);
//                                }else {
//                                    System.out.println(response.body().getPesan());
//                                }
//                            }
//
//                            @Override
//                            public void onFailure(Call<ModelResponseAll> call, Throwable t) {
//                                progressDialog.dismiss();
//                                showAlertDialog();
//                            }
//                        });
//
//
//                    }
//
//                });
//                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        dialog.dismiss();
//                    }
//                });
//
//                AlertDialog alertDialog = builder.create();
//                alertDialog.show();
//
//
//            }
//        });
//        editdiajukan = findViewById(R.id.editdiajukan);
//        editdiajukan.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String namateks = et_namalengkapseniman.getText().toString().trim();
//                String alamatteks = et_alamatseniman.getText().toString().trim();
//                String untukpentasteks = et_namapentasseniman.getText().toString().trim();
//                String tanggalteks = et_tanggalpentasseniman.getText().toString().trim();
//                String tempatteks = et_lokasiseniman.getText().toString().trim();
//
//
//                if (TextUtils.isEmpty(untukpentasteks)) {
//                    et_namapentasseniman.setError("Untuk Pentas Harus Diisi!");
//                    et_namapentasseniman.requestFocus();
//                } else if (TextUtils.isEmpty(tanggalteks)) {
//                    et_tanggalpentasseniman.setError("Tanggal Pentas Harus Diisi!");
//                    et_tanggalpentasseniman.requestFocus();
//                } else if (TextUtils.isEmpty(tempatteks)) {
//                    et_lokasiseniman.setError("Tempat seniman Harus Diisi!");
//                    et_lokasiseniman.requestFocus();
//                } else {
//                    AlertDialog.Builder builder = new AlertDialog.Builder(FormSenimanDiajukan.this);
//                    builder.setMessage("Apakah Anda Yakin Dengan Data yang anda masukkan?");
//                    builder.setCancelable(false);
//                    builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            // Tampilkan ProgressDialog sebelum penghapusan
//
//                            progressDialog.show();
//                            String id_seniman = getIntent().getStringExtra("id_seniman");
//                            APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
//                            Call<ModelResponseAll> getResponse = ardData.editsenimanDiajukan(id_seniman, untukpentasteks, tanggalteks, tempatteks);
//                            getResponse.enqueue(new Callback<ModelResponseAll>() {
//                                @Override
//                                public void onResponse(Call<ModelResponseAll> call, Response<ModelResponseAll> response) {
//                                    if (response.body().getKode() == 1) {
//
//                                        new Handler().postDelayed(new Runnable() {
//                                            @Override
//                                            public void run() {
//                                                Intent intent = new Intent(FormSenimanDiajukan.this, MainActivity.class)
//                                                        .putExtra(MainActivity.FRAGMENT, R.layout.fragment_status);
//                                                startActivity(intent);
//                                                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
//                                            }
//                                        }, 3000);
//                                    }else {
//                                        System.out.println(response.body().getPesan());
//                                    }
//                                }
//
//                                @Override
//                                public void onFailure(Call<ModelResponseAll> call, Throwable t) {
//                                    progressDialog.dismiss();
//                                    showAlertDialog();
//                                }
//                            });
//
//
//                        }
//
//                    });
//                    builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
//                        @Override
//                        public void onClick(DialogInterface dialog, int which) {
//                            dialog.dismiss();
//                        }
//                    });
//
//                    AlertDialog alertDialog = builder.create();
//                    alertDialog.show();
//
//
//                }
//
//
//            }
//        });
    }

    @Override
    public void onResume() {
        super.onResume();
        showData();

    }

    private void showData() {
        mFrameLayout.startShimmer();
        mDataSemua.setVisibility(View.GONE);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseDetailSenimanDiajukan> getDetail = ardData.getDetailSenimanDiajukan(getIntent().getStringExtra("id_seniman"));
        getDetail.enqueue(new Callback<ResponseDetailSenimanDiajukan>() {
            @Override
            public void onResponse(Call<ResponseDetailSenimanDiajukan> call, Response<ResponseDetailSenimanDiajukan> response) {
                if (response.body().getKode() == 1) {

                    ModelDetailSenimanDiajukan ambildata = response.body().getData();

                    if (ambildata.getId_seniman().isEmpty()) {
                        mFrameLayout.startShimmer();
                        mDataSemua.setVisibility(View.GONE);
                    } else {
                        mFrameLayout.setVisibility(View.GONE);
                        mFrameLayout.stopShimmer();
                        mDataSemua.setVisibility(View.VISIBLE);
                        mDataSemua.startAnimation(fadeIn);
                    }
                    editTextNIK.setText(ambildata.getNik());
                    editTextNamaLengkap.setText(ambildata.getNama_seniman());
//                    gender_spinner.set(ambildata.getAlamat_seniman());

                } else if (response.body().getKode() == 0) {
                    Toast.makeText(FormSenimanDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                } else if (response.body().getKode() == 2) {
                    Toast.makeText(FormSenimanDiajukan.this, response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }
            }


            @Override
            public void onFailure(Call<ResponseDetailSenimanDiajukan> call, Throwable t) {
                showAlertDialog();
            }
        });
    }

    private void showAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(FormSenimanDiajukan.this);
        builder.setMessage("Tidak ada koneksi internet. Harap cek koneksi Anda.")
                .setCancelable(false)
                .setPositiveButton("Tutup", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                        showData();
                    }
                });

        AlertDialog alert = builder.create();
        alert.show();
    }

    public void onBackPressed() {

    }
}