package com.example.yow.easystock.BottomBar.MySelection;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yow.easystock.R;
import com.github.mikephil.charting.charts.CandleStickChart;
import com.github.mikephil.charting.data.CandleData;
import com.github.mikephil.charting.data.CandleDataSet;
import com.github.mikephil.charting.data.CandleEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class SelectionDetailFragment extends Fragment {

    public SelectionDetailFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_selection_detail, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        CandleStickChartTest();
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
}
