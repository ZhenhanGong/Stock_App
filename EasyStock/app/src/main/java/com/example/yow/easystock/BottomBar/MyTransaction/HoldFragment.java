package com.example.yow.easystock.BottomBar.MyTransaction;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yow.easystock.R;

/**
 * Created by 12205 on 2016/7/20.
 */
public class HoldFragment extends Fragment {

    public static HoldFragment newInstance() {
        
        Bundle args = new Bundle();
        
        HoldFragment fragment = new HoldFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_hold,container,false);
        return rootView;
    }
}
