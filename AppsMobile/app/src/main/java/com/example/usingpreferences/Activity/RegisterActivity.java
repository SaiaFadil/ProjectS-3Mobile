package com.example.usingpreferences.Activity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.DataModel.CekEmailModel;
import com.example.usingpreferences.DataModel.VerifyResponse;
import com.example.usingpreferences.R;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private TextInputEditText mViewNama, mViewNotlp, mViewEmail, mViewPassword, mViewPassword2;
    private Button mBtnRegister;
    TextView mBtnLogin;
    private TextView tekserror;
    private ProgressDialog progressDialog;
    private Drawable icCircleGreen, icCircleRed;
    private CheckBox tampilkansandi;
    private final boolean[] isPasswordVisible = {false};

    private void setDrawablepw1(Drawable drawable) {
        mViewPassword.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }

    private void setDrawablepw2(Drawable drawable) {
        mViewPassword2.setCompoundDrawablesWithIntrinsicBounds(null, null, drawable, null);
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        tekserror = findViewById(R.id.texterror);
        // Inisialisasi EditText
        mViewNama = findViewById(R.id.et_namaSignup);
        mViewEmail = findViewById(R.id.et_emailSignup);
        mViewNotlp = findViewById(R.id.et_nohpSignup);
        mViewPassword = findViewById(R.id.et_passwordSignup);
        mViewPassword2 = findViewById(R.id.et_passwordSignup2);

// Membuat InputFilter untuk menghilangkan spasi
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

// mengnetapkan InputFilter ke setiap EditText
        mViewNotlp.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        mViewEmail.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        mViewPassword.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        mViewPassword2.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        mBtnRegister = findViewById(R.id.button_signupSignup);
        mBtnLogin = findViewById(R.id.button_signinsignin);
        tampilkansandi = findViewById(R.id.checkboxpw);
        tampilkansandi.setChecked(false);
        mViewPassword.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        mViewPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        progressDialog = new ProgressDialog(RegisterActivity.this);
        progressDialog.setTitle("Data Sedang Diproses...");
        progressDialog.setMessage("Mohon Tunggu...");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        icCircleGreen = getResources().getDrawable(R.drawable.ic_circle_green);
        icCircleRed = getResources().getDrawable(R.drawable.ic_circle_red);
        // Atur listener untuk checkbox
        tampilkansandi.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // Mengubah visibilitas kata sandi berdasarkan status checkbox
                if (isChecked) {
                    mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mViewPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    setDrawablepw1(null);
                    setDrawablepw2(null);
                } else {
                    mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mViewPassword2.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    setDrawablepw1(null);
                    setDrawablepw2(null);
                }

                // Set kursor ke posisi akhir teks
                mViewPassword.setSelection(mViewPassword.getText().length());
            }
        });
        mViewEmail.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean fokuskah) {
                if (!fokuskah) {
                    mViewEmail.setHint(null);
                } else {
                    mViewEmail.setHint("Nama@gmail.com");
                }
            }
        });

        mViewNotlp.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {

                    mViewNotlp.setHint(null);

                } else {
                    mViewNotlp.setHint("Contoh : 0851234567890");

                }
            }
        });
        mBtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getBaseContext(), LoginActivity.class));
                finish();
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
            }
        });
        mViewPassword.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Menghapus drawable saat kehilangan fokus
                    setDrawablepw1(null);
                } else {
                    // Menambahkan listener TextWatcher saat mendapatkan fokus
                    mViewPassword.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (s.length() >= 8 && s.toString().matches(".*[a-zA-Z].*") && s.toString().matches(".*\\d.*")) {
                                setDrawablepw1(icCircleGreen);
                            } else {
                                setDrawablepw1(icCircleRed);
                            }
                        }
                    });
                }
            }
        });
        mViewPassword2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!hasFocus) {
                    // Menghapus drawable saat kehilangan fokus
                    setDrawablepw2(null);
                } else {
                    // Menambahkan listener TextWatcher saat mendapatkan fokus
                    mViewPassword2.addTextChangedListener(new TextWatcher() {
                        @Override
                        public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                        }

                        @Override
                        public void onTextChanged(CharSequence s, int start, int before, int count) {
                        }

                        @Override
                        public void afterTextChanged(Editable s) {
                            if (s.length() >= 8 && s.toString().matches(".*[a-zA-Z].*") && s.toString().matches(".*\\d.*")) {
                                setDrawablepw2(icCircleGreen);
                            } else {
                                setDrawablepw2(icCircleRed);
                            }
                        }
                    });
                }
            }
        });
        mBtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewNama.setError(null);
                mViewNotlp.setError(null);
                mViewEmail.setError(null);
                mViewPassword.setError(null);
                mViewPassword2.setError(null);
                View focus = null;


                String user = mViewNama.getText().toString();
                String notlp = mViewNotlp.getText().toString().trim();
                String email = mViewEmail.getText().toString();
                String password = mViewPassword.getText().toString();
                String confirmPassword = mViewPassword2.getText().toString();
                if (TextUtils.isEmpty(user)) {
                    mViewNama.setError("Nama harus diisi");
                    focus = mViewNama;
                    mViewNama.requestFocus();

                } else if (TextUtils.isEmpty(notlp)) {
                    mViewNotlp.setError("No Telpon harus diisi");
                    focus = mViewNotlp;

                    mViewNotlp.requestFocus();
                } else if (notlp.length() <= 10) {
                    mViewNotlp.setError("No Telpon minimal 11 angka");
                    focus = mViewNotlp;

                    mViewNotlp.requestFocus();
                } else if (!notlp.startsWith("08")) {
                    mViewNotlp.setError("Nomor Telepon Tidak Valid");
                    focus = mViewNotlp;

                    mViewNotlp.requestFocus();
                } else if (notlp.length() >= 15) {
                    mViewNotlp.setError("No Telpon Maksimum 15");
                    focus = mViewNotlp;

                    mViewNotlp.requestFocus();
                    int jumlahnomor = mViewNotlp.length();
                } else if (TextUtils.isEmpty(email)) {
                    mViewEmail.setError("Email harus diisi");
                    focus = mViewEmail;

                    mViewEmail.requestFocus();
                } else if (!email.endsWith("@gmail.com")) {
                    mViewEmail.setError("Email tidak valid!!");
                    focus = mViewEmail;

                    mViewEmail.requestFocus();
                } else if (TextUtils.isEmpty(password)) {
                    mViewPassword.setError("Kata Sandi harus diisi");
                    focus = mViewPassword;

                    mViewPassword.requestFocus();
                } else if (password.length() < 8) {
                    mViewPassword.setError("Kata Sandi minimal 8 karakter");
                    focus = mViewPassword;

                    mViewPassword.requestFocus();
                } else if (!password.matches(".*[a-zA-Z].*") || !password.matches(".*\\d.*")) {
                    mViewPassword.setError("Kata Sandi harus terdiri dari huruf dan angka");
                    focus = mViewPassword;

                    mViewPassword.requestFocus();
                } else if (TextUtils.isEmpty(confirmPassword)) {
                    mViewPassword2.setError("Konfirmasi Kata Sandi harus diisi");
                    focus = mViewPassword2;

                    mViewPassword2.requestFocus();

                } else if (!TextUtils.equals(password, confirmPassword)) {
                    mViewPassword2.setError("Kata Sandi tidak cocok");
                    focus = mViewPassword2;
                    mViewPassword2.requestFocus();
                    tampilkansandi.setChecked(true);

                } else {
                    progressDialog.show();
                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                    Call<CekEmailModel> getLoginResponse = ardData.cekemail(mViewEmail.getText().toString());
                    getLoginResponse.enqueue(new Callback<CekEmailModel>() {
                        @Override
                        public void onResponse(Call<CekEmailModel> call, Response<CekEmailModel> response) {

                            if (response.body().getKode() == 0) {
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        sendOtp();
                                    }
                                }, 1000);


                            } else if (response.body().getKode() == 1) {
                                progressDialog.dismiss();
                                mViewEmail.requestFocus();
                                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                                builder.setMessage("Email Sudah Pernah Digunakan!!")
                                        .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                            public void onClick(DialogInterface dialog, int id) {

                                            }
                                        });
                                AlertDialog dialog1 = builder.create();
                                dialog1.show();
                                progressDialog.dismiss();
                            }

                        }

                        @Override
                        public void onFailure(Call<CekEmailModel> call, Throwable t) {
                            t.printStackTrace();
                            progressDialog.dismiss();
                        }
                    });

                }//ini kurung akhir dari else yg di statement pengecekan ketika memasukkan data
            }
        });//ini akhir dari fungsi tmbol registrasi di klik
    }

    private void sendOtp() {

        RetroServer.getInstance().sendEmail(mViewEmail.getText().toString(), "SignUp", "new", "1")
                .enqueue(new Callback<VerifyResponse>() {
                    @Override
                    public void onResponse(Call<VerifyResponse> call, Response<VerifyResponse> response) {

                        if (response.body() != null && response.body().getStatus().equalsIgnoreCase("success")) {
                            Toast.makeText(RegisterActivity.this, "OTP Terkirim", Toast.LENGTH_SHORT).show();
                            progressDialog.show();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    // Tutup ProgressDialog
                                    progressDialog.dismiss();
                                    String email = mViewEmail.getText().toString();
//
//                                    VerifyUtil util = new VerifyUtil(RegisterActivity.this, response.body().getData());

                                    // Buat Intent
                                    Intent pindah = new Intent(RegisterActivity.this, KodeOtp.class);

                                    // Kirim email ke LoginActivity
                                    pindah.putExtra("email", email);
                                    pindah.putExtra("otp", response.body().getData().getOtp());
                                    pindah.putExtra("nama_lengkap", mViewNama.getText().toString());
                                    pindah.putExtra("no_telpon", mViewNotlp.getText().toString());
                                    pindah.putExtra("password", mViewPassword.getText().toString());
                                    startActivity(pindah);
                                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);


                                }
                            }, 2000);
                        } else {
                            Toast.makeText(RegisterActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<VerifyResponse> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                        tekserror.setVisibility(View.VISIBLE);
                        tekserror.setText(t.getMessage());

                    }
                });

    }

    private void bersihkan() {

        mViewNama.setText(null);
        mViewNotlp.setText(null);
        mViewPassword.setText(null);
        mViewPassword2.setText(null);
    }


    @Override
    public void onBackPressed() {
        // Tidak melakukan apa-apa ketika tombol kembali ditekan
    }
}
