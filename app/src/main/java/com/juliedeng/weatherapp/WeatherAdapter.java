package com.juliedeng.weatherapp;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by juliedeng on 3/8/18.
 */

public class WeatherAdapter extends FragmentStatePagerAdapter {
    public WeatherAdapter(FragmentManager fm) {
        super(fm);
    }
    @Override
    public Fragment getItem(int position) {
        return WeatherFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return 2;
    }
}
