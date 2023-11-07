package com.example.usingpreferences.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.usingpreferences.MenuFragment.StatusPentas;
import com.example.usingpreferences.MenuFragment.StatusSemua;

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
                return new StatusSemua();
            case 2:
                return new StatusSemua();
            case 3:
                return new StatusPentas();
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
                return "Event";
            case 1:
                return "Tempat";
            case 2:
                return "Induk Seniman";
            case 3:
                return "Pentas";
            default:
                return null;
        }
    }
}