package com.example.yow.easystock.BottomBar.MarketInfo;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by 12205 on 2016/7/28.
 */
public class MarketInfoPagerAdapter extends FragmentPagerAdapter {

    public MarketInfoPagerAdapter(FragmentManager fm){
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        MarketGzFragment gz = MarketGzFragment.newInstance();
        MarketShFragment sh = MarketShFragment.newInstance();
        MarketSzFragment sz = MarketSzFragment.newInstance();
        MarketGmFragment gm = MarketGmFragment.newInstance();

        switch(position){
            case 0:
                return gz;
            case 1:
                return sh;
            case 2:
                return sz;
            case 3:
                return gm;
        }
        return null;
    }

    @Override
    public int getCount() {
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {
            case 0:
                return "股指";
            case 1:
                return "沪股";
            case 2:
                return "深股";
            case 3:
                return "港美";
        }
        return null;
    }

}
