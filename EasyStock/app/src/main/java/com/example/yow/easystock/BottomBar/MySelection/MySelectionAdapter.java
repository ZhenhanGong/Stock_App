package com.example.yow.easystock.BottomBar.MySelection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yow.easystock.R;

import java.util.ArrayList;

/**
 * Created by 12205 on 2016/7/19.
 */
public class MySelectionAdapter extends ArrayAdapter<MySelectionItem> {

    public MySelectionAdapter(Context context, ArrayList<MySelectionItem> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        MySelectionItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_my_selection, parent, false);
        }

        // Lookup view for data population
        TextView stockName = (TextView) convertView.findViewById(R.id.stockName);
        TextView stockPrice = (TextView) convertView.findViewById(R.id.stockPrice);
        TextView stockOffset = (TextView) convertView.findViewById(R.id.stockOffset);
        TextView floatingPercentage = (TextView) convertView.findViewById(R.id.floatingPercentage);

        // Populate the data into the template view using the data object
        stockName.setText(item.stock_name);
        stockPrice.setText(item.stock_price);
        stockOffset.setText(item.offset);
        floatingPercentage.setText(item.floating_percentage);

        // Return the completed view to render on screen
        return convertView;
    }

}
