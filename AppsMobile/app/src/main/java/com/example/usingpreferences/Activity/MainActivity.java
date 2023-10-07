package com.example.usingpreferences.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ImageButton;

import com.example.usingpreferences.MenuFragment.HomeFragment;
import com.example.usingpreferences.MenuFragment.LayananFragment;
import com.example.usingpreferences.MenuFragment.ProfilFragment;
import com.example.usingpreferences.MenuFragment.ProfilLengkapFragment;
import com.example.usingpreferences.MenuFragment.StatusFragment;
import com.example.usingpreferences.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView navbarbttm;

    public static String FRAGMENT = "fragment";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        navbarbttm = findViewById(R.id.bottomNav);
        navbarbttm.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Fragment selectedFragment = null;
                if(item.getItemId() == R.id.Beranda){
                    selectedFragment = new HomeFragment();
                }else if (item.getItemId() == R.id.Layanan){
                    selectedFragment = new LayananFragment();
                }else if (item.getItemId() == R.id.Status){
                    selectedFragment = new StatusFragment();
                }else if (item.getItemId() == R.id.Profil){
                    selectedFragment = new ProfilFragment();
                }

                if(selectedFragment != null){
                    getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_layout, selectedFragment)
                            .commit();
                 }
                return true;
            }
        });
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame_layout, new HomeFragment())
                .commit();

        if (getIntent().getIntExtra(MainActivity.FRAGMENT, 0) == R.layout.fragment_status) {
            navbarbttm.setSelectedItemId(R.id.Status);//agar ketika kembali ke mainactivity akan mengaktifkan navbar bagian status
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new StatusFragment())
                    .commit();
        }
        if (getIntent().getIntExtra(MainActivity.FRAGMENT, 0) == R.layout.fragment_profil_lengkap) {
            navbarbttm.setSelectedItemId(R.id.Profil);//agar ketika kembali ke mainactivity akan mengaktifkan navbar bagian profil
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_layout, new ProfilLengkapFragment())
                    .commit();
        }
    }

    @Override
    public void onBackPressed() {
        // Tidak mlakukan apa-apa ketika tombol kembali ditekan
    }

}