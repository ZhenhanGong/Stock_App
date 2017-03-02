package com.example.yow.easystock.BottomBar.MySelection;

import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.yow.easystock.R;
import com.example.yow.easystock.SinaAPI.ParseStockInfoException;
import com.example.yow.easystock.SinaAPI.ShortStockClient;
import com.example.yow.easystock.SinaAPI.ShortStockInfo;
import com.example.yow.easystock.SinaAPI.SinaStockInfo;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import org.apache.commons.httpclient.HttpException;
import org.greenrobot.eventbus.EventBus;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * A placeholder fragment containing a simple view.
 */
public class AddSelectionActivityFragment extends Fragment {

    View rootView;
    String code = "";
    Handler handler;
    ShortStockClient mClient;
    TextView stockNameTextView,textView1,textView2,textView3,textView4,textView5;
    Button buttonAdd;
    EditText editTextStockCode;

    public AddSelectionActivityFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_add_selection, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        FindViewById();
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(editTextStockCode.getText().toString().isEmpty()){
                    editTextStockCode.setError("输入为空");
                }else{
                    EventBus.getDefault().postSticky(new AddSelectionEvent(editTextStockCode.getText().toString()));
                }
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        initClient();
        CandleStickChartTest();

        final EditText editText = (EditText)rootView.findViewById(R.id.editText_stock_code);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                code = editText.getText().toString();
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

    private void initClient() {

        mClient = ShortStockClient.getInstance();
    }

    private void FindViewById(){

        stockNameTextView = (TextView)rootView.findViewById(R.id.stock_name);
        textView1 = (TextView)rootView.findViewById(R.id.current_price);
        textView2 = (TextView)rootView.findViewById(R.id.offset);
        textView3 = (TextView)rootView.findViewById(R.id.offset_percentage);
        textView4 = (TextView)rootView.findViewById(R.id.deal_quantity);
        textView5 = (TextView)rootView.findViewById(R.id.deal_amount);
        buttonAdd = (Button)rootView.findViewById(R.id.button_add);
        editTextStockCode = (EditText)rootView.findViewById(R.id.editText_stock_code);

    }

    private void CandleStickChartTest(){

        //1. findViewByID
        CandleStickChart candleStickChart = (CandleStickChart)getActivity().findViewById(R.id.candle_stick_chart);

        //2. create entries
        ArrayList<CandleEntry> entries = new ArrayList<>();
        entries.add(new CandleEntry(0, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(1, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(2, 5.25f, 3.02f, 3.50f, 4.50f));
        entries.add(new CandleEntry(3, 6f,    3.25f, 4.40f, 5.0f));
        entries.add(new CandleEntry(4, 5.57f, 2f,    2.80f, 4.5f));
        entries.add(new CandleEntry(5, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(6, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(7, 5.25f, 3.02f, 3.50f, 4.50f));
        entries.add(new CandleEntry(8, 6f,    3.25f, 4.40f, 5.0f));
        entries.add(new CandleEntry(9, 5.57f, 2f,    2.80f, 4.5f));
        entries.add(new CandleEntry(10, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(11, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(12, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(13, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(14, 5.25f, 3.02f, 3.50f, 4.50f));
        entries.add(new CandleEntry(15, 6f,    3.25f, 4.40f, 5.0f));
        entries.add(new CandleEntry(16, 5.57f, 2f,    2.80f, 4.5f));
        entries.add(new CandleEntry(17, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(18, 5.50f, 2.70f, 3.35f, 4.96f));
        entries.add(new CandleEntry(19, 5.25f, 3.02f, 3.50f, 4.50f));
        entries.add(new CandleEntry(20, 6f,    3.25f, 4.40f, 5.0f));
        entries.add(new CandleEntry(21, 5.57f, 2f,    2.80f, 4.5f));
        entries.add(new CandleEntry(22, 4.62f, 2.02f, 2.70f, 4.13f));
        entries.add(new CandleEntry(23, 5.50f, 2.70f, 3.35f, 4.96f));

        //3. create data set using entries
        CandleDataSet candleDataSet = new CandleDataSet(entries,"# of calls");
        candleDataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        //4. define X-axis labels
        ArrayList<String> labels = new ArrayList<String>();
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");
        labels.add("January");
        labels.add("February");
        labels.add("March");
        labels.add("April");
        labels.add("May");
        labels.add("June");
        labels.add("July");
        labels.add("August");
        labels.add("September");
        labels.add("October");
        labels.add("November");
        labels.add("December");

        //5. set the data using data set & labels
        CandleData data = new CandleData(labels,candleDataSet);

        //6. inflat chart with data
        candleStickChart.setData(data);


    }

    private void myHandleMessage(){

        handler = new Handler(){
            @Override
            public void handleMessage(Message msg) {

                Bundle bundle = msg.getData();
                stockNameTextView.setText(bundle.getString("stockName"));
                textView1.setText(bundle.getString("currentPrice"));
                textView2.setText(bundle.getString("offset"));
                textView3.setText(bundle.getString("offsetPercentage")+"%");
                textView4.setText(bundle.getString("dealQuantity"));
                textView5.setText(bundle.getString("dealAmount"));

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
                    arr[0] = "s_sh" + code;
                    List<ShortStockInfo> stockInfoList = mClient.getStockInfo(arr);

                    bundle.putString("stockName",stockInfoList.get(0).getName());
                    bundle.putString("currentPrice",""+stockInfoList.get(0).getPrice());
                    bundle.putString("offset",""+stockInfoList.get(0).getOffset());
                    bundle.putString("offsetPercentage",""+stockInfoList.get(0).getPercent());
                    bundle.putString("dealQuantity",""+stockInfoList.get(0).getQuantity());
                    bundle.putString("dealAmount",""+stockInfoList.get(0).getAmount());

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

}
