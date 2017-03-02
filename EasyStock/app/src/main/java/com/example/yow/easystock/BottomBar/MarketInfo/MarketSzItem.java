package com.example.yow.easystock.BottomBar.MarketInfo;

/**
 * Created by 12205 on 2016/8/20.
 */
public class MarketSzItem {

    public String name;
    public String price;
    public String offset;
    public String offsetPercent;

    public MarketSzItem(String name, String price, String offset, String offsetPercent){

        this.name = name;
        this.price = price;
        this.offset = offset;
        this.offsetPercent = offsetPercent;
    }
}
