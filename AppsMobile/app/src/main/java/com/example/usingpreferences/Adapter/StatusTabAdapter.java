package com.example.usingpreferences.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.usingpreferences.FragmentStatus.StatusDitolak;
import com.example.usingpreferences.FragmentStatus.StatusProses;
import com.example.usingpreferences.FragmentStatus.StatusSelesai;
import com.example.usingpreferences.FragmentStatus.StatusSemua;

public class StatusTabAdapter  extends FragmentPagerAdapter {

    public StatusTabAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new StatusSemua();
            case 1:
                return new StatusProses();
            case 2:
                return new StatusSelesai();
            case 3:
                return new StatusDitolak();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Semua";
            case 1:
                return "Proses";
            case 2:
                return "Selesai";
            case 3:
                return "Ditolak";
            default:
                return null;
        }
    }
}