package com.example.yow.easystock.BottomBar.MarketInfo;


import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.MarketGlobalClient;
import com.example.yow.easystock.SinaAPI.MarketGlobalInfo;
import com.example.yow.easystock.SinaAPI.ShortStockClient;
import com.example.yow.easystock.SinaAPI.ShortStockInfo;
import com.example.yow.easystock.SinaAPI.SinaStockClient;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by 12205 on 2016/7/25.
 */
public class MarketShFragment extends Fragment {

    private ShortStockClient client;
    Handler handler;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12;
    LinearLayout layout1,layout2,layout3;
    ListView listView;
    MarketShAdapter adapter;
    ArrayList<MarketShItem> array;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static MarketShFragment newInstance() {

        Bundle args = new Bundle();

        MarketShFragment fragment = new MarketShFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        array = new ArrayList<MarketShItem>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_market_sh, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        FindViewById();

        //set adapter for the listView
        listView = (ListView)getParentFragment().getActivity().findViewById(R.id.list_sh);
        adapter = new MarketShAdapter(getParentFragment().getContext(),array);
        listView.setAdapter(adapter);

        mSwipeRefreshLayout = (SwipeRefreshLayout)getParentFragment().getActivity().findViewById(R.id.swipe_refresh_layout);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        SwipeRefresh();
        refresh();
    }

    public void refresh(){

        initClient();
        myHandleMessage();
        StartNetworkTask();
    }

    private void initClient() {

        client = ShortStockClient.getInstance();
    }

    private void FindViewById(){

        text1 = (TextView)getParentFragment().getActivity().findViewById(R.id.name1);
        text2 = (TextView)getParentFragment().getActivity().findViewById(R.id.price1);
        text3 = (TextView)getParentFragment().getActivity().findViewById(R.id.offset1);
        text4 = (TextView)getParentFragment().getActivity().findViewById(R.id.percent1);
        text5 = (TextView)getParentFragment().getActivity().findViewById(R.id.name2);
        text6 = (TextView)getParentFragment().getActivity().findViewById(R.id.price2);
        text7 = (TextView)getParentFragment().getActivity().findViewById(R.id.offset2);
        text8 = (TextView)getParentFragment().getActivity().findViewById(R.id.percent2);
        text9 = (TextView)getParentFragment().getActivity().findViewById(R.id.name3);
        text10 = (TextView)getParentFragment().getActivity().findViewById(R.id.price3);
        text11 = (TextView)getParentFragment().getActivity().findViewById(R.id.offset3);
        text12 = (TextView)getParentFragment().getActivity().findViewById(R.id.percent3);
        layout1 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.square1);
        layout2 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.square2);
        layout3 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.square3);

    }

    private void myHandleMessage(){

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Bundle bundle = msg.getData();
                String[] temp = new String[72];
                for(int i = 0; i < 72; i++){
                    temp[i] = bundle.getString("" + i);
                }

                text1.setText(temp[0]);
                text2.setText(temp[1]);
                text3.setText(temp[2]);
                text4.setText(temp[3]);
                text5.setText(temp[4]);
                text6.setText(temp[5]);
                text7.setText(temp[6]);
                text8.setText(temp[7]);
                text9.setText(temp[8]);
                text10.setText(temp[9]);
                text11.setText(temp[10]);
                text12.setText(temp[11]);
                float isPositive1 = Float.parseFloat(temp[2]);
                float isPositive2 = Float.parseFloat(temp[6]);
                float isPositive3 = Float.parseFloat(temp[10]);

                if(isPositive1 > 0.0){
                    layout1.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout1.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive2 > 0.0){
                    layout2.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout2.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive3 > 0.0){
                    layout3.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout3.setBackgroundColor(Color.parseColor("#96CC7A"));
                }

                adapter.clear();
                for(int i = 0; i < 15; i++){

                    MarketShItem item = new MarketShItem(temp[i*4+12],temp[i*4+13],temp[i*4+14],temp[i*4+15]);
                    adapter.add(item);
                }
            }
        };
    }

    private void StartNetworkTask(){

        Runnable networkTask = new Runnable() {
            @Override
            public void run() {

                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();

                try{
                    String[] arr = new String[3];
                    arr[0] = "s_sh000001";
                    arr[1] = "s_sh000002";
                    arr[2] = "s_sh000003";
                    List<ShortStockInfo> stockInfoList = client.getStockInfo(arr);

                    String[] temp = new String[12];

                    temp[0] = "" + stockInfoList.get(0).getName();
                    bundle.putString("" + 0, temp[0]);
                    temp[1] = "" + stockInfoList.get(0).getPrice();
                    bundle.putString("" + 1, temp[1]);
                    temp[2] = "" + stockInfoList.get(0).getOffset();
                    bundle.putString("" + 2, temp[2]);
                    temp[3] = "" + stockInfoList.get(0).getPercent();
                    bundle.putString("" + 3, temp[3]);
                    temp[4] = "" + stockInfoList.get(1).getName();
                    bundle.putString("" + 4, temp[4]);
                    temp[5] = "" + stockInfoList.get(1).getPrice();
                    bundle.putString("" + 5, temp[5]);
                    temp[6] = "" + stockInfoList.get(1).getOffset();
                    bundle.putString("" + 6, temp[6]);
                    temp[7] = "" + stockInfoList.get(1).getPercent();
                    bundle.putString("" + 7, temp[7]);
                    temp[8] = "" + stockInfoList.get(2).getName();
                    bundle.putString("" + 8, temp[8]);
                    temp[9] = "" + stockInfoList.get(2).getPrice();
                    bundle.putString("" + 9, temp[9]);
                    temp[10] = "" + stockInfoList.get(2).getOffset();
                    bundle.putString("" + 10, temp[10]);
                    temp[11] = "" + stockInfoList.get(2).getPercent();
                    bundle.putString("" + 11, temp[11]);

                    String[] arr2 = new String[15];
                    arr2[0] = "s_sh601398";
                    arr2[1] = "s_sh601939";
                    arr2[2] = "s_sh601288";
                    arr2[3] = "s_sh601988";
                    arr2[4] = "s_sh601318";
                    arr2[5] = "s_sh601328";
                    arr2[6] = "s_sh600036";
                    arr2[7] = "s_sh601166";
                    arr2[8] = "s_sh600016";
                    arr2[9] = "s_sh601628";
                    arr2[10] = "s_sh600000";
                    arr2[11] = "s_sh601998";
                    arr2[12] = "s_sh600104";
                    arr2[13] = "s_sh601818";
                    arr2[14] = "s_sh601857";
                    List<ShortStockInfo> stockInfoList2 = client.getStockInfo(arr2);

                    String[] temp2 = new String[60];
                    temp2[0] = "" + stockInfoList2.get(0).getName();
                    bundle.putString("" + 12, temp2[0]);
                    temp2[1] = "" + stockInfoList2.get(0).getPrice();
                    bundle.putString("" + 13, temp2[1]);
                    temp2[2] = "" + stockInfoList2.get(0).getOffset();
                    bundle.putString("" + 14, temp2[2]);
                    temp2[3] = "" + stockInfoList2.get(0).getPercent();
                    bundle.putString("" + 15, temp2[3]);

                    temp2[4] = "" + stockInfoList2.get(1).getName();
                    bundle.putString("" + 16, temp2[4]);
                    temp2[5] = "" + stockInfoList2.get(1).getPrice();
                    bundle.putString("" + 17, temp2[5]);
                    temp2[6] = "" + stockInfoList2.get(1).getOffset();
                    bundle.putString("" + 18, temp2[6]);
                    temp2[7] = "" + stockInfoList2.get(1).getPercent();
                    bundle.putString("" + 19, temp2[7]);

                    temp2[8] = "" + stockInfoList2.get(2).getName();
                    bundle.putString("" + 20, temp2[8]);
                    temp2[9] = "" + stockInfoList2.get(2).getPrice();
                    bundle.putString("" + 21, temp2[9]);
                    temp2[10] = "" + stockInfoList2.get(2).getOffset();
                    bundle.putString("" + 22, temp2[10]);
                    temp2[11] = "" + stockInfoList2.get(2).getPercent();
                    bundle.putString("" + 23, temp2[11]);

                    temp2[12] = "" + stockInfoList2.get(3).getName();
                    bundle.putString("" + 24, temp2[12]);
                    temp2[13] = "" + stockInfoList2.get(3).getPrice();
                    bundle.putString("" + 25, temp2[13]);
                    temp2[14] = "" + stockInfoList2.get(3).getOffset();
                    bundle.putString("" + 26, temp2[14]);
                    temp2[15] = "" + stockInfoList2.get(3).getPercent();
                    bundle.putString("" + 27, temp2[15]);

                    temp2[16] = "" + stockInfoList2.get(4).getName();
                    bundle.putString("" + 28, temp2[16]);
                    temp2[17] = "" + stockInfoList2.get(4).getPrice();
                    bundle.putString("" + 29, temp2[17]);
                    temp2[18] = "" + stockInfoList2.get(4).getOffset();
                    bundle.putString("" + 30, temp2[18]);
                    temp2[19] = "" + stockInfoList2.get(4).getPercent();
                    bundle.putString("" + 31, temp2[19]);

                    temp2[20] = "" + stockInfoList2.get(5).getName();
                    bundle.putString("" + 32, temp2[20]);
                    temp2[21] = "" + stockInfoList2.get(5).getPrice();
                    bundle.putString("" + 33, temp2[21]);
                    temp2[22] = "" + stockInfoList2.get(5).getOffset();
                    bundle.putString("" + 34, temp2[22]);
                    temp2[23] = "" + stockInfoList2.get(5).getPercent();
                    bundle.putString("" + 35, temp2[23]);

                    temp2[24] = "" + stockInfoList2.get(6).getName();
                    bundle.putString("" + 36, temp2[24]);
                    temp2[25] = "" + stockInfoList2.get(6).getPrice();
                    bundle.putString("" + 37, temp2[25]);
                    temp2[26] = "" + stockInfoList2.get(6).getOffset();
                    bundle.putString("" + 38, temp2[26]);
                    temp2[27] = "" + stockInfoList2.get(6).getPercent();
                    bundle.putString("" + 39, temp2[27]);

                    temp2[28] = "" + stockInfoList2.get(7).getName();
                    bundle.putString("" + 40, temp2[28]);
                    temp2[29] = "" + stockInfoList2.get(7).getPrice();
                    bundle.putString("" + 41, temp2[29]);
                    temp2[30] = "" + stockInfoList2.get(7).getOffset();
                    bundle.putString("" + 42, temp2[30]);
                    temp2[31] = "" + stockInfoList2.get(7).getPercent();
                    bundle.putString("" + 43, temp2[31]);

                    temp2[32] = "" + stockInfoList2.get(8).getName();
                    bundle.putString("" + 44, temp2[32]);
                    temp2[33] = "" + stockInfoList2.get(8).getPrice();
                    bundle.putString("" + 45, temp2[33]);
                    temp2[34] = "" + stockInfoList2.get(8).getOffset();
                    bundle.putString("" + 46, temp2[34]);
                    temp2[35] = "" + stockInfoList2.get(8).getPercent();
                    bundle.putString("" + 47, temp2[35]);

                    temp2[36] = "" + stockInfoList2.get(9).getName();
                    bundle.putString("" + 48, temp2[36]);
                    temp2[37] = "" + stockInfoList2.get(9).getPrice();
                    bundle.putString("" + 49, temp2[37]);
                    temp2[38] = "" + stockInfoList2.get(9).getOffset();
                    bundle.putString("" + 50, temp2[38]);
                    temp2[39] = "" + stockInfoList2.get(9).getPercent();
                    bundle.putString("" + 51, temp2[39]);

                    temp2[40] = "" + stockInfoList2.get(10).getName();
                    bundle.putString("" + 52, temp2[40]);
                    temp2[41] = "" + stockInfoList2.get(10).getPrice();
                    bundle.putString("" + 53, temp2[41]);
                    temp2[42] = "" + stockInfoList2.get(10).getOffset();
                    bundle.putString("" + 54, temp2[42]);
                    temp2[43] = "" + stockInfoList2.get(10).getPercent();
                    bundle.putString("" + 55, temp2[43]);

                    temp2[44] = "" + stockInfoList2.get(11).getName();
                    bundle.putString("" + 56, temp2[44]);
                    temp2[45] = "" + stockInfoList2.get(11).getPrice();
                    bundle.putString("" + 57, temp2[45]);
                    temp2[46] = "" + stockInfoList2.get(11).getOffset();
                    bundle.putString("" + 58, temp2[46]);
                    temp2[47] = "" + stockInfoList2.get(11).getPercent();
                    bundle.putString("" + 59, temp2[47]);

                    temp2[48] = "" + stockInfoList2.get(12).getName();
                    bundle.putString("" + 60, temp2[48]);
                    temp2[49] = "" + stockInfoList2.get(12).getPrice();
                    bundle.putString("" + 61, temp2[49]);
                    temp2[50] = "" + stockInfoList2.get(12).getOffset();
                    bundle.putString("" + 62, temp2[50]);
                    temp2[51] = "" + stockInfoList2.get(12).getPercent();
                    bundle.putString("" + 63, temp2[51]);

                    temp2[52] = "" + stockInfoList2.get(13).getName();
                    bundle.putString("" + 64, temp2[52]);
                    temp2[53] = "" + stockInfoList2.get(13).getPrice();
                    bundle.putString("" + 65, temp2[53]);
                    temp2[54] = "" + stockInfoList2.get(13).getOffset();
                    bundle.putString("" + 66, temp2[54]);
                    temp2[55] = "" + stockInfoList2.get(13).getPercent();
                    bundle.putString("" + 67, temp2[55]);

                    temp2[56] = "" + stockInfoList2.get(14).getName();
                    bundle.putString("" + 68, temp2[56]);
                    temp2[57] = "" + stockInfoList2.get(14).getPrice();
                    bundle.putString("" + 69, temp2[57]);
                    temp2[58] = "" + stockInfoList2.get(14).getOffset();
                    bundle.putString("" + 70, temp2[58]);
                    temp2[59] = "" + stockInfoList2.get(14).getPercent();
                    bundle.putString("" + 71, temp2[59]);

                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(networkTask).start();
    }

    private void SwipeRefresh(){

        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorAccent);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {

            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem,
                                 int visibleItemCount, int totalItemCount) {

                boolean enable = false;
                if(listView != null && listView.getChildCount() > 0){
                    // check if the first item of the list is visible
                    boolean firstItemVisible = listView.getFirstVisiblePosition() == 0;
                    // check if the top of the first item is visible
                    boolean topOfFirstItemVisible = listView.getChildAt(0).getTop() == 0;
                    // enabling or disabling the refresh layout
                    enable = firstItemVisible && topOfFirstItemVisible;
                }
                mSwipeRefreshLayout.setEnabled(enable);
            }
        });
    }

}
