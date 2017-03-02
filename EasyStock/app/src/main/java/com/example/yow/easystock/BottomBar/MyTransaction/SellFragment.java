package com.example.yow.easystock.BottomBar.MyTransaction;


import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.yow.easystock.SinaAPI.ParseStockInfoException;
import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.SinaStockClient;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;

import org.apache.commons.httpclient.HttpException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12205 on 2016/7/20.
 */
public class SellFragment extends Fragment {

    private SinaStockClient mClient;
    EditText stockCodeEditText;
    TextView stockNameTextView;
    ListView sellListView;
    View rootView;
    SellAdapter adapter;
    String code;
    Handler handler;

    public static SellFragment newInstance() {
        
        Bundle args = new Bundle();
        
        SellFragment fragment = new SellFragment();
        fragment.setArguments(args);
        return fragment;
    }
    
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_sell,container,false);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initClient();
        FindViewById();

        // Create the adapter to convert the array to views
        ArrayList<SellItem> arrayOfSell = new ArrayList<SellItem>();
        adapter = new SellAdapter(getParentFragment().getContext(),arrayOfSell);

        // Attach the adapter to a ListView & set onItemClickListener
        sellListView.setAdapter(adapter);
        initializeListView();

        //refresh listview if type in stock code
        EditTextMonitor();

    }

    private void initClient() {

        mClient = SinaStockClient.getInstance();
    }

    private void FindViewById(){

        sellListView = (ListView)rootView.findViewById(R.id.sell_list);
        stockCodeEditText = (EditText) rootView.findViewById(R.id.stock_code_sell);
        stockNameTextView = (TextView)rootView.findViewById(R.id.stock_name_sell);

    }

    private void initializeListView(){

        SellItem item1 = new SellItem("卖1","价格", "数量");
        SellItem item2 = new SellItem("卖2","价格", "数量");
        SellItem item3 = new SellItem("卖3","价格", "数量");
        SellItem item4 = new SellItem("卖4","价格", "数量");
        SellItem item5 = new SellItem("卖5","价格", "数量");
        adapter.add(item1);
        adapter.add(item2);
        adapter.add(item3);
        adapter.add(item4);
        adapter.add(item5);
    }

    private void myHandleMessage(){

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Bundle bundle = msg.getData();

                String[] sellPrices = new String[5];
                for(int i = 0; i < 5; i++){
                    sellPrices[i] = bundle.getString("sell" + i + "price");
                }

                String[] sellQuantity = new String[5];
                for(int i = 0; i < 5; i++){
                    sellQuantity[i] = bundle.getString("sell" + i + "quantity");
                }

                for(int i = 0; i < 5; i++){
                    int temp = i+1;
                    SellItem item = new SellItem("卖"+ temp,sellPrices[i], sellQuantity[i]);
                    adapter.add(item);
                }
                stockNameTextView.setText(bundle.getString("stockName"));
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

                    String[] arr = new String[1];
                    arr[0] = "sh" + code;
                    List<SinaStockInfo> stockInfoList = mClient.getStockInfo(arr);

                    bundle.putString("stockName",stockInfoList.get(0).getName());
                    for(int i = 0; i < 5; i++){
                        String[] temp = new String[2];
                        temp[0] = "" + stockInfoList.get(0).getSellInfo()[i].mPrice;
                        temp[1] = "" + stockInfoList.get(0).getSellInfo()[i].mCount;

                        bundle.putString("sell" + i + "price", temp[0]);
                        bundle.putString("sell" + i + "quantity", temp[1]);
                    }

                    msg.setData(bundle);
                    handler.sendMessage(msg);

                }catch (HttpException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (ParseStockInfoException e){
                    e.printStackTrace();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        };

        new Thread(networkTask).start();

    }

    private void EditTextMonitor(){

        stockCodeEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int start, int before, int count) {

                //clear listview info when enter a new stock code
                adapter.clear();
            }
            @Override
            public void afterTextChanged(Editable editable) {

                code = stockCodeEditText.getText().toString();

                if(code.length() == 6){
                    //create handler to receive message from networkTask thread
                    myHandleMessage();
                    //start new thread: networkTask
                    try{
                        //fetch stock info from sina API
                        StartNetworkTask();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }

            }
        });

    }


}
