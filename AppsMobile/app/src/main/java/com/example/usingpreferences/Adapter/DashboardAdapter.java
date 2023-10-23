package com.example.usingpreferences.Adapter;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.example.usingpreferences.MenuFragment.BannerFragment;
import com.example.usingpreferences.R;

    public class DashboardAdapter extends FragmentStatePagerAdapter {

        private static final int NUM_PAGES = 200000;
        private static final int[] BANNER_IMAGES = {
                R.drawable.banner1,
                R.drawable.banner4,
                R.drawable.banner2,
                R.drawable.banner3
        };

        public DashboardAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            int newPosition = position % NUM_PAGES;
            int bannerPosition = newPosition % BANNER_IMAGES.length;
            return BannerFragment.newInstance(BANNER_IMAGES[bannerPosition]);
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }


