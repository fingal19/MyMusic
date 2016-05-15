package com.fingal.mymusic.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by FX50J on 2016/5/14.
 */
public class Fragment_Adapter extends FragmentPagerAdapter {
    private String[] mtittle = {"「欧美」热门","「内地」热门"};

    public Fragment_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new Euramerican_fragment();
            case 1:
                return new Mainland_fragment();
            default:
                break;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mtittle[position];
    }
}
