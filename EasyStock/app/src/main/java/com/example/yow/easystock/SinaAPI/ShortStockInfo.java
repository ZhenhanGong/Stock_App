package com.example.yow.easystock.SinaAPI;

/**
 * Created by 12205 on 2016/8/18.
 */
public class ShortStockInfo {

    //股票名字
    private String mName;
    //当前价
    private float mNowPrice;
    //涨跌额
    private float mOffset;
    //涨跌幅
    private float mOffsetPercent;
    //成交量
    private float dealtQuantity;
    //成交额
    private float dealtAmount;

    private ShortStockInfo(String myName, float myPrice, float myOffset, float myPercent, float myQuantity, float myAmount){

        mName = myName;
        mNowPrice = myPrice;
        mOffset = myOffset;
        mOffsetPercent = myPercent;
        dealtQuantity = myQuantity;
        dealtAmount = myAmount;
    }

    public static ShortStockInfo parseStockInfo(String source) throws ParseStockInfoException{

        int start = source.indexOf('\"');
        String targetString = source.substring(start + 1, source.length() - 2);

        String[] infoStr = targetString.split(",");

        int length = infoStr.length;
        if(infoStr.length != 6) {
            throw new ParseStockInfoException();
        }

        final String name = infoStr[0];
        final float todayPrice = Float.parseFloat(infoStr[1]);
        final float offset = Float.parseFloat(infoStr[2]);
        final float offsetPercent = Float.parseFloat(infoStr[3]);
        final float quantity = Float.parseFloat(infoStr[4]);
        final float amount = Float.parseFloat(infoStr[5]);

        ShortStockInfo shortStockInfo = new ShortStockInfo(name,todayPrice,offset,offsetPercent,quantity,amount);
        return shortStockInfo;
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

    public float getQuantity() {
        return dealtQuantity;
    }

    public float getAmount() {
        return dealtAmount;
    }

}
