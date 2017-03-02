package com.example.yow.easystock.BottomBar.MarketInfo;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yow.easystock.R;

/**
 * Created by 12205 on 2016/7/24.
 */
public class MarketInfoFragment extends Fragment {

    ViewPager viewPager;
    MarketInfoPagerAdapter viewPagerAdapter;

    public static MarketInfoFragment newInstance() {

        Bundle args = new Bundle();

        MarketInfoFragment fragment = new MarketInfoFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_market_info,container,false);
        return rootView;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        viewPagerAdapter = new MarketInfoPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager)getActivity().findViewById(R.id.market_info_viewPager);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);

        TabLayout tabLayout = (TabLayout)getActivity().findViewById(R.id.sliding_tab);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }
}
