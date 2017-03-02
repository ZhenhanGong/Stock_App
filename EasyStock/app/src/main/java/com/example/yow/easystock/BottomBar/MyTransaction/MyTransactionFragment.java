package com.example.yow.easystock.BottomBar.MyTransaction;


import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yow.easystock.R;

/**
 * Created by 12205 on 2016/7/20.
 */
public class MyTransactionFragment extends Fragment {

    ViewPager viewPager;
    TransactionViewPagerAdapter viewPagerAdapter;
    TabLayout tabLayout;

    public static MyTransactionFragment newInstance() {

        Bundle args = new Bundle();

        MyTransactionFragment fragment = new MyTransactionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_my_transaction,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

        viewPagerAdapter = new TransactionViewPagerAdapter(getChildFragmentManager());
        viewPager = (ViewPager) getActivity().findViewById(R.id.transactionViewPager);

        viewPager.setOffscreenPageLimit(3);
        viewPager.setAdapter(viewPagerAdapter);

        tabLayout = (TabLayout) getActivity().findViewById(R.id.sliding_tab);
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
