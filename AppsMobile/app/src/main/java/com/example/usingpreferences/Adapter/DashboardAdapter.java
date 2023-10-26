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
            return BannerFragment.newInstance(R.drawable.banner1);
        } else if (newPosition == 1) {
            return BannerFragment.newInstance(R.drawable.banner2);
        } else if (newPosition == 2) {
            return BannerFragment.newInstance(R.drawable.banner4);
        } else if (newPosition == 3) {
            return BannerFragment.newInstance(R.drawable.banner5);
        } else if (newPosition == 4) {
            return BannerFragment.newInstance(R.drawable.banner3);
        }

        return null;
    }



    @Override
    public int getCount() {
        return 5; // Jumlah gambar yang dapat digeser
    }
}