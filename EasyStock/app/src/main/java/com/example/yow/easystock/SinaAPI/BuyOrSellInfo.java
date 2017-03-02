package com.example.yow.easystock.SinaAPI;

/**
 * Created by 12205 on 2016/7/22.
 */
public class BuyOrSellInfo{
    //数量。单位为“股”。100股为1手。
    public long mCount;
    //价格。
    public float mPrice;

    public BuyOrSellInfo(long count, float price){
        mCount = count;
        mPrice = price;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("数量： " + mCount + "股\n");
        sb.append("价格： " + mPrice + "元\n");
        return sb.toString();
    }
}
