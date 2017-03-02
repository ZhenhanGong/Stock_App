package com.example.yow.easystock.SinaAPI;

/**
 * Created by 12205 on 2016/8/19.
 */
public class HongKongInfo {

    //英文名
    private String mEnName;
    //中文名
    private String mCnName;
    //开盘价
    private float mTodayPrice;
    //昨收价
    private float mYestardayPrice;
    //最高价
    private float mHighestPrice;
    //最低价
    private float mLowestPrice;
    //当前价
    private float mNowPrice;
    //涨跌额
    private float mOffset;
    //涨跌幅
    private float mOffsetPercent;
    //卖一
    private float mSellPrice;
    //买一
    private float mBuyPrice;
    //成交额
    private float mTradeAmount;
    //成交量
    private long mTradeQuantity;
    //市盈率
    private float mEarnRate;
    //收益率
    private float mEarnRate2;
    //52周最高
    private float mHigh;
    //52周最低
    private float mLow;
    //行情日期
    private String mTime;
    //行情时间
    private String mSpecificTime;

    private HongKongInfo(String temp1, String temp2, float temp3, float temp4,float temp5,float temp6,float temp7,float temp8,float temp9,float temp10,
                         float temp11,float temp12,long temp13,float temp14,float temp15,float temp16,float temp17,String temp18,String temp19){

        mEnName = temp1;
        mCnName = temp2;
        mTodayPrice = temp3;
        mYestardayPrice = temp4;
        mHighestPrice = temp5;
        mLowestPrice = temp6;
        mNowPrice = temp7;
        mOffset = temp8;
        mOffsetPercent = temp9;
        mSellPrice = temp10;
        mBuyPrice = temp11;
        mTradeAmount = temp12;
        mTradeQuantity = temp13;
        mEarnRate = temp14;
        mEarnRate2 = temp15;
        mHigh = temp16;
        mLow = temp17;
        mTime = temp18;
        mSpecificTime = temp19;

    }

    public static HongKongInfo parseStockInfo(String source) throws ParseStockInfoException{

        int start = source.indexOf('\"');
        String targetString = source.substring(start + 1, source.length() - 2);

        String[] infoStr = targetString.split(",");

        int length=infoStr.length;
        if(infoStr.length != 19) {
            throw new ParseStockInfoException();
        }

        final String EnName = infoStr[0];
        final String CnName = infoStr[1];
        final float TodayPrice = Float.parseFloat(infoStr[2]);
        final float YestardayPrice = Float.parseFloat(infoStr[3]);
        final float HighestPrice = Float.parseFloat(infoStr[4]);
        final float LowestPrice = Float.parseFloat(infoStr[5]);
        final float NowPrice = Float.parseFloat(infoStr[6]);
        final float Offset = Float.parseFloat(infoStr[7]);
        final float OffsetPercent = Float.parseFloat(infoStr[8]);
        final float SellPrice = Float.parseFloat(infoStr[9]);
        final float BuyPrice = Float.parseFloat(infoStr[10]);
        final float TradeAmount = Float.parseFloat(infoStr[11]);
        final long TradeQuantity = Long.parseLong(infoStr[12]);
        final float EarnRate = Float.parseFloat(infoStr[13]);
        final float EarnRate2 = Float.parseFloat(infoStr[14]);
        final float High = Float.parseFloat(infoStr[15]);
        final float Low = Float.parseFloat(infoStr[16]);
        final String Time = infoStr[17];
        final String SpecificTime = infoStr[18];

        HongKongInfo info = new HongKongInfo(EnName,CnName,TodayPrice,YestardayPrice,HighestPrice,LowestPrice,NowPrice,Offset,OffsetPercent,SellPrice,BuyPrice,
                TradeAmount,TradeQuantity,EarnRate,EarnRate2,High,Low,Time,SpecificTime);
        return info;
    }

    public String getmEnName(){
        return mEnName;
    }

    public String getmCnName(){
        return mCnName;
    }

    public float getmTodayPrice(){
        return mTodayPrice;
    }

    public float getmYestardayPrice(){
        return mYestardayPrice;
    }

    public float getmHighestPrice(){
        return mHighestPrice;
    }

    public float getmLowestPrice(){
        return mLowestPrice;
    }

    public float getmNowPrice(){
        return mNowPrice;
    }

    public float getmOffset(){
        return mOffset;
    }

    public float getmOffsetPercent(){
        return mOffsetPercent;
    }

    public float getmSellPrice(){
        return mSellPrice;
    }

    public long getmTradeQuantity(){
        return mTradeQuantity;
    }

    public float getmEarnRate(){
        return mEarnRate;
    }

    public float getmBuyPrice(){
        return mBuyPrice;
    }

    public float getmTradeAmount(){
        return  mTradeAmount;
    }

    public float getmEarnRate2(){
        return mEarnRate2;
    }

    public float getmHigh(){
        return mHigh;
    }

    public float getmLow(){
        return mLow;
    }

    public String getmTime(){
        return mTime;
    }

    public String getmSpecificTime(){
        return mSpecificTime;
    }

}
