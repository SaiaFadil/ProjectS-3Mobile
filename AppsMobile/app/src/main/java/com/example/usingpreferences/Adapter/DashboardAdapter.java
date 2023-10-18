package com.example.usingpreferences.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.usingpreferences.MenuFragment.BannerFragment;
import com.example.usingpreferences.R;

public class DashboardAdapter extends FragmentStatePagerAdapter {

    public DashboardAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        int itemCount = getCount();
        int newPosition = position % itemCount;

        if (newPosition == 0) {
            // Kembali ke item pertama setelah mencapai item terakhir
            return BannerFragment.newInstance(R.drawable.banner1);
        } else if (newPosition == 1) {
            return BannerFragment.newInstance(R.drawable.banner2);
        }

        return null;
    }



    @Override
    public int getCount() {
        return 2; // Jumlah gambar yang dapat digeser
    }
}