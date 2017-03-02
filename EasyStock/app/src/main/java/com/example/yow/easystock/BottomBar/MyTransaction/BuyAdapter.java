package com.example.yow.easystock.BottomBar.MyTransaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.yow.easystock.R;

import java.util.ArrayList;

/**
 * Created by 12205 on 2016/7/22.
 */
public class BuyAdapter extends ArrayAdapter<BuyItem> {

    public BuyAdapter(Context context, ArrayList<BuyItem> items){
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position
        BuyItem item = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transacton_buy, parent, false);
        }

        // Lookup view for data population
        TextView name_textView = (TextView) convertView.findViewById(R.id.name_item_buy);
        TextView price_textView = (TextView) convertView.findViewById(R.id.price_item_buy);
        TextView quantity_textView = (TextView) convertView.findViewById(R.id.quantity_item_buy);

        // Populate the data into the template view using the data object
        name_textView.setText(item.name);
        price_textView.setText(item.price);
        quantity_textView.setText(item.quantity);

        // Return the completed view to render on screen
        return convertView;
    }
}
