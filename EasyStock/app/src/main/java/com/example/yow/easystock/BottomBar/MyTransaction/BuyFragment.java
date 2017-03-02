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

import org.apache.commons.httpclient.HttpException;

import com.example.yow.easystock.SinaAPI.ParseStockInfoException;
import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.SinaStockClient;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 12205 on 2016/7/20.
 */
public class BuyFragment extends Fragment {

    private SinaStockClient mClient;
    EditText stockCodeEditText;
    TextView stockNameTextView;
    String code;
    View rootView;
    ListView buyListView;
    BuyAdapter adapter;
    Handler handler;
    ArrayList<BuyItem> arrayOfBuy;

    public static BuyFragment newInstance() {

        Bundle args = new Bundle();

        BuyFragment fragment = new BuyFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        arrayOfBuy = new ArrayList<BuyItem>();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView = inflater.inflate(R.layout.fragment_buy,container,false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FindViewById();
        // Create the adapter to convert the array to views
        adapter = new BuyAdapter(getParentFragment().getContext(),arrayOfBuy);

        // Attach the adapter to a ListView & set onItemClickListener
        buyListView.setAdapter(adapter);
        initializeListView();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initClient();
        //refresh listview if type in stock code
        EditTextMonitor();
    }

    public void initializeListView(){

        //adapter.clear();
        BuyItem item1 = new BuyItem("买1","价格", "数量");
        BuyItem item2 = new BuyItem("买2","价格", "数量");
        BuyItem item3 = new BuyItem("买3","价格", "数量");
        BuyItem item4 = new BuyItem("买4","价格", "数量");
        BuyItem item5 = new BuyItem("买5","价格", "数量");
        adapter.add(item1);
        adapter.add(item2);
        adapter.add(item3);
        adapter.add(item4);
        adapter.add(item5);
    }

    private void initClient() {

        mClient = SinaStockClient.getInstance();
    }

    private void FindViewById(){

        buyListView = (ListView)rootView.findViewById(R.id.buy_list);
        stockCodeEditText = (EditText) rootView.findViewById(R.id.stock_code_buy);
        stockNameTextView = (TextView)rootView.findViewById(R.id.stock_name_buy);

    }

    private void myHandleMessage(){

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Bundle bundle = msg.getData();

                String[] buyPrices = new String[5];
                for(int i = 0; i < 5; i++){
                    buyPrices[i] = bundle.getString("buy" + i + "price");
                }

                String[] buyQuantity = new String[5];
                for(int i = 0; i < 5; i++){
                    buyQuantity[i] = bundle.getString("buy" + i + "quantity");
                }

                for(int i = 0; i < 5; i++){
                    int temp = i+1;
                    BuyItem item = new BuyItem("买"+ temp,buyPrices[i], buyQuantity[i]);
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
                        temp[0] = "" + stockInfoList.get(0).getBuyInfo()[i].mPrice;
                        temp[1] = "" + stockInfoList.get(0).getBuyInfo()[i].mCount;

                        bundle.putString("buy" + i + "price", temp[0]);
                        bundle.putString("buy" + i + "quantity", temp[1]);
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
