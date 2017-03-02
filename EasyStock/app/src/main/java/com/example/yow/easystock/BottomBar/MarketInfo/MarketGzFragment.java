package com.example.yow.easystock.BottomBar.MarketInfo;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.MarketGlobalClient;
import com.example.yow.easystock.SinaAPI.MarketGlobalInfo;
import com.example.yow.easystock.SinaAPI.SinaStockClient;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;

import java.util.List;

/**
 * Created by 12205 on 2016/7/24.
 */
public class MarketGzFragment extends Fragment {

    private SinaStockClient mClient;
    private MarketGlobalClient GlobalClient;
    Handler handler;
    TextView text1,text2,text3,text4,text5,text6,text7,text8,text9,text10,text11,text12;
    SwipeRefreshLayout mSwipeRefreshLayout;

    public static MarketGzFragment newInstance() {

        Bundle args = new Bundle();

        MarketGzFragment fragment = new MarketGzFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_market_gz,container,false);
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

        text1 = (TextView)getParentFragment().getActivity().findViewById(R.id.shangzhengzhishu);
        text2 = (TextView)getParentFragment().getActivity().findViewById(R.id.aguzhishu);
        text3 = (TextView)getParentFragment().getActivity().findViewById(R.id.bguzhishu);
        text4 = (TextView)getParentFragment().getActivity().findViewById(R.id.shenzhenzongzhi);
        text5 = (TextView)getParentFragment().getActivity().findViewById(R.id.shenzhenazhi);
        text6 = (TextView)getParentFragment().getActivity().findViewById(R.id.shenzhenbzhi);
        text7 = (TextView)getParentFragment().getActivity().findViewById(R.id.nasidake);
        text8 = (TextView)getParentFragment().getActivity().findViewById(R.id.daoqiongsi);
        text9 = (TextView)getParentFragment().getActivity().findViewById(R.id.biaopuzhishu);
        text10 = (TextView)getParentFragment().getActivity().findViewById(R.id.hengshengzhishu);
        text11 = (TextView)getParentFragment().getActivity().findViewById(R.id.fushi100);
        text12 = (TextView)getParentFragment().getActivity().findViewById(R.id.rijing225);

        mSwipeRefreshLayout = (SwipeRefreshLayout)getParentFragment().getActivity().findViewById(R.id.swipe_refresh_layout3);
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

        mClient = SinaStockClient.getInstance();
        GlobalClient = MarketGlobalClient.getInstance();
    }

    private void myHandleMessage(){

        handler = new Handler(){

            @Override
            public void handleMessage(Message msg) {

                Bundle bundle = msg.getData();

                String[] Prices = new String[6];
                for(int i = 0; i < 6; i++){
                    Prices[i] = bundle.getString("" + i);
                }

                //global market
                String[] Prices2 = new String[5];
                for(int i = 0; i < 5; i++){
                    int temp = i + 6;
                    Prices2[i] = bundle.getString("" + temp);
                }

                text1.setText(Prices[0]);
                text2.setText(Prices[1]);
                text3.setText(Prices[2]);
                text4.setText(Prices[3]);
                text5.setText(Prices[4]);
                text6.setText(Prices[5]);

                text7.setText(Prices2[0]);
                text8.setText(Prices2[1]);
                text9.setText(Prices2[2]);
                text10.setText(Prices2[3]);
                text11.setText(Prices2[4]);

            }
        };

    }

    private void StartNetworkTask(){

        Runnable networkTask1 = new Runnable() {
            @Override
            public void run() {

                Message msg = handler.obtainMessage();
                Bundle bundle = new Bundle();

                try{
                    String[] arr = new String[6];
                    arr[0] = "sh000001";
                    arr[1] = "sh000002";
                    arr[2] = "sh000003";
                    arr[3] = "sz399106";
                    arr[4] = "sz399107";
                    arr[5] = "sz399108";
                    List<SinaStockInfo> stockInfoList = mClient.getStockInfo(arr);

                    //global market
                    String[] arr2 = new String[5];
                    arr2[0] = "int_nasdaq";
                    arr2[1] = "int_dji";
                    arr2[2] = "int_sp500";
                    arr2[3] = "int_hangseng";
                    arr2[4] = "int_ftse";
                    List<MarketGlobalInfo> globalInfoList = GlobalClient.getStockInfo(arr2);

                    String[] temp = new String[6];
                    for(int i = 0; i < 6; i++) {

                        temp[i] = "" + stockInfoList.get(i).getNowPrice();
                        bundle.putString("" + i, temp[i]);
                    }

                    //global market
                    String[] temp2 = new String[5];
                    for(int i = 0; i < 5; i++) {

                        int _temp = i + 6;
                        temp2[i] = "" + globalInfoList.get(i).getPrice();
                        bundle.putString("" + _temp, temp2[i]);
                    }

                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(networkTask1).start();
    }

}
