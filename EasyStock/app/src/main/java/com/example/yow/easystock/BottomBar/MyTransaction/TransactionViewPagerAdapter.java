package com.example.yow.easystock.BottomBar.MyTransaction;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by 12205 on 2016/7/27.
 */
public class TransactionViewPagerAdapter extends FragmentPagerAdapter {

    public TransactionViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        BuyFragment buy = BuyFragment.newInstance();
        SellFragment sell = SellFragment.newInstance();
        CancelFragment cancel = CancelFragment.newInstance();
        HoldFragment hold = HoldFragment.newInstance();

        switch(position){

            case 0:
                return buy;
            case 1:
                return sell;
            case 2:
                return cancel;
            case 3:
                return hold;
        }
        return null;
    }

    @Override
    public int getCount() {
        // Show 4 total pages.
        return 4;
    }

    @Override
    public CharSequence getPageTitle(int position) {

        switch (position) {

            case 0:
                return "买入";
            case 1:
                return "卖出";
            case 2:
                return "撤单";
            case 3:
                return "持仓";
        }
        return null;
    }

}
