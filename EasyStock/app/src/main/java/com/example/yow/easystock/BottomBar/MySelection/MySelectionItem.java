package com.example.yow.easystock.BottomBar.MySelection;

/**
 * Created by 12205 on 2016/7/19.
 */
public class MySelectionItem {

    public String stock_name;
    public String stock_price;
    public String offset;
    public String floating_percentage;

    public MySelectionItem(String name, String price, String mOffset, String percentage) {

        stock_name = name;
        stock_price = price;
        offset = mOffset;
        floating_percentage = percentage;
    }
}
