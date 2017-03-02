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
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.HongKongClient;
import com.example.yow.easystock.SinaAPI.HongKongInfo;
import com.example.yow.easystock.SinaAPI.MarketGlobalClient;
import com.example.yow.easystock.SinaAPI.MarketGlobalInfo;
import com.example.yow.easystock.SinaAPI.ShortStockInfo;

import java.util.List;

/**
 * Created by 12205 on 2016/7/25.
 */
public class MarketGmFragment extends Fragment {

    private MarketGlobalClient client;
    private HongKongClient hongKongClient;
    Handler handler;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12;
    TextView text13,text14,text15,text16,text17,text18,text19,text20,text21,text22,text23,text24;
    TextView text25,text26,text27,text28,text29,text30,text31,text32,text33,text34,text35,text36;
    TextView text37,text38,text39,text40,text41,text42,text43,text44,text45,text46,text47,text48;
    LinearLayout layout1,layout2,layout3,layout4,layout5,layout6,layout7,layout8,layout9;
    LinearLayout layout10,layout11,layout12;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static MarketGmFragment newInstance() {

        Bundle args = new Bundle();

        MarketGmFragment fragment = new MarketGmFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_market_gm, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        super.onViewCreated(view, savedInstanceState);

        FindViewById();

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

    private void FindViewById(){

        text1 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name1);
        text2 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price1);
        text3 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset1);
        text4 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent1);
        text5 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name2);
        text6 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price2);
        text7 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset2);
        text8 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent2);
        text9 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name3);
        text10 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price3);
        text11 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset3);
        text12 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent3);

        text13 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name4);
        text14 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price4);
        text15 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset4);
        text16 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent4);
        text17 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name5);
        text18 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price5);
        text19 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset5);
        text20 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent5);
        text21 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name6);
        text22 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price6);
        text23 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset6);
        text24 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent6);

        text25 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name7);
        text26 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price7);
        text27 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset7);
        text28 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent7);
        text29 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name8);
        text30 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price8);
        text31 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset8);
        text32 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent8);
        text33 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name9);
        text34 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price9);
        text35 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset9);
        text36 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent9);

        text37 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name10);
        text38 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price10);
        text39 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset10);
        text40 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent10);
        text41 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name11);
        text42 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price11);
        text43 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset11);
        text44 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent11);
        text45 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_name12);
        text46 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_price12);
        text47 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_offset12);
        text48 = (TextView)getParentFragment().getActivity().findViewById(R.id.gm_percent12);

        layout1 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square1);
        layout2 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square2);
        layout3 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square3);
        layout4 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square4);
        layout5 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square5);
        layout6 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square6);
        layout7 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square7);
        layout8 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square8);
        layout9 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square9);
        layout10 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square10);
        layout11 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square11);
        layout12 = (LinearLayout) getParentFragment().getActivity().findViewById(R.id.gm_square12);

        mSwipeRefreshLayout = (SwipeRefreshLayout)getParentFragment().getActivity().findViewById(R.id.swipe_refresh_layout4);

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

    }

    private void initClient() {

        client = MarketGlobalClient.getInstance();
        hongKongClient = HongKongClient.getInstance();
    }

    private void myHandleMessage(){

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);

                Bundle bundle = msg.getData();
                String[] temp = new String[60];
                for(int i = 0; i < 60; i++){
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

                text13.setText(temp[12]);
                text14.setText(temp[13]);
                text15.setText(temp[14]);
                text16.setText(temp[15]);
                text17.setText(temp[16]);
                text18.setText(temp[17]);
                text19.setText(temp[18]);
                text20.setText(temp[19]);
                text21.setText(temp[20]);
                text22.setText(temp[21]);
                text23.setText(temp[22]);
                text24.setText(temp[23]);

                text25.setText(temp[24]);
                text26.setText(temp[25]);
                text27.setText(temp[26]);
                text28.setText(temp[27]);
                text29.setText(temp[28]);
                text30.setText(temp[29]);
                text31.setText(temp[30]);
                text32.setText(temp[31]);
                text33.setText(temp[32]);
                text34.setText(temp[33]);
                text35.setText(temp[34]);
                text36.setText(temp[35]);

                text37.setText(temp[36]);
                text38.setText(temp[37]);
                text39.setText(temp[38]);
                text40.setText(temp[39]);
                text41.setText(temp[40]);
                text42.setText(temp[41]);
                text43.setText(temp[42]);
                text44.setText(temp[43]);
                text45.setText(temp[44]);
                text46.setText(temp[45]);
                text47.setText(temp[46]);
                text48.setText(temp[47]);

                float isPositive1 = Float.parseFloat(temp[2]);
                float isPositive2 = Float.parseFloat(temp[6]);
                float isPositive3 = Float.parseFloat(temp[10]);
                float isPositive4 = Float.parseFloat(temp[14]);
                float isPositive5 = Float.parseFloat(temp[18]);
                float isPositive6 = Float.parseFloat(temp[22]);
                float isPositive7 = Float.parseFloat(temp[26]);
                float isPositive8 = Float.parseFloat(temp[30]);
                float isPositive9 = Float.parseFloat(temp[34]);
                float isPositive10 = Float.parseFloat(temp[38]);
                float isPositive11 = Float.parseFloat(temp[42]);
                float isPositive12 = Float.parseFloat(temp[46]);


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

                if(isPositive4 > 0.0){
                    layout4.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout4.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive5 > 0.0){
                    layout5.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout5.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive6 > 0.0){
                    layout6.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout6.setBackgroundColor(Color.parseColor("#96CC7A"));
                }

                if(isPositive7 > 0.0){
                    layout7.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout7.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive8 > 0.0){
                    layout8.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout8.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive9 > 0.0){
                    layout9.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout9.setBackgroundColor(Color.parseColor("#96CC7A"));
                }

                if(isPositive10 > 0.0){
                    layout10.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout10.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive11 > 0.0){
                    layout11.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout11.setBackgroundColor(Color.parseColor("#96CC7A"));
                }
                if(isPositive12 > 0.0){
                    layout12.setBackgroundColor(Color.parseColor("#EA705D"));
                }else {
                    layout12.setBackgroundColor(Color.parseColor("#96CC7A"));
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
                    arr[0] = "int_hangseng";
                    arr[1] = "int_dji";
                    arr[2] = "int_nasdaq";
                    List<MarketGlobalInfo> stockInfoList = client.getStockInfo(arr);

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

                    String[] arr2 = new String[9];
                    arr2[0] = "hk02318";
                    arr2[1] = "hk00388";
                    arr2[2] = "hk00700";
                    arr2[3] = "hk01988";
                    arr2[4] = "hk02800";
                    arr2[5] = "hk00939";
                    arr2[6] = "hk01733";
                    arr2[7] = "hk01117";
                    arr2[8] = "hk00359";

                    List<HongKongInfo> stockInfoList2 = hongKongClient.getStockInfo(arr2);

                    String[] temp2 = new String[36];

                    temp2[0] = "" + stockInfoList2.get(0).getmCnName();
                    bundle.putString("" + 12, temp2[0]);
                    temp2[1] = "" + stockInfoList2.get(0).getmNowPrice();
                    bundle.putString("" + 13, temp2[1]);
                    temp2[2] = "" + stockInfoList2.get(0).getmOffset();
                    bundle.putString("" + 14, temp2[2]);
                    temp2[3] = "" + stockInfoList2.get(0).getmOffsetPercent();
                    bundle.putString("" + 15, temp2[3]);
                    temp2[4] = "" + stockInfoList2.get(1).getmCnName();
                    bundle.putString("" + 16, temp2[4]);
                    temp2[5] = "" + stockInfoList2.get(1).getmNowPrice();
                    bundle.putString("" + 17, temp2[5]);
                    temp2[6] = "" + stockInfoList2.get(1).getmOffset();
                    bundle.putString("" + 18, temp2[6]);
                    temp2[7] = "" + stockInfoList2.get(1).getmOffsetPercent();
                    bundle.putString("" + 19, temp2[7]);
                    temp2[8] = "" + stockInfoList2.get(2).getmCnName();
                    bundle.putString("" + 20, temp2[8]);
                    temp2[9] = "" + stockInfoList2.get(2).getmNowPrice();
                    bundle.putString("" + 21, temp2[9]);
                    temp2[10] = "" + stockInfoList2.get(2).getmOffset();
                    bundle.putString("" + 22, temp2[10]);
                    temp2[11] = "" + stockInfoList2.get(2).getmOffsetPercent();
                    bundle.putString("" + 23, temp2[11]);

                    temp2[12] = "" + stockInfoList2.get(3).getmCnName();
                    bundle.putString("" + 24, temp2[12]);
                    temp2[13] = "" + stockInfoList2.get(3).getmNowPrice();
                    bundle.putString("" + 25, temp2[13]);
                    temp2[14] = "" + stockInfoList2.get(3).getmOffset();
                    bundle.putString("" + 26, temp2[14]);
                    temp2[15] = "" + stockInfoList2.get(3).getmOffsetPercent();
                    bundle.putString("" + 27, temp2[15]);
                    temp2[16] = "" + stockInfoList2.get(4).getmCnName();
                    bundle.putString("" + 28, temp2[16]);
                    temp2[17] = "" + stockInfoList2.get(4).getmNowPrice();
                    bundle.putString("" + 29, temp2[17]);
                    temp2[18] = "" + stockInfoList2.get(4).getmOffset();
                    bundle.putString("" + 30, temp2[18]);
                    temp2[19] = "" + stockInfoList2.get(4).getmOffsetPercent();
                    bundle.putString("" + 31, temp2[19]);
                    temp2[20] = "" + stockInfoList2.get(5).getmCnName();
                    bundle.putString("" + 32, temp2[20]);
                    temp2[21] = "" + stockInfoList2.get(5).getmNowPrice();
                    bundle.putString("" + 33, temp2[21]);
                    temp2[22] = "" + stockInfoList2.get(5).getmOffset();
                    bundle.putString("" + 34, temp2[22]);
                    temp2[23] = "" + stockInfoList2.get(5).getmOffsetPercent();
                    bundle.putString("" + 35, temp2[23]);

                    temp2[24] = "" + stockInfoList2.get(6).getmCnName();
                    bundle.putString("" + 36, temp2[24]);
                    temp2[25] = "" + stockInfoList2.get(6).getmNowPrice();
                    bundle.putString("" + 37, temp2[25]);
                    temp2[26] = "" + stockInfoList2.get(6).getmOffset();
                    bundle.putString("" + 38, temp2[26]);
                    temp2[27] = "" + stockInfoList2.get(6).getmOffsetPercent();
                    bundle.putString("" + 39, temp2[27]);
                    temp2[28] = "" + stockInfoList2.get(7).getmCnName();
                    bundle.putString("" + 40, temp2[28]);
                    temp2[29] = "" + stockInfoList2.get(7).getmNowPrice();
                    bundle.putString("" + 41, temp2[29]);
                    temp2[30] = "" + stockInfoList2.get(7).getmOffset();
                    bundle.putString("" + 42, temp2[30]);
                    temp2[31] = "" + stockInfoList2.get(7).getmOffsetPercent();
                    bundle.putString("" + 43, temp2[31]);
                    temp2[32] = "" + stockInfoList2.get(8).getmCnName();
                    bundle.putString("" + 44, temp2[32]);
                    temp2[33] = "" + stockInfoList2.get(8).getmNowPrice();
                    bundle.putString("" + 45, temp2[33]);
                    temp2[34] = "" + stockInfoList2.get(8).getmOffset();
                    bundle.putString("" + 46, temp2[34]);
                    temp2[35] = "" + stockInfoList2.get(8).getmOffsetPercent();
                    bundle.putString("" + 47, temp2[35]);

                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(networkTask).start();
    }

}
