package com.example.yow.easystock.BottomBar.MarketInfo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yow.easystock.R;

import java.util.ArrayList;

/**
 * Created by 12205 on 2016/8/19.
 */
public class MarketShAdapter extends ArrayAdapter<MarketShItem> {

    public MarketShAdapter(Context context, ArrayList<MarketShItem> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        MarketShItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_market_sh, parent, false);
        }

        // Lookup view for data population
        TextView name_textView = (TextView) convertView.findViewById(R.id.market_sh_name);
        TextView price_textView = (TextView) convertView.findViewById(R.id.market_sh_price);
        TextView offset_textView = (TextView) convertView.findViewById(R.id.market_sh_offset);
        TextView percent_textView = (TextView) convertView.findViewById(R.id.market_sh_percent);

        // Populate the data into the template view using the data object
        name_textView.setText(item.name);
        price_textView.setText(item.price);
        offset_textView.setText(item.offset);
        String temp = item.offsetPercent + "%";
        percent_textView.setText(temp);

        // Return the completed view to render on screen
        return convertView;
    }
}
