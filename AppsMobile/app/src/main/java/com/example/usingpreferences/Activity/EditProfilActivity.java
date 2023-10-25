package com.example.usingpreferences.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.ModelUpdateProfil;
import com.example.usingpreferences.DataModel.ModelUsers;
import com.example.usingpreferences.R;
import com.google.android.material.button.MaterialButton;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditProfilActivity extends AppCompatActivity {
    private DatePickerDialog picker;
    private EditText tanggallahiredit;
    private Spinner genderSpinner;
    private EditText nama_lengkap, no_telpon, tempat_lahir, email;
    private TextView id_user;
    MaterialButton simpanupdate, batalupdate;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profil);

        progressDialog = new ProgressDialog(EditProfilActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Harap Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);

        simpanupdate = findViewById(R.id.simpanedit);
        batalupdate = findViewById(R.id.bataledit);
        id_user = findViewById(R.id.iduseredit);
        nama_lengkap = findViewById(R.id.namalengkapedit);
        no_telpon = findViewById(R.id.teleponedit);
        genderSpinner = findViewById(R.id.gender_spinneredit);
        tempat_lahir = findViewById(R.id.tempatlahiredit);
        email = findViewById(R.id.emailedit);
        tanggallahiredit = findViewById(R.id.tanggaledit);
        ImageButton profilback = findViewById(R.id.profilback);
        profilback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        InputFilter noWhiteSpaceFilter = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                for (int i = start; i < end; i++) {
                    if (Character.isWhitespace(source.charAt(i))) {
                        return ""; //gantiin spasi dengan string kosong
                    }
                }
                return null; // nothing perubahan
            }
        };
        no_telpon.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        email.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        tanggallahiredit.setInputType(InputType.TYPE_NULL);
        tanggallahiredit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int tgl = cldr.get(Calendar.DAY_OF_MONTH);
                int bulan = cldr.get(Calendar.MONTH);
                int tahun = cldr.get(Calendar.YEAR);

                picker = new DatePickerDialog(EditProfilActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        tanggallahiredit.setText(year + "-" + (month + 1) + "-" + dayOfMonth);
                    }
                }, tahun, bulan, tgl);
                picker.show();
            }
        });
        genderSpinner = findViewById(R.id.gender_spinneredit);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.jenis_kelamin_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        genderSpinner.setAdapter(adapter);

        batalupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowData();
            }
        });
        ShowData();//Tampil
        simpanupdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari input pengguna
                String idUser = id_user.getText().toString().trim();
                String namaLengkap = nama_lengkap.getText().toString().trim();
                String noTelpon = no_telpon.getText().toString().trim();
                String tempatLahir = tempat_lahir.getText().toString().trim();
                String tanggalLahir = tanggallahiredit.getText().toString().trim();
                String emailteks = email.getText().toString().trim();
                String selectedGender = genderSpinner.getSelectedItem().toString();

                // Ambil data dari SharedPreferences
                SharedPreferences sharedPreferences = EditProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                String idUserShared = sharedPreferences.getString("id_user", "");
                String namaLengkapShared = sharedPreferences.getString("nama_lengkap", "");
                String noTelponShared = sharedPreferences.getString("no_telpon", "");
                String tempatLahirShared = sharedPreferences.getString("tempat_lahir", "");
                String tanggalLahirShared = sharedPreferences.getString("tanggal_lahir", "");
                String emailShared = sharedPreferences.getString("email", "");
                String selectedGenderShared = sharedPreferences.getString("jenis_kelamin", "");




                // Mulai validasi
                if (idUser.equals(idUserShared) &&
                        namaLengkap.equals(namaLengkapShared) &&
                        noTelpon.equals(noTelponShared) &&
                        tempatLahir.equals(tempatLahirShared) &&
                        tanggalLahir.equals(tanggalLahirShared) &&
                        emailteks.equals(emailShared) &&
                        selectedGender.equals(selectedGenderShared)) {

                    // Tidak ada perubahan
                    Toast.makeText(EditProfilActivity.this, "Tidak ada perubahan", Toast.LENGTH_SHORT).show();
                } else {
                    // Ada perubahan, lanjutkan dengan pembaruan data
                    if (idUser.isEmpty() || namaLengkap.isEmpty() || noTelpon.isEmpty() || tempatLahir.isEmpty() ||
                            tanggalLahir.isEmpty() || emailteks.isEmpty() || selectedGender.equals("Pilih Jenis Kelamin")) {

                        Toast.makeText(EditProfilActivity.this, "Semua kolom harus diisi", Toast.LENGTH_SHORT).show();
                    } else if (!emailteks.endsWith("@gmail.com")) {
                        email.setError("Email Tidak Valid!");
                        email.requestFocus();
                    } else if (noTelpon.length() <= 10) {
                        no_telpon.setError("No Telpon minimal 11 angka");
                        no_telpon.requestFocus();
                    } else if (!noTelpon.startsWith("08")) {
                        no_telpon.setError("Nomor Telepon Tidak Valid");
                        no_telpon.requestFocus();
                    } else if (noTelpon.length() >= 15) {
                        no_telpon.setError("No Telpon Maksimum 15");
                    } else {


                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                        builder.setMessage("Apakah Anda Yakin Mengubah Profil Anda?");
                        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Tampilkan ProgressDialog sebelum penghapusan


                                APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                                Call<ModelUpdateProfil> call = ardData.updateUser(idUser, namaLengkap, noTelpon, selectedGender, tanggalLahir, tempatLahir, emailteks);
                                call.enqueue(new Callback<ModelUpdateProfil>() {
                                    @Override
                                    public void onResponse(Call<ModelUpdateProfil> call, Response<ModelUpdateProfil> response) {
                                        if (response.isSuccessful()) {
                                            ModelUpdateProfil result = response.body();
                                            if (result != null && result.getKode() == 1) {
                                                progressDialog.show();
                                                new Handler().postDelayed(new Runnable() {
                                                    @Override
                                                    public void run() {
                                                        AlertDialog.Builder builder = new AlertDialog.Builder(EditProfilActivity.this);
                                                        builder.setMessage("Data Berhasil Disimpan")
                                                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                                                    public void onClick(DialogInterface dialog, int id) {

                                                                        SharedPreferences sharedPreferencesedit = EditProfilActivity.this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
                                                                        SharedPreferences.Editor editor = sharedPreferencesedit.edit();

                                                                        editor.putString("id_user", idUser);
                                                                        editor.putString("nama_lengkap", namaLengkap);
                                                                        editor.putString("no_telpon", noTelpon);
                                                                        editor.putString("tempat_lahir", tempatLahir);
                                                                        editor.putString("tanggal_lahir", tanggalLahir);
                                                                        editor.putString("email", emailteks);
                                                                        editor.putString("jenis_kelamin", selectedGender);

                                                                        editor.apply(); // Simpan perubahan
                                                                        // Panggil ShowData untuk menampilkan data yang telah diperbarui
                                                                        ShowData();
                                                                        dialog.dismiss();
                                                                    }
                                                                });

                                                        AlertDialog dialog = builder.create();
                                                        dialog.show();

                                                        // Tutup ProgressDialog
                                                        progressDialog.dismiss();

                                                    }
                                                }, 2000);

                                                dialog.dismiss();

                                            } else {
                                                // Update gagal
                                                Toast.makeText(EditProfilActivity.this, "Update gagal", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            // Kesalahan saat komunikasi dengan server
                                            Toast.makeText(EditProfilActivity.this, "" + response, Toast.LENGTH_SHORT).show();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<ModelUpdateProfil> call, Throwable t) {
                                        // Kesalahan jaringan atau kesalahan lainnya
                                        Toast.makeText(EditProfilActivity.this, "Kesalahan: " + t.getMessage(), Toast.LENGTH_SHORT).show();
                                    }
                                });
                            }

                        });
                        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();


                    }
                }
            }
        });



    }

    public void onBackPressed() {
        finish();
        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
    }

    private void ShowData() {
        SharedPreferences sharedPreferences = this.getSharedPreferences("prefLogin", Context.MODE_PRIVATE);
        String iduserget = sharedPreferences.getString("id_user", "");
        String namaLengkapget = sharedPreferences.getString("nama_lengkap", "");
        String noteleponget = sharedPreferences.getString("no_telpon", "");
        String jeniskelaminget = sharedPreferences.getString("jenis_kelamin", "");
        String tempatlahirget = sharedPreferences.getString("tempat_lahir", "");
        String tgllahir = sharedPreferences.getString("tanggal_lahir", "");
        String emailget = sharedPreferences.getString("email", "");

        id_user.setText(iduserget);
        nama_lengkap.setText(namaLengkapget);
        no_telpon.setText(noteleponget);

        if (jeniskelaminget.endsWith("aki-laki")) {
            genderSpinner.setSelection(1);
        } else if (jeniskelaminget.endsWith("erempuan")) {
            genderSpinner.setSelection(2);
        }
        tempat_lahir.setText(tempatlahirget);
        tanggallahiredit.setText(tgllahir);
        email.setText(emailget);


    }


}