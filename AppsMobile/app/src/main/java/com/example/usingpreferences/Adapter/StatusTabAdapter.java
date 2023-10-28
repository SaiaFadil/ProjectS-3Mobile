package com.example.usingpreferences.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.usingpreferences.MenuFragment.StatusDitolak;
import com.example.usingpreferences.MenuFragment.StatusProses;
import com.example.usingpreferences.MenuFragment.StatusSelesai;
import com.example.usingpreferences.MenuFragment.StatusSemua;
import com.example.usingpreferences.MenuFragment.StatusDiajukan;

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
                return new StatusDiajukan();
            case 2:
                return new StatusProses();
            case 3:
                return new StatusSelesai();
            case 4:
                return new StatusDitolak();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Semua";
            case 1:
                return "Diajukan";
            case 2:
                return "Proses";
            case 3:
                return "Selesai";
            case 4:
                return "Ditolak";
            default:
                return null;
        }
    }
}