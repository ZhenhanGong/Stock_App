package com.example.yow.easystock.SinaAPI;

/**
 * Created by 12205 on 2016/8/18.
 */
public class MarketGlobalInfo {

    //股票名字
    private String mName;
    //当前价
    private float mNowPrice;
    //涨跌额
    private float mOffset;
    //涨跌幅
    private float mOffsetPercent;

    private MarketGlobalInfo(String myName, float myPrice, float myOffset, float myPercent){

        mName = myName;
        mNowPrice = myPrice;
        mOffset = myOffset;
        mOffsetPercent = myPercent;
    }

    public static MarketGlobalInfo parseStockInfo(String source) throws ParseStockInfoException{

        int start = source.indexOf('\"');
        String targetString = source.substring(start + 1, source.length() - 3);

        String[] infoStr = targetString.split(",");

        int length=infoStr.length;
        if(infoStr.length != 4) {
            throw new ParseStockInfoException();
        }

        final String name = infoStr[0];
        final float todayPrice = Float.parseFloat(infoStr[1]);
        final float offset = Float.parseFloat(infoStr[2]);
        final float offsetPercent = Float.parseFloat(infoStr[3]);

        MarketGlobalInfo marketGlobalInfo = new MarketGlobalInfo(name,todayPrice,offset,offsetPercent);
        return marketGlobalInfo;
    }

    public String getName() {
        return mName;
    }

    public float getPrice() {
        return mNowPrice;
    }

    public float getOffset() {
        return mOffset;
    }

    public float getPercent() {
        return mOffsetPercent;
    }

}
