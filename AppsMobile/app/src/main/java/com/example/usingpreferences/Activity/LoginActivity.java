package com.example.usingpreferences.Activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.InputFilter;
import android.text.InputType;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.usingpreferences.API.APIRequestData;
import com.example.usingpreferences.API.RetroServer;
import com.example.usingpreferences.API.google.GoogleUsers;
import com.example.usingpreferences.DataModel.ModelUsers;
import com.example.usingpreferences.DataModel.ResponseModelUsers;
import com.example.usingpreferences.MenuFragment.HomeFragment;
import com.example.usingpreferences.R;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.material.textfield.TextInputEditText;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {



    private TextInputEditText mViewUser, mViewPassword;
    private GoogleUsers googleUsers;
    private ProgressDialog progressDialog;
    private Button loginbutton,lupapassword;
    TextView registerbutton;
    TextView tekserror;
    private final boolean[] isPasswordVisible = {false};
    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        setContentView(R.layout.activity_login);
        tekserror = findViewById(R.id.texterror);
        googleUsers = new GoogleUsers(this);
        Button logingoogle = findViewById(R.id.logingoogle);
        logingoogle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivityForResult(googleUsers.getIntent(), GoogleUsers.REQUEST_CODE);
            }
        });
        progressDialog = new ProgressDialog(LoginActivity.this);
        progressDialog.setTitle("Loading...");
        progressDialog.setMessage("Harap Tunggu");
        progressDialog.setCancelable(false);
        progressDialog.setIcon(R.drawable.logonganjuk);
        registerbutton = findViewById(R.id.button_signupSignin);
        loginbutton = findViewById(R.id.button_siginSignin);
        lupapassword = findViewById(R.id.btn_lupapassword);
        mViewUser = findViewById(R.id.et_emailSignin);
        mViewPassword = findViewById(R.id.et_passwordSignin);
        mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
        String email = getIntent().getStringExtra("email");
        mViewUser.setText(email);
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
        mViewPassword.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        mViewUser.setFilters(new InputFilter[]{noWhiteSpaceFilter});
        lupapassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, LupaKatasandi.class));
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

            }
        });
        registerbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pindah = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(pindah);
                overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                bersihkan();
            }
        });
        mViewPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selection = mViewPassword.getSelectionEnd(); // Simpan posisi kursor

                if (isPasswordVisible[0]) {
                    mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    mViewPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);
                } else {
                    mViewPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                    mViewPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.ic_eye, 0);
                }

                mViewPassword.setSelection(selection); // Setel kursor ke posisi yang disimpan
                isPasswordVisible[0] = !isPasswordVisible[0];
            }
        });
        loginbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mViewUser.setError(null);
                mViewPassword.setError(null);
                String email = mViewUser.getText().toString();
                if (TextUtils.isEmpty(email)) {
                    mViewUser.setError("Email harus diisi");
                    mViewUser.requestFocus();
                } else if (!email.endsWith("@gmail.com")) {
                    mViewUser.setError("Email tidak valid!!");
                    mViewUser.requestFocus();
                } else if(mViewPassword.getText().toString().isEmpty()){
                    mViewPassword.setError("Password Harus Diisi");
                    mViewPassword.requestFocus();
                } else{
                    APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
                    Call<ResponseModelUsers> getLoginResponse = ardData.login(mViewUser.getText().toString(), mViewPassword.getText().toString());
                    getLoginResponse.enqueue(new Callback<ResponseModelUsers>() {
                        @Override
                        public void onResponse(Call<ResponseModelUsers> call, Response<ResponseModelUsers> response) {

                            if (response.body().kode == 1){
                                progressDialog.show();
                                new Handler().postDelayed(new Runnable() {
                                    @Override
                                    public void run() {
                                        // Tutup ProgressDialog
                                        progressDialog.dismiss();
                                        // Simpan semua data pengguna ke SharedPreferences
                                        ModelUsers user = response.body().getData();
                                        editor.putString("id_user", String.valueOf(user.getId_user()));
                                        editor.putString("nama_lengkap", user.getNama_lengkap());
                                        editor.putString("no_telpon", user.getNo_telpon());
                                        editor.putString("tanggal_lahir", user.getTanggal_lahir());
                                        editor.putString("tempat_lahir", user.getTempat_lahir());
                                        editor.putString("role", user.getRole());
                                        editor.putString("email", user.getEmail());
                                        editor.putString("password", user.getPassword());
                                        editor.putString("verifikasi", user.getVerifikasi());
                                        editor.apply();
                                        Intent pindah = new Intent(LoginActivity.this,MainActivity.class);
                                        startActivity(pindah);
                                        overridePendingTransition(R.anim.layout_in, R.anim.layout_out);
                                    }
                                }, 2000);
                                bersihkan();

                            }else if (response.body().kode == 0) {
                                mViewUser.requestFocus();
                                Toast.makeText(LoginActivity.this, "Akun Belum Terdaftar", Toast.LENGTH_SHORT).show();
                            }else if (response.body().kode == 2) {
                                mViewPassword.requestFocus();
                                Toast.makeText(LoginActivity.this, "Password Salah!!", Toast.LENGTH_SHORT).show();
                            }else if(response.body().kode == 3){
                                mViewUser.requestFocus();
                                Toast.makeText(LoginActivity.this, "Akun Admin Tidak dapat login ke Aplikasi Mobile", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<ResponseModelUsers> call, Throwable t) {
                            Toast.makeText(LoginActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                            t.printStackTrace();
                        }
                    });

                }

            }

        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        SharedPreferences sharedPreferences = getSharedPreferences("prefLogin", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        googleUsers.onActivityResult(requestCode, resultCode, data);
        APIRequestData ardData = RetroServer.getConnection().create(APIRequestData.class);
        Call<ResponseModelUsers> getLoginResponse = ardData.google_login(googleUsers.getUserData().getEmail());
        getLoginResponse.enqueue(new Callback<ResponseModelUsers>() {
            @Override
            public void onResponse(Call<ResponseModelUsers> call, Response<ResponseModelUsers> response) {
                if (response.body().getKode() == 1) {
                    Toast.makeText(getApplicationContext(), "Login berhasil", Toast.LENGTH_SHORT).show();
                    // Simpan semua data pengguna ke SharedPreferences
                    ModelUsers user = response.body().getData();
                    editor.putString("id_user", String.valueOf(user.getId_user()));
                    editor.putString("nama_lengkap", user.getNama_lengkap());
                    editor.putString("no_telpon", user.getNo_telpon());
                    editor.putString("tanggal_lahir", user.getTanggal_lahir());
                    editor.putString("tempat_lahir", user.getTempat_lahir());
                    editor.putString("role", user.getRole());
                    editor.putString("email", user.getEmail());
                    editor.putString("password", user.getPassword());
                    editor.putString("verifikasi", user.getVerifikasi());
                    editor.apply();
                    Intent pindah = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(pindah);

                    overridePendingTransition(R.anim.layout_in, R.anim.layout_out);

                    bersihkan();

                    startActivity(new Intent(LoginActivity.this, MainActivity.class));
                } else if (response.body().getKode() == 2){
                    Toast.makeText(getApplicationContext(), response.body().getPesan(), Toast.LENGTH_SHORT).show();
                }else if (response.body().getKode() == 0){
                    startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                    Toast.makeText(getApplicationContext(), response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }else if (response.body().getKode() == 3){
                    Toast.makeText(getApplicationContext(), response.body().getPesan(), Toast.LENGTH_SHORT).show();

                }else{
                    Toast.makeText(LoginActivity.this, "Kesalahannn", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseModelUsers> call, Throwable t) {
                t.printStackTrace();
                tekserror.setVisibility(View.VISIBLE);
                tekserror.setText(t.getMessage());
            }
        });
    }






    private void bersihkan(){
        mViewPassword.setText(null);
        mViewUser.setText(null);
    }
    public void onBackPressed() {
    }

}