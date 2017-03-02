package com.example.yow.easystock.BottomBar.MySelection;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.ParseStockInfoException;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;

import org.apache.commons.httpclient.HttpException;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12205 on 2016/7/19.
 */
public class MySelectionFragment extends Fragment implements AdapterView.OnItemClickListener{

    ListView listView;
    Handler handler;

    public static MySelectionFragment newInstance() {
        
        Bundle args = new Bundle();
        
        MySelectionFragment fragment = new MySelectionFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_selection,container,false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Create the adapter to convert the array to views
        ArrayList<MySelectionItem> arrayOfStocks = new ArrayList<MySelectionItem>();
        MySelectionAdapter adapter = new MySelectionAdapter(getActivity(),arrayOfStocks);

        // Attach the adapter to a ListView & set onItemClickListener
        listView = (ListView) getActivity().findViewById(R.id.mylist);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);

        // Add item to adapter
        //TODO place those three textview evenly in a row  ---> item_my_selection.xml
        MySelectionItem item1 = new MySelectionItem("中国石油","4.81", "5.23" ,"-0.82%");
        adapter.add(item1);
        MySelectionItem item2 = new MySelectionItem("中国石化","7.33", "5.68" ,"-0.68%");
        adapter.add(item2);
        //adapter.clear();
    }

    //display message according to the listView item selected
    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        Intent addSelection = new Intent(getActivity(),SelectionDetail.class);
        startActivity(addSelection);
    }


    @Override
    public void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void onMessage(AddSelectionEvent event){
        event.getMessage();
    }

}
//push 16th